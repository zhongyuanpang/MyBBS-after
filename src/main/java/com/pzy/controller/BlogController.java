package com.pzy.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pzy.entity.Blog;
import com.pzy.entity.User;
import com.pzy.service.BlogService;
import com.pzy.service.TagService;
import com.pzy.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author nice
 * @since 2021-07-07
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;



    @PostMapping("/saveBlog")
    public Result saveBlog(@Validated @RequestBody Blog blog){
        System.out.println(blog);
        blogService.save(blog);

        return Result.succ("发表成功");
    }
/**
 * @author : pangzy
 * @date : 2021/8/16 17:41
 * @return : com.pzy.util.Result
 * 分页
 */
    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage){
        Page page = new Page(currentPage,10);
        IPage pageDate = blogService.page(page,new QueryWrapper<Blog>().eq("published","1"));
        return Result.succ(pageDate);
    }

    @GetMapping("/updateBlog")
    public Result updateBlog(){

        return Result.succ("");
    }


    @GetMapping("/deleteBlog")
    public Result deleteBlog(){

        return Result.succ("");
    }


    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id") Long id){

        return Result.succ("");
    }





}
