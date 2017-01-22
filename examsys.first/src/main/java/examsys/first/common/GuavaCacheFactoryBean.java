package examsys.first.common;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;


import org.springframework.beans.factory.FactoryBean;
import org.springframework.cache.concurrent.ConcurrentMapCache;

import com.google.common.cache.CacheBuilder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuavaCacheFactoryBean implements FactoryBean<ConcurrentMapCache>  {
    private String name;
    private int maxSize;
    private int expirationAccessTime;
 
    private ConcurrentMap<Object, Object> store;
    private ConcurrentMapCache cache;
 
    @Override
    public ConcurrentMapCache getObject() throws Exception {
        return cache;
    }
 
    @Override
    public Class<?> getObjectType() {
        return ConcurrentMapCache.class;
    }
 
    @Override
    public boolean isSingleton() {
        return true;
    }
 
    @PostConstruct
    public void init() {
        this.store = CacheBuilder.newBuilder()
                .expireAfterAccess(expirationAccessTime, TimeUnit.MINUTES)
                .maximumSize(getMaxSize())
                .build().asMap();
        this.cache = new ConcurrentMapCache(this.getName(), this.store, true);
    }
}
