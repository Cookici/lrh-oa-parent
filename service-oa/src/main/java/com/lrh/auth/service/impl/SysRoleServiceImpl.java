package com.lrh.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lrh.auth.mapper.SysRoleMapper;
import com.lrh.auth.service.SysRoleService;
import com.lrh.auth.service.SysUserRoleService;
import com.lrh.model.system.SysRole;
import com.lrh.model.system.SysUserRole;
import com.lrh.vo.system.AssginRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ProjectName: lrh-oa-parent
 * @Package: com.lrh.auth.service.impl
 * @ClassName: SysRoleServiceImpl
 * @Author: 63283
 * @Description: TODO
 * @Date: 2023/4/11 13:56
 */

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    //ServiceImpl<SysRoleMapper, SysRole> 中已经将SysRoleMapper注入到到M中 直接使用M对象即SysRoleMapper接口

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public Map<String, Object> findRoleDataByUserId(Long userId) {

        //1.查询所有角色，返回list集合
        List<SysRole> allRoleList = baseMapper.selectList(null);

        //2.根据用户id查询角色用户关系表，然后根据userId查到指定用户所对应的所有角色id
        LambdaQueryWrapper<SysUserRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysUserRole::getUserId, userId);
        List<SysUserRole> existUserRoleList = sysUserRoleService.list(lambdaQueryWrapper);

        //从查询出来的用户id对应角色sysRoles集合，获取所有角色id
        // 方法一
        /*
        List<Long> roleIds = new ArrayList<>();
        for (SysUserRole existSysRoleUser : existSysRoleUsers) {
            roles.add(existSysRoleUser.getRoleId());
        }
        */

        //方法二
        List<Long> exsitRoleIdList = existUserRoleList.stream()
                .map(existSysRoleUser -> existSysRoleUser.getRoleId())
                .collect(Collectors.toList());


        //3.根据查询所有角色id，找到所对应角色信息
        //根据角色id到所有的角色的list集合里面进行比较，如果说有相同的就取到对象，没有就不取
        List<SysRole> assignRoleList = new ArrayList<>();
        for (SysRole sysRole : allRoleList) {
            if (exsitRoleIdList.contains(sysRole.getId())) {
                assignRoleList.add(sysRole);
            }
        }

        //4.把得到的两个部分的数据封装在map集合返回
        Map<String,Object> roleMap = new HashMap<>();
        roleMap.put("assignRoleList",assignRoleList);
        roleMap.put("allRoleList",allRoleList);

        return roleMap;
    }

    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {
        //把用户之前分配的角色数据删除,用户角色关系表中根据userid删除
        LambdaQueryWrapper<SysUserRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysUserRole::getUserId,assginRoleVo.getUserId());
        sysUserRoleService.remove(lambdaQueryWrapper);

        //重新进行分配
        List<Long> roleIdList = assginRoleVo.getRoleIdList();
        for (Long roleId : roleIdList) {
            if(StringUtils.isEmpty(roleId)){
                continue;
            }
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(assginRoleVo.getUserId());
            sysUserRole.setRoleId(roleId);
            sysUserRoleService.save(sysUserRole);
        }

    }


}
