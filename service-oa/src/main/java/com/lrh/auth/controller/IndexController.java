package com.lrh.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lrh.auth.service.SysMenuService;
import com.lrh.auth.service.SysUserService;
import com.lrh.common.config.exception.CustomException;
import com.lrh.common.jwt.JwtHelper;
import com.lrh.common.result.Result;
import com.lrh.common.utils.MD5;
import com.lrh.model.system.SysUser;
import com.lrh.vo.system.LoginVo;
import com.lrh.vo.system.RouterVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: lrh-oa-parent
 * @Package: com.lrh.auth.controller
 * @ClassName: IndexController
 * @Author: 63283
 * @Description: 登录以及授权用户操作的权限
 * @Date: 2023/4/14 14:54
 */

@Api(tags = "后台登录管理")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;


    /**
     * 登录操作
     *
     * @param loginVo
     * @return
     */
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo) {
        // {"code":200,"data":{"token":"admin-token"}}
        //Map<String,Object> map = new HashMap<>();
        //map.put("token","admin-token");
        //return Result.ok(map);

        //1.获取输入用户名和密码
        String username = loginVo.getUsername();
        String password = loginVo.getPassword();

        //2.根据用户名查询数据库
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysUser::getUsername, username);
        SysUser sysUser = sysUserService.getOne(lambdaQueryWrapper);

        //3.用户信息是否存在
        if (sysUser == null) {
            throw new CustomException(201, "没有此用户");
        }

        //4.判断密码
        String password_db = sysUser.getPassword();
        String password_input = MD5.encrypt(password);
        if (!password_db.equals(password_input)) {
            throw new CustomException(201, "密码错误");
        }

        //5.判断用户是否禁用
        if (sysUser.getStatus().intValue() == 0) {
            throw new CustomException(201, "用户被停用");
        }

        //6.使用jwt根据用户id和用户名生成token字符串
        String token = JwtHelper.createToken(sysUser.getId(), sysUser.getUsername());

        //7.返回
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        return Result.ok(map);

    }

    //info
    @GetMapping("info")
    public Result info(HttpServletRequest request) {
        //1.从请求头获取用户信息（获取请求头token字符串）
        String token = request.getHeader("token");

        //2.从token字符串获取用户id 或者 用户名称
        Long userId = JwtHelper.getUserId(token);

        //3.根据用户id查询数据库，把用户信息取出来
        SysUser sysUser = sysUserService.getById(userId);

        //4.根据用户id获取用户可以操作的菜单列表（菜单）
        //查询数据库动态构建路由结构，进行显示
        List<RouterVo> routerList = sysMenuService.findUserMenuListByUserId(userId);

        //5.根据用户id获取用户可以操作的按钮列表（按钮）
        List<String> permsList = sysMenuService.findUserPermsByUserId(userId);

        //6.返回相应的数据

        Map<String, Object> map = new HashMap<>();
        map.put("roles", "[admin]");
        map.put("name", sysUser.getName());
        map.put("avatar", "https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
        //返回用户可以操作的菜单
        map.put("routers", routerList);
        //返回用户可以操作的按钮
        map.put("buttons", permsList);

        return Result.ok(map);
    }

    /**
     * 退出操作
     *
     * @return
     */
    @PostMapping("/logout")
    public Result logout() {
        return Result.ok();
    }

}
