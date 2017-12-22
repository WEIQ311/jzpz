package com.jzpz.repository;

import com.jzpz.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author weiQiang
 * @date 2016/10/7
 */
public interface UsersRepository extends JpaRepository<Users, Integer> {
    /**
     * 通过用户名获取用户
     *
     * @param userName
     * @return
     */
    Users findByUserName(String userName);
}
