package com.lrh.auth.service.impl;


import com.lrh.auth.mapper.SysMenuMapper;
import com.lrh.auth.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lrh.auth.utils.MenuHelper;
import com.lrh.model.system.SysMenu;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author lrh
 * @since 2023-04-19
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Override
    public List<SysMenu> findNodes() {
        //1.查询所有菜单数据
        List<SysMenu> sysMenuList = baseMapper.selectList(null);

        //2.构建成树形结构
        List<SysMenu> returnList = MenuHelper.buildTree(sysMenuList);

        return returnList;
    }
}
