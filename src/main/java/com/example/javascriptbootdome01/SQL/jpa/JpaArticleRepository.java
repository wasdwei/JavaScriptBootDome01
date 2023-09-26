//package com.example.javascriptbootdome01.SQL.jpa;
//
//import com.example.javascriptbootdome01.SQL.Article;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
///**
// * @Author: wql                   //作者
// * @CreateTime: 2023-09-22  18:43  //时间
// */
//public interface JpaArticleRepository extends JpaRepository<JpaArticle, Integer> {
//        //1.查询author非空的JpaArticle评论合集
////        public List<JpaArticle> findByAuthorNotNull2();
//
//        //2.根据文章id分页查询JpaArticle评论合集
//        @Query("select c from t_article c where c.id = ?1")
//        public List<JpaArticle> getJpaArticlePaged(Integer id, Pageable pageable);
//
//        //3.使用元素SQL语句，根据文章id分页查询JpaArticle评论合集
//        @Query(value = "SELECT * FROM t_article  WHERE  Id = ?1", nativeQuery = true)
//        public List<JpaArticle> getJpaArticlePaged2(Integer id, Pageable pageable);
//
//        //4、根据评论id修改评论作者author
//        @Transactional
//        @Modifying
//        @Query("UPDATE t_article c SET c.title = ?1 WHERE  c.id = ?2")
//        public int updateJpaArticle(String author, Integer id);
//
//        //5、根据评论id删除评论
//        @Transactional
//        @Modifying
//        @Query("DELETE t_article c WHERE  c.id = ?1")
//        public int deleteJpaArticle(Integer id);
//}
