package com.lrh.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lrh.model.system.SysRole;
import com.lrh.vo.system.AssginRoleVo;

import java.util.Map;

/**
 * @ProjectName: lrh-oa-parent
 * @Package: com.lrh.auth.service
 * @ClassName: SysRoleService
 * @Author: 63283
 * @Description: TODO
 * @Date: 2023/4/11 13:55
 */

public interface SysRoleService extends IService<SysRole> {


    /**
     * 查询所有角色和当前角色所属用户
     * @param userId
     * @return
     */
    Map<String, Object> findRoleDataByUserId(Long userId);


    /**
     * 为用户分配角色
     * @param assginRoleVo
     */
    void doAssign(AssginRoleVo assginRoleVo);
}
