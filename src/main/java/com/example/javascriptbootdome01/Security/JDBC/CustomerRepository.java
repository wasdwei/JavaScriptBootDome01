package com.example.javascriptbootdome01.Security.JDBC;

import com.example.javascriptbootdome01.Security.JDBC.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wql
 * @date 2023/10/10 0010 🐷   16:33
 * @description:
 */
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
        Customer findByUsername(String username);
}
