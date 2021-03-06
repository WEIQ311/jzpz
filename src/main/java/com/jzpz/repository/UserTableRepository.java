package com.jzpz.repository;

import com.jzpz.domain.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author weiQiang
 * @date 2016/10/10
 */
public interface UserTableRepository extends JpaRepository<UserTable, Integer> {
}
