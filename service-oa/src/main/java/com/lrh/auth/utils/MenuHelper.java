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
     * @param sysMenuList
     * @return
     */
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        //创建List集合保存最终数据
        List<SysMenu> trees = new ArrayList<>();
        //把所有的菜单数据进行遍历
        for(SysMenu sysMenu:sysMenuList){

        }

    }
}
