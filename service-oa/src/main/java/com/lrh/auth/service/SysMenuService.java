package com.lrh.auth.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lrh.model.system.SysMenu;

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
}
