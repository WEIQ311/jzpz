package com.jzpz.repository;

import com.jzpz.domain.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author weiQiang
 * @date 2016/10/8
 */
public interface SysRoleRepository extends JpaRepository<SysRole, Integer> {

    /**
     * 通过id获取角色
     *
     * @param id
     * @return
     */
    SysRole findById(Integer id);
}
