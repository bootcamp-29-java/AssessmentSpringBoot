/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.component;

import com.bootcamp.cobaspringapplication.entities.Employee;
import com.bootcamp.cobaspringapplication.repositories.EmployeeRepository;
import java.util.Collection;
import javax.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author asus
 */
@Service
@Transactional
public class MyUserDetailService implements UserDetailsService{
    
    @Resource
    private EmployeeRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = repository.getByEmail(email);
        if (email == null) {
            throw new UsernameNotFoundException("User dengan email"+email+" tidak ditemukan");
        }
          return new User(employee.getEmail(), employee.getAccount().getPassword(), getAuthorities(employee));
    }
    
    private static Collection<? extends GrantedAuthority> getAuthorities(Employee user) {
        String[] userRoles = user.getEmployeeRoleList().stream().map((role) -> role.getRole().getName()).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }
    
}
