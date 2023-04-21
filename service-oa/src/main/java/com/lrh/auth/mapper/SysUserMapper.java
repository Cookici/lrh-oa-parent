package com.lrh.auth.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lrh.model.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author lrh
 * @since 2023-04-18
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}
