package com.lrh.auth.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lrh.model.system.SysUser;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lrh
 * @since 2023-04-18
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据id更新状态
     * @param id
     * @param status
     */
    void updateStatus(Long id, Integer status);
}
