package com.lrh.auth.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lrh.auth.mapper.SysUserMapper;
import com.lrh.auth.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lrh.model.system.SysUser;
import com.lrh.security.custom.CustomUser;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;


/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lrh
 * @since 2023-04-18
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {


    @Override
    public void updateStatus(Long id, Integer status) {
        //1.根据用户id查询出用户信息
        SysUser sysUser = baseMapper.selectById(id);
        //2。设置修改状态值
        sysUser.setStatus(status);
        //3.调用方法进行修改
        baseMapper.updateById(sysUser);
    }

    @Override
    public SysUser getUerByUserName(String username) {
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysUser::getUsername,username);
        SysUser sysUser = baseMapper.selectOne(lambdaQueryWrapper);
        return sysUser;
    }

}
