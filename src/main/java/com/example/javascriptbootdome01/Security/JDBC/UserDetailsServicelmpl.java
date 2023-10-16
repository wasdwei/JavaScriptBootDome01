package com.example.javascriptbootdome01.Security.JDBC;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Name: wql
 * @Time: 2023-10-11  11:41
 */
@Service
public class UserDetailsServicelmpl implements UserDetailsService {


    @Autowired
    private CustomerService customerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        获取用户信息
        Customer customer = customerService.getCustomer(username);
//        获取权限
        List<Authority> authorityList = customerService.getCustomerAuthority(username);
//        信息权限封装
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = authorityList.stream()
                .map(authority -> new SimpleGrantedAuthority(authority
                        .getAuthority())).collect(Collectors.toList());
//       返回的封装的userdatails用户详情类
        if (customer != null) {
            UserDetails userDetails = new User(customer.getUsername(), customer.getPassword(), simpleGrantedAuthorityList);
            return userDetails;
        } else {
            throw new UsernameNotFoundException("用户不存在");
        }
    }
}