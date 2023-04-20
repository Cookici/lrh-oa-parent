package com.lrh.auth.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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

    @Override
    public boolean removeMenuById(Long id) {
        //判断当前菜单是否有下一层菜单  先搜索所有菜单 如果parenId等于id 证明该id的菜单有子菜单
        LambdaQueryWrapper<SysMenu> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysMenu::getParentId,id);
        Integer count = baseMapper.selectCount(lambdaQueryWrapper);
        if(count > 0){
            return false;
        }else{
            baseMapper.deleteById(id);
            return true;
        }
    }
}
