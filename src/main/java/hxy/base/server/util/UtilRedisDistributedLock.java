//package hxy.base.server.util;
//
//import com.google.common.collect.Lists;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisCallback;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.Jedis;
//
//import java.util.Collections;
//
///**
// * yj.utils
// * UtilRedisDistributedLock
// *
// * @author ykk
// * @date 2021/1/18
// * @desc
// */
//@Component
//public class UtilRedisDistributedLock {
//    protected static Logger logger = LoggerFactory.getLogger(UtilRedisDistributedLock.class);
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    public static final Long RELEASE_SUCCESS = 1L;
//    public static final Long POSTPONE_SUCCESS = 1L;
//
//    private static final String LOCK_SUCCESS = "OK";
//    private static final String SET_IF_NOT_EXIST = "NX";
//    private static final String SET_WITH_EXPIRE_TIME = "EX";
//    // 解锁脚本(lua)
//    private static final String RELEASE_LOCK_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
//    // 延时脚本
//    private static final String POSTPONE_LOCK_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('expire', KEYS[1], ARGV[2]) else return '0' end";
//
//    /**
//     * 分布式锁
//     *          // SET IF NOT EXIST，而且还是原子的
//     *
//     * @param key
//     * @param value
//     * @param expireTime 单位: 秒
//     * @return
//     */
//    public boolean lock(String key, String value, long expireTime) {
//        // 加锁
//        Boolean locked = (Boolean) redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
//            Jedis jedis = (Jedis) redisConnection.getNativeConnection();
//            String result = jedis.set(key, value, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
//            if (LOCK_SUCCESS.equals(result)) {
//                return Boolean.TRUE;
//            }
//            return Boolean.FALSE;
//        });
//
//        return locked;
//    }
//
//    /**
//     * 解锁
//     * @param key
//     * @param value
//     * @return
//     */
//    public Boolean unLock(String key, String value) {
//        return (Boolean) redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
//
//            Jedis jedis = (Jedis) redisConnection.getNativeConnection();
//            Object result = jedis.eval(RELEASE_LOCK_SCRIPT, Collections.singletonList(key), Collections.singletonList(value));
//            if (RELEASE_SUCCESS.equals(result)) {
//                return Boolean.TRUE;
//            }
//            return Boolean.FALSE;
//        });
//    }
//
//    /**
//     * 锁延时
//     * @param key
//     * @param value
//     * @param expireTime
//     * @return
//     */
//    public Boolean postpone(String key, String value, long expireTime) {
//        return (Boolean) redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
//            Jedis jedis = (Jedis) redisConnection.getNativeConnection();
//            Object result = jedis.eval(POSTPONE_LOCK_SCRIPT, Lists.newArrayList(key), Lists.newArrayList(value, String.valueOf(expireTime)));
//            if (POSTPONE_SUCCESS.equals(result)) {
//                return Boolean.TRUE;
//            }
//            return Boolean.FALSE;
//        });
//    }
//}
//package hxy.base.server.util;
//
//import com.google.common.collect.Lists;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisCallback;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.Jedis;
//
//import java.util.Collections;
//
///**
// * yj.utils
// * UtilRedisDistributedLock
// *
// * @author ykk
// * @date 2021/1/18
// * @desc
// */
//@Component
//public class UtilRedisDistributedLock {
//    protected static Logger logger = LoggerFactory.getLogger(UtilRedisDistributedLock.class);
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    public static final Long RELEASE_SUCCESS = 1L;
//    public static final Long POSTPONE_SUCCESS = 1L;
//
//    private static final String LOCK_SUCCESS = "OK";
//    private static final String SET_IF_NOT_EXIST = "NX";
//    private static final String SET_WITH_EXPIRE_TIME = "EX";
//    // 解锁脚本(lua)
//    private static final String RELEASE_LOCK_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
//    // 延时脚本
//    private static final String POSTPONE_LOCK_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('expire', KEYS[1], ARGV[2]) else return '0' end";
//
//    /**
//     * 分布式锁
//     *          // SET IF NOT EXIST，而且还是原子的
//     *
//     * @param key
//     * @param value
//     * @param expireTime 单位: 秒
//     * @return
//     */
//    public boolean lock(String key, String value, long expireTime) {
//        // 加锁
//        Boolean locked = (Boolean) redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
//            Jedis jedis = (Jedis) redisConnection.getNativeConnection();
//            String result = jedis.set(key, value, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
//            if (LOCK_SUCCESS.equals(result)) {
//                return Boolean.TRUE;
//            }
//            return Boolean.FALSE;
//        });
//
//        return locked;
//    }
//
//    /**
//     * 解锁
//     * @param key
//     * @param value
//     * @return
//     */
//    public Boolean unLock(String key, String value) {
//        return (Boolean) redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
//
//            Jedis jedis = (Jedis) redisConnection.getNativeConnection();
//            Object result = jedis.eval(RELEASE_LOCK_SCRIPT, Collections.singletonList(key), Collections.singletonList(value));
//            if (RELEASE_SUCCESS.equals(result)) {
//                return Boolean.TRUE;
//            }
//            return Boolean.FALSE;
//        });
//    }
//
//    /**
//     * 锁延时
//     * @param key
//     * @param value
//     * @param expireTime
//     * @return
//     */
//    public Boolean postpone(String key, String value, long expireTime) {
//        return (Boolean) redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
//            Jedis jedis = (Jedis) redisConnection.getNativeConnection();
//            Object result = jedis.eval(POSTPONE_LOCK_SCRIPT, Lists.newArrayList(key), Lists.newArrayList(value, String.valueOf(expireTime)));
//            if (POSTPONE_SUCCESS.equals(result)) {
//                return Boolean.TRUE;
//            }
//            return Boolean.FALSE;
//        });
//    }
//}
