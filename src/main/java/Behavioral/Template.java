package Behavioral;


import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.HashMap;
import java.util.Map;

/*
 模版模式
 定义一个操作中的算法的骨架，而将一些步骤延迟到子类中。模板方法使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤
 */
public class Template {
    public static void main(String[] args) {
        AbstractSetting setting = new LocalSetting();
        setting.putIntoCache("key", "value");
        System.out.println(setting.lookupCache("key"));
    }
}

abstract class AbstractSetting {
    public final String getSetting(String key) {
        String value = lookupCache(key);
        if (value == null) {
            value = readFromDatabase(key);
            putIntoCache(key, value);
        }
        return value;
    }

    private String readFromDatabase(String key) {
        return key + "tt";
    }

    public abstract String lookupCache(String key);

    public abstract void putIntoCache(String key, String value);

}

class LocalSetting extends AbstractSetting {
    private Map<String, String> cache = new HashMap<>();

    @Override
    public String lookupCache(String key) {
        return cache.get(key);
    }

    @Override
    public void putIntoCache(String key, String value) {
        cache.put(key, value);
    }
}

class RedisSetting extends AbstractSetting {
    private RedisClient client = RedisClient.create();


    @Override
    public String lookupCache(String key) {
        try (StatefulRedisConnection<String, String> conn = client.connect()) {
            RedisCommands<String, String> commands = conn.sync();
            return commands.get(key);
        }
    }

    @Override
    public void putIntoCache(String key, String value) {
        try (StatefulRedisConnection<String, String> conn = client.connect()) {
            RedisCommands<String, String> commands = conn.sync();
            commands.set(key, value);
        }
    }
}