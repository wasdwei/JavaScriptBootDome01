package com.example.javascriptbootdome01.Security.JDBC;

import com.example.javascriptbootdome01.Security.JDBC.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Name: wql
 * @Time: 2023-10-10  11:41
 */
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
        Customer findByUsername(String username);
}
