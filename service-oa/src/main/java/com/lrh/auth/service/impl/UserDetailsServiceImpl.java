package com.lrh.auth.service.impl;

import com.lrh.auth.service.SysMenuService;
import com.lrh.auth.service.SysUserService;
import com.lrh.model.system.SysUser;
import com.lrh.security.custom.CustomUser;
import com.lrh.security.custom.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: lrh-oa-parent
 * @Package: com.lrh.auth.service.impl
 * @ClassName: UserDetailsServiceImpl
 * @Author: 63283
 * @Description: TODO
 * @Date: 2023/4/23 15:24
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getUerByUserName(username);
        if(sysUser == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        if(sysUser.getStatus().intValue() == 0){
            throw new RuntimeException("账号已停用");
        }

        //根据userId查询用户可以操作权限的数据
        List<String> userPermsList = sysMenuService.findUserPermsByUserId(sysUser.getId());
        //创建list集合，封装最终权限数据
        List<SimpleGrantedAuthority> simpleGrantedAuthList = new ArrayList<>();
        //查询list集合遍历
        for (String perm : userPermsList) {
            simpleGrantedAuthList.add(new SimpleGrantedAuthority(perm.trim()));
        }
        return new CustomUser(sysUser, simpleGrantedAuthList);
    }
}
