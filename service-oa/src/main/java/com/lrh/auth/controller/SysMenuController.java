package com.lrh.auth.controller;


import com.lrh.auth.service.SysMenuService;
import com.lrh.common.result.Result;
import com.lrh.model.system.SysMenu;
import com.lrh.vo.system.AssginMenuVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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


    //查询所有菜单和角色分配的菜单
    @PreAuthorize("hasAuthority('bnt.sysMenu.list')")
    @ApiOperation("查询所有菜单和角色分配的菜单")
    @GetMapping("toAssign/{roleId}")
    public Result toAssign(@PathVariable Long roleId) {
        List<SysMenu> list = sysMenuService.findMenuByRoleId(roleId);
        return Result.ok(list);
    }

    @PreAuthorize("hasAuthority('bnt.sysRole.assignAuth')")
    @ApiOperation("角色分配菜单")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginMenuVo assginMenuVo) {
        sysMenuService.doAssign(assginMenuVo);
        return Result.ok();
    }


    @PreAuthorize("hasAuthority('bnt.sysMenu.list')")
    @ApiOperation("菜单列表")
    @GetMapping("/findNodes")
    public Result findNodes() {
        List<SysMenu> list = sysMenuService.findNodes();
        return Result.ok(list);
    }


    @PreAuthorize("hasAuthority('bnt.sysMenu.add')")
    @ApiOperation("新增菜单")
    @PostMapping("/save")
    public Result sava(@RequestBody SysMenu sysMenu) {
        boolean isSuccess = sysMenuService.save(sysMenu);
        if (isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @PreAuthorize("hasAuthority('bnt.sysMenu.update')")
    @ApiOperation("修改菜单")
    @PutMapping("/update")
    public Result update(@RequestBody SysMenu sysMenu) {
        boolean isSuccess = sysMenuService.updateById(sysMenu);
        if (isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @PreAuthorize("hasAuthority('bnt.sysMenu.remove')")
    @ApiOperation("删除菜单")
    @DeleteMapping("/remove/{id}")
    public Result delete(@PathVariable Long id) {
        sysMenuService.removeMenuById(id);
        return Result.ok();
    }


}

