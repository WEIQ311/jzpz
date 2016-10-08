package com.jzpz.repository;

import com.jzpz.domain.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by weiQiang on 2016/10/8.
 */
public interface SysRoleRepository extends JpaRepository<SysRole,Integer> {
    SysRole findById(Integer id);
}
