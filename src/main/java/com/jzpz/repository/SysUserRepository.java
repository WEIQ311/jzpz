package com.jzpz.repository;

import com.jzpz.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by weiQiang on 2016/10/7.
 */
public interface SysUserRepository extends JpaRepository<SysUser,String> {
}
