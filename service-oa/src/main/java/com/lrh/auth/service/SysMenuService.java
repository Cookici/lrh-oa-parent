package com.lrh.auth.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lrh.model.system.SysMenu;
import com.lrh.vo.system.AssginMenuVo;
import com.lrh.vo.system.RouterVo;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author lrh
 * @since 2023-04-19
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 菜单列表接口
     * @return
     */
    List<SysMenu> findNodes();

    /**
     * 没有子目录才删除
     * @param id
     */
    void removeMenuById(Long id);

    /**
     * 查询所有菜单和角色分配的菜单
     * @param roleId
     * @return
     */
    List<SysMenu> findMenuByRoleId(Long roleId);

    /**
     * 角色分配菜单
     * @param assginMenuVo
     * @return
     */
    void doAssign(AssginMenuVo assginMenuVo);

    /**
     * 根据用户id获取用户可以操作的菜单列表（菜单）
     * @param userId
     * @return
     */
    List<RouterVo> findUserMenuListByUserId(Long userId);

    /**
     * 根据用户id获取用户可以操作的按钮列表（按钮）
     * @param userId
     * @return
     */
    List<String> findUserPermsByUserId(Long userId);
}
