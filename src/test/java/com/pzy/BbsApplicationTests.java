package com.pzy;
import cn.hutool.core.date.DateUtil;
import com.pzy.entity.User;
import com.pzy.mapper.UserMapper;
import com.pzy.util.SnowFlakeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class BbsApplicationTests {

    @Autowired(required = false)
    private UserMapper userMapper;



    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }


}
