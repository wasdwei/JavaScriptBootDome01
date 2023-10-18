package com.example.javascriptbootdome01.Security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * @Name: wql
 * @Time: 2023-10-18  22:30
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    //    可以直接使用JPA生成的实现
//    @Query(value = "select c.* from t_customer c where c.username=?1", nativeQuery = true)
    Customer findByUsername(String username);

    @Query("select c from t_customer c where c.username = ?1")
    public Customer findById2(String username);

    @Transactional
    @Modifying//JPA会以更新类语句来执行，而不再是以查询语句执行。
    @Query("UPDATE t_customer c SET c.username = ?1 WHERE  c.id = ?2")
    public int updateUser(String username, Integer id);



    //5、根据评论id删除评论
    @Transactional
    @Modifying
    @Query("DELETE t_customer c WHERE  c.id = ?1")
    public Customer deleteUser(Integer id);

}
