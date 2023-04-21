package com.lrh.auth.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lrh.model.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author lrh
 * @since 2023-04-19
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据userId查询用户对应的菜单列表
     * @param userId
     * @return
     */
    List<SysMenu> findMenuListByUserId(@Param("userId") Long userId);
}
