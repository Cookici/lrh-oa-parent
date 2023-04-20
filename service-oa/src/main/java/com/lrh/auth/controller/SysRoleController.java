package com.lrh.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lrh.auth.service.SysRoleService;
import com.lrh.common.result.Result;
import com.lrh.model.system.SysRole;
import com.lrh.vo.system.AssginRoleVo;
import com.lrh.vo.system.SysRoleQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @ProjectName: lrh-oa-parent
 * @Package: com.lrh.auth.controller
 * @ClassName: SysRoleController
 * @Author: 63283
 * @Description: 角色管理Controller
 * @Date: 2023/4/11 13:58
 */

@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {


    @Autowired
    private SysRoleService sysRoleService;

    //1.查询所有角色和当前对象所属角色
    @ApiOperation("获取角色")
    @GetMapping("/toAssign/{userId}")
    public Result toAssign(@PathVariable Long userId){
        Map<String, Object> map = sysRoleService.findRoleDataByUserId(userId);
        return Result.ok(map);
    }

    //2.为用户分配角色
    @ApiOperation("为用户分配角色")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginRoleVo assginRoleVo){
        sysRoleService.doAssign(assginRoleVo);
        return Result.ok();
    }


    @ApiOperation("查询所有角色")
    @GetMapping("/findAll")
    public Result getAll(){
        List<SysRole> list = sysRoleService.list();
        return Result.ok(list);
    }

    @ApiOperation("条件分页查询")
    @GetMapping("/{page}/{limit}")       //page:当前页 limit:每页显示记录数 sysRoleQueryVo:条件对象
    public Result pageQueryRole(
            @PathVariable Long page,
            @PathVariable Long limit,
            @Nullable SysRoleQueryVo sysRoleQueryVo
    ){
        //调用service的方法实现
        //1.创建Page对象,传递分页参数
        Page<SysRole> pageParam = new Page<>(page,limit);
        //2.封装条件,判断条件是否为空,不为空进行封装
        LambdaQueryWrapper<SysRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        String roleName = sysRoleQueryVo.getRoleName();
        if(!StringUtils.isEmpty(roleName)){
            //封装
            lambdaQueryWrapper.like(SysRole::getRoleName,roleName);
        }
        //3.调用方法实现
        IPage<SysRole> pageModel = sysRoleService.page(pageParam, lambdaQueryWrapper);
        return Result.ok(pageModel);
    }



    @ApiOperation("添加角色")
    @PostMapping("/save")
    public Result save(@RequestBody SysRole sysRole){
        //调用service方法实现添加
        boolean isSuccess = sysRoleService.save(sysRole);
        if (isSuccess){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }


    @ApiOperation("根据id查询角色")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable Long id){
        SysRole sysRole = sysRoleService.getById(id);
        return Result.ok(sysRole);
    }


    @ApiOperation("修改角色")
    @PutMapping("/update")
    public Result update(@RequestBody SysRole sysRole){
        //调用service方法实现添加
        boolean isSuccess = sysRoleService.updateById(sysRole);
        if (isSuccess){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }


    @ApiOperation("根据id删除角色")
    @DeleteMapping ("/remove/{id}")
    
    public Result delete(@PathVariable Long id){
        //调用service方法实现添加
        boolean isSuccess = sysRoleService.removeById(id);
        if (isSuccess){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    @ApiOperation("批量删除角色")
    @DeleteMapping ("/batchRemove")
    
    public Result batchDelete(@RequestBody List<Long> idList){
        //调用service方法实现添加
        boolean isSuccess = sysRoleService.removeByIds(idList);
        if (isSuccess){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }




}
