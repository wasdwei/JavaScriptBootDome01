package com.example.javascriptbootdome01.SQL.jpa;//package com.example.javascriptbootdome01.SQL.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * @Author: wql                   //作者
 * @CreateTime: 2023-09-25  18:43  //时间
 */
@Service//标记当前类是一个service类，加上该注解会将当前类自动注入到spring容器中，不需要再在applicationContext.xml文件定义bean了。
public class DiscussService {
    @Autowired
    private DiscussRepository discussRepository;

    //根据id查询评论信息
    @Cacheable(cacheNames = "comment", unless = "#result==null")// 表示查询结果为空不进行缓存,将运行结果缓存，以后查询相同的数据，直接从缓存中取，不需要调用方法。
    public Discuss findById(int comment_id) {
        Optional<Discuss> optional = discussRepository.findById(comment_id);
        //判断optional是否存在
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @CachePut(cacheNames = "comment", key = "#result.id")//将运行结果更新
    public Discuss updateDiscuss(Discuss discuss) {
        discussRepository.updateDiscuss(discuss.getAuthor(), discuss.getId());
        return discuss;
    }

    @CacheEvict(cacheNames = "comment")//将redis服务中的comment删除
    public void deleteDiscuss(int comment_id) {
        discussRepository.deleteById(comment_id);
    }


}
