package com.example.rtdataassetcoord.common;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.google.common.collect.Maps;
import java.util.Map;
import org.springframework.core.NamedThreadLocal;

public class ThreadLocalUtil {
    private static final ThreadLocal<Map<String, Object>> threadLocal = new NamedThreadLocal("xxx-threadlocal") {
        protected Map<String, Object> initialValue() {
            return Maps.newHashMap();
        }
    };

    public ThreadLocalUtil() {
    }

    public static Map<String, Object> getThreadLocal() {
        return (Map)threadLocal.get();
    }

    public static <T> T get(String key) {
        Map map = (Map)threadLocal.get();
        return (T) map.get(key);
    }

    public static <T> T get(String key, T defaultValue) {
        Map map = (Map)threadLocal.get();
        return map.get(key) == null ? defaultValue : (T) map.get(key);
    }

    public static void set(String key, Object value) {
        Map map = (Map)threadLocal.get();
        map.put(key, value);
    }

    public static void set(Map<String, Object> keyValueMap) {
        Map map = (Map)threadLocal.get();
        map.putAll(keyValueMap);
    }

    public static void remove() {
        threadLocal.remove();
    }
}
