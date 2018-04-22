package com.mmall.common;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 利用Guava实现TokenCache
 * Created by WavyPeng on 2018/4/20.
 */
public class TokenCache {
    private static Logger logger = LoggerFactory.getLogger(TokenCache.class);

    public static final String TOKEN_PREFIX = "token_";

    //Guava中的本地缓存，LRU算法
    private static LoadingCache<String,String> localCache = CacheBuilder.newBuilder()
            .initialCapacity(1000)   //本地缓存初始化1000
            .maximumSize(10000)      //最大值10000，超过则使用LRU算法进行清除
            .expireAfterAccess(12, TimeUnit.HOURS)  //缓存有效期是12小时
            .build(new CacheLoader<String, String>() {
                //默认的数据加载实现，当调用get取值的时候，如果key没有对应的值，就调用这个方法进行加载。
                //如果key没有命中，就调用这个匿名方法进行实现
                @Override
                public String load(String s) throws Exception {
                    return "null";
                }
            });
    public static void setKey(String key,String value){
        localCache.put(key,value);
    }
    public static String getKey(String key){
        String value = null;
        try{
            value = localCache.get(key);
            if("null".equals(value)){
               return null;
            }
            return value;
        }catch (Exception e){
            logger.error("localCache get error",e);
        }
        return null;
    }
}
