package com.jzpz.repository;

import com.jzpz.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by weiQiang on 2016/10/7.
 */
public interface UsersRepository extends JpaRepository<Users,Integer>{
    Users findByUserName(String userName);
}
