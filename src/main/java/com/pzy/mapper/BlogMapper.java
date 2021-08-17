package com.pzy.mapper;

import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.pzy.entity.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author nice
 * @since 2021-07-07
 */
public interface BlogMapper extends BaseMapper<Blog> {

}
