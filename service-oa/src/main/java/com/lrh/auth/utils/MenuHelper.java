package com.lrh.auth.utils;

import com.lrh.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: lrh-oa-parent
 * @Package: com.lrh.auth.utils
 * @ClassName: MenuHelper
 * @Author: 63283
 * @Description: TODO
 * @Date: 2023/4/20 13:30
 */

public class MenuHelper {

    /**
     * 递归方式构建菜单
     *
     * @param sysMenuList
     * @return
     */
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        //创建List集合保存最终数据
        List<SysMenu> trees = new ArrayList<>();
        //把所有的菜单数据进行遍历
        for (SysMenu sysMenu : sysMenuList) {
            //递归的入口
            //parentId=0是入口
            if (sysMenu.getParentId().longValue() == 0) {
                trees.add(getChildren(sysMenu, sysMenuList));
            }
        }
        return trees;
    }

    public static SysMenu getChildren(SysMenu sysMenu, List<SysMenu> sysMenuList) {
        sysMenu.setChildren(new ArrayList<SysMenu>());
        //遍历所有菜单数据，判断id和parentId对应的关系
        for (SysMenu menu : sysMenuList) {
            if (sysMenu.getId().longValue() == menu.getParentId().longValue()) {
                if(sysMenu.getChildren() == null){
                    sysMenu.setChildren(new ArrayList<SysMenu>());
                }
                sysMenu.getChildren().add(getChildren(menu, sysMenuList));
            }
        }
        return sysMenu;
    }
}
