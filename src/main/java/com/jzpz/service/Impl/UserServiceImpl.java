package com.jzpz.service.Impl;

import com.jzpz.domain.Users;
import com.jzpz.repository.UsersRepository;
import com.jzpz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by weiQiang on 2016/10/7.
 */
@Service
public class UserServiceImpl implements UserService,UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;
    @Override
    public Users getUserByname(String username) {
        return Users.builder().userName("admin").passWord("admin").build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + " not found");
        }
        System.err.println(user.getRole() + "正在执行查询角色名称");
        return new UserDetails() {
            private static final long serialVersionUID = 3720901165271071386L;
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<SimpleGrantedAuthority> auths = new ArrayList<>();
                auths.add(new SimpleGrantedAuthority(user.getRole()));
                return auths;
            }

            @Override
            public String getPassword() {
                return user.getPassWord();
            }

            @Override
            public String getUsername() {
                return username;
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
    }
}
