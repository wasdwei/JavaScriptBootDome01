package com.example.javascriptbootdome01.SQL.jpa;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @Author: wql                   //作者
 * @CreateTime: 2023-09-22  18:43  //时间
 */

@RestController
public class JpaController {
//    @Autowired
//    private JpaArticleService jpaArticleService;
    @Autowired
    private DiscussService discussService;

    //t_comment表
//查询数据操作：
    @GetMapping("/cget/{id}")
    public Discuss findById(@PathVariable("id") int comment_id) {
        Discuss discuss = discussService.findById(comment_id);
        return discuss;
    }
//
    @GetMapping("/cupdate/{id}/{author}")
    public Discuss updateDiscuss(@PathVariable("id") int comment_id,
                                 @PathVariable("author") String author) {
        Discuss discuss = discussService.findById(comment_id);
        discuss.setAuthor(author);
        Discuss updateDiscuss = discussService.updateDiscuss(discuss);
        return updateDiscuss;
    }
//删除数据操作：
    @GetMapping("/cdelete/{id}")
    public void deletaDiscuss(@PathVariable("id") int comment_id) {
        discussService.deleteDiscuss(comment_id);
    }


    //t_article表
//    @GetMapping("/aget/{id}")
//    public JpaArticle findById2(@PathVariable("id") int article_id) {
//        JpaArticle jpaArticle = jpaArticleService.findById2(article_id);
//        return jpaArticle;
//    }
//
//    @GetMapping("/aupdate/{id}/{author}")
//    public JpaArticle updateJpaArticle(@PathVariable("id") int article_id,
//                                 @PathVariable("author") String title) {
//        JpaArticle jpaArticle=jpaArticleService.findById2(article_id);
//        jpaArticle.setTitle(title);
//        JpaArticle updateJpaArticle=jpaArticleService.updateJpaArticle(jpaArticle);
//        return updateJpaArticle;
//    }
//
//    @GetMapping("/adelete/{id}")
//    public void deleta(@PathVariable("id") int article_id) {
//        jpaArticleService.deleteJpaArticle(article_id);
//    }

}
