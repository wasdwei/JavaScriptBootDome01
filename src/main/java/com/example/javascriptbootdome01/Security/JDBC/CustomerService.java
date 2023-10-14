package com.example.javascriptbootdome01.Security.JDBC;

import com.example.javascriptbootdome01.Security.JDBC.Authority;
import com.example.javascriptbootdome01.Security.JDBC.AuthorityRepository;
import com.example.javascriptbootdome01.Security.JDBC.Customer;
import com.example.javascriptbootdome01.Security.JDBC.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wql
 * @date 2023/10/10 0010 🐷   16:33
 * @description:
 */
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private RedisTemplate redisTemplate;

    public Customer getCustomer(String username) {
        Customer customer = null;
        Object o = redisTemplate.opsForValue().get("customer_" + username);
        if (o != null) {
            customer = (Customer) o;
        } else {
            customer = customerRepository.findByUsername(username);
            if (customer != null) {
                redisTemplate.opsForValue().set("customer_" + username, customer);
            }
        }
        return customer;
    }

    //业务控制：使用唯一用户权限
    public List<Authority> getCustomerAuthority(String username) {
        List<Authority> authorities = null;
        Object o = redisTemplate.opsForValue().get("authorities_" + username);
        if (o != null) {
            authorities = (List<Authority>) o;
        } else {
            authorities = authorityRepository.findAuthoritiesByUsername(username);
            if (authorities.size() > 0) {
                redisTemplate.opsForValue().set("authorities_" + username, authorities);
            }
        }
        return authorities;
    }
}
