package com.zll.vueblog.service.impl;

import com.zll.vueblog.entity.Blog;
import com.zll.vueblog.mapper.BlogMapper;
import com.zll.vueblog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zll
 * @since 2021-06-16
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
