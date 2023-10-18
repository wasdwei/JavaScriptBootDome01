package com.example.javascriptbootdome01.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Name: wql
 * @Time: 2023-10-18  22:30
 */
//对用户数据结合Redis缓存进行业务处理
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private RedisTemplate redisTemplate;

//    @Cacheable(cacheNames = "customer", unless = "#result==null")// 表示查询结果为空不进行缓存,将运行结果缓存，以后查询相同的数据，直接从缓存中取，不需要调用方法。
    public Customer findById(String name) {
        Customer optional = customerRepository.findById2(name);
        //判断optional是否存在
//        if (optional.isPresent()) {
//            return optional.get();
//        }
        return optional;
    }

//    @CachePut(cacheNames = "customer", key = "#result.id")//将运行结果更新
//    public Customer updateUser(String name ,Integer id) {
//       Customer customer= customerRepository.updateUser(name,id);
//        return customer;
//    }

    public Customer updateUser(String name, Integer id) {
        // 调用 UserRepository 中定义的 updateUser 方法来更新用户信息
        // 这个方法需要一个新的用户名和一个用户ID作为参数
        // 它将返回被更新的行数
        int updated = customerRepository.updateUser(name, id);

        // 检查是否有行被更新（即 updateUser 方法返回的值是否大于0）
        if (updated > 0) {
            // 如果更新成功，那么使用 findById 方法获取并返回更新后的 Customer 对象
            // 如果找不到匹配的ID，那么 findById 方法将返回一个空的 Optional 对象
            // 在这种情况下，orElse 方法将返回 null
            return customerRepository.findById(id).orElse(null);
        } else {
            // 如果没有任何行被更新（例如，没有找到匹配的ID），那么返回 null
            return null;
        }
    }

    //    使用用户名查询用户信息
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


    //业务控制：使用唯一用户名查询权限
    public List<Authority> getCustomerAuthority(String username) {
        // 初始化一个Authority对象列表
        List<Authority> authorities = null;

        // 从Redis缓存中尝试获取Authority对象列表，键为"authorities_"与username的拼接
        Object o = redisTemplate.opsForValue().get("authorities_" + username);

        // 如果在缓存中找到了Authority对象列表（即，它不为null）
        if (o != null) {
            // 则将其赋值给authorities
            authorities = (List<Authority>) o;
        } else {
            // 如果在缓存中没有找到Authority对象列表（即，它为null）
            // 则尝试从repository（可能是数据库）中获取Authority对象列表
            authorities = authorityRepository.findAuthoritiesByUsername(username);

            // 如果在repository中找到了Authority对象列表
            if (authorities.size() > 0) {
                // 则将其存入Redis缓存，键为"authorities_"与username的拼接
                redisTemplate.opsForValue().set("authorities_" + username, authorities);
            }
        }

        // 返回Authority对象列表
        return authorities;
    }

}
