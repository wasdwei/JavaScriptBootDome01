package com.example.javascriptbootdome01.Security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Name: wql
 * @Time: 2023-10-18  22:30
 */
public interface AuthorityRepository extends JpaRepository<Authority,Integer> {
    @Query(value = "select a.* from t_customer c,t_authority a,t_customer_authority ca where ca.customer_id=c.id and ca.authority_id=a.id and c.username=?1", nativeQuery = true)
    List<Authority> findAuthoritiesByUsername(String username);

}
