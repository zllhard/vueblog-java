package com.zll.vueblog.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zll.vueblog.common.dto.LoginDto;
import com.zll.vueblog.common.lang.Result;
import com.zll.vueblog.entity.User;
import com.zll.vueblog.service.UserService;
import com.zll.vueblog.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    // loginDto中的用户名和密码去RequestBody里获取，需要校验
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        // 根据loginDto中的用户名去数据库查找user
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        // 判断用户是否为空，为空会抛出异常，由全局异常类处理来捕获处理。
        Assert.notNull(user, "用户不存在");

        // 判断密码是否正确
        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            return Result.fail("密码不正确");
        }

        String jwt = jwtUtils.generateToken(user.getId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");
        return Result.success(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .map()
        );
    }

    @GetMapping("/logout")
    @RequiresAuthentication  // 登录后才能退出
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.success(null);
    }
}
