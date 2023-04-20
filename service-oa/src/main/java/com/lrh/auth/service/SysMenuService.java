package com.lrh.auth.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lrh.model.system.SysMenu;
import com.lrh.vo.system.AssginMenuVo;

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
    boolean removeMenuById(Long id);

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

}
