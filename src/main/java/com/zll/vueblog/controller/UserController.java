package com.zll.vueblog.controller;


import com.zll.vueblog.common.lang.Result;
import com.zll.vueblog.entity.User;
import com.zll.vueblog.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zll
 * @since 2021-06-16
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequiresAuthentication  // 登录后才能访问
    @GetMapping("/{id}")
    // //@PathVariable可以用来映射URL中的占位符到目标方法的参数中
    public Result test(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        return Result.success(user);
    }

    @PostMapping("/save")
    // 使用@Validated注解方式，如果实体不符合要求，系统会抛出异常，那么我们的异常处理中就捕获到MethodArgumentNotValidException
    public Result save(@Validated @RequestBody User user) {
        return Result.success(user);
    }
}
