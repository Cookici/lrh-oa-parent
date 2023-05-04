package com.lrh.security.filter;

import com.lrh.common.jwt.JwtHelper;
import com.lrh.common.result.ResponseUtil;
import com.lrh.common.result.Result;
import com.lrh.common.result.ResultCodeEnum;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * @ProjectName: lrh-oa-parent
 * @Package: com.lrh.security.filter
 * @ClassName: TokenAuthenticationFilter
 * @Author: 63283
 * @Description: TODO
 * @Date: 2023/5/4 10:37
 */

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //如果是登录接口，直接放行
        if("/admin/system/index/login".equals(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        if(null != authentication) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } else {
            ResponseUtil.out(response, Result.build(null, ResultCodeEnum.LOGIN_ERROR));
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        //请求头里是否有token
        String token = request.getHeader("token");
        if(!StringUtils.isEmpty(token)){
            String userName = JwtHelper.getUserName(token);
            if(!StringUtils.isEmpty(userName)){
                return new UsernamePasswordAuthenticationToken(userName,null, Collections.emptyList());
            }
        }
        return null;
    }
}
