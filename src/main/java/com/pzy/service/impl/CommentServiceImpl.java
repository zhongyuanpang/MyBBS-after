package com.pzy.service.impl;

import com.pzy.entity.Comment;
import com.pzy.mapper.CommentMapper;
import com.pzy.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nice
 * @since 2021-07-07
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
