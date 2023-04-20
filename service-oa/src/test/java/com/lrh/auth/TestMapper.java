package com.lrh.auth;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lrh.auth.mapper.SysRoleMapper;
import com.lrh.model.system.SysRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * @ProjectName: lrh-oa-parent
 * @Package: com.lrh.auth
 * @ClassName: TestMapper
 * @Author: 63283
 * @Description: TODO
 * @Date: 2023/4/11 11:29
 */

@SpringBootTest
public class TestMapper {

    @Autowired
    private SysRoleMapper sysRoleMapper;


    @Test
    public void getAll() {
        List<SysRole> sysRoles = sysRoleMapper.selectList(null);
        sysRoles.forEach(System.out::println);
    }

    @Test
    public void insert() {
        SysRole role = new SysRole();
        role.setRoleName("角色管理员");
        role.setRoleCode("role");
        role.setDescription("3");
        int insert = sysRoleMapper.insert(role);
        System.out.println("insert: " + insert);
        System.out.println(role);
    }

    @Test
    public void update() {
        SysRole role = sysRoleMapper.selectById(9);
        role.setRoleName("yjl砂壁玩意");
        int rows = sysRoleMapper.updateById(role);
        System.out.println("rows=" + rows);
    }

    @Test
    public void delete() {
        int rows = sysRoleMapper.deleteById(9);
        System.out.println("rows=" + rows);
    }

    @Test
    public void deleteBatch() {
        int rows = sysRoleMapper.deleteBatchIds(Arrays.asList(8, 9));
        System.out.println("rows=" + rows);
    }

    @Test
    public void testQuery() {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_name", "用户").and(
                condition -> condition.eq("role_code", "role")
                        .or()
                        .eq("description", "3")
        );
        SysRole role = sysRoleMapper.selectOne(queryWrapper);
        System.out.println(role);
    }


}
