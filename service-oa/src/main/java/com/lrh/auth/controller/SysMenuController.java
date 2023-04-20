package com.lrh.auth.controller;


import com.lrh.auth.service.SysMenuService;
import com.lrh.common.result.Result;
import com.lrh.model.system.SysMenu;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author lrh
 * @since 2023-04-19
 */
@Api(tags = "菜单管理接口")
@RestController
@RequestMapping("/admin/system/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @ApiOperation("菜单列表")
    @GetMapping("/findNodes")
    public Result findNodes(){
        List<SysMenu> list = sysMenuService.findNodes();
        return Result.ok(list);
    }


    @ApiOperation("新增菜单")
    @PostMapping("/save")
    public Result sava(@RequestBody SysMenu sysMenu){
        boolean isSuccess = sysMenuService.save(sysMenu);
        if (isSuccess){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @ApiOperation("修改菜单")
    @PutMapping("/update")
    public Result update(@RequestBody SysMenu sysMenu){
        boolean isSuccess = sysMenuService.updateById(sysMenu);
        if (isSuccess){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @ApiOperation("删除菜单")
    @DeleteMapping("/remove/{id}")
    public Result delete(@PathVariable Long id){
        boolean isSuccess = sysMenuService.removeById(id);
        if (isSuccess){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }


}

