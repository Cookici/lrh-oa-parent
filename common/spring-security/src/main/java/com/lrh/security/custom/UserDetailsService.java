package com.lrh.security.custom;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: lrh-oa-parent
 * @Package: com.lrh.security.custom
 * @ClassName: UserDetailService
 * @Author: 63283
 * @Description: TODO
 * @Date: 2023/4/22 19:35
 */

@Component
public interface UserDetailsService extends org.springframework.security.core.userdetails.UserDetailsService{

    /**
     * 根据用户名获取用户对象（获取不到直接抛异常）
     */
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
