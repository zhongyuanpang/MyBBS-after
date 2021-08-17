package com.pzy.controller;

import com.pzy.util.RandomBgUtils;
import com.pzy.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Nice
 * @Date 2021/8/14 8:56
 */
@RestController
public class BackgroundController {
    @GetMapping("/getBC")
    public Result getBC(){
        String imhgUrl = RandomBgUtils.randomBackground();
        return Result.succ(imhgUrl);
    }
}
