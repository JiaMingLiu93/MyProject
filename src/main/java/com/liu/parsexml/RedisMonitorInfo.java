package com.liu.parsexml;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Created by jam on 2017/2/9.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RedisMonitorInfo {
    public static final String REDIS_REJECTED_CONNECTIONS = "redis_rejected_connections";
    public static final String REDIS_ROLE = "redis_role";
    public static final String REDIS_EXPIRED_KEYS = "redis_expired_keys";
    public static final String REDIS_USED_MEMORY_RSS = "redis_used_memory_rss";
    public static final String REDIS_USED_MEMORY = "redis_used_memory";
    public static final String REDIS_BLOCKED_CLIENTS = "redis_blocked_clients";
    public static final String REDIS_CONNECTED_SLAVES = "redis_connected_slaves";
    public static final String REDIS_CONNECTED_CLIENTS = "redis_connected_clients";

    private int rejectedConnections;
    private String role;
    private int expiredKeys;
    private int usedMemoryRss;
    private int usedMemory;
    private int blockedClients;
    private int connectedSlaves;
    private int connectedClients;
    private String ip;

    public static RedisMonitorInfo getInstanceOfMap(Map<String, String> map) {
        return RedisMonitorInfo.builder()
                .rejectedConnections(Integer.valueOf(map.get(REDIS_REJECTED_CONNECTIONS)==null?"0":map.get(REDIS_REJECTED_CONNECTIONS)))
                .role(map.get(REDIS_ROLE)==null?"":map.get(REDIS_ROLE))
                .expiredKeys(Integer.valueOf(map.get(REDIS_EXPIRED_KEYS)==null?"0":map.get(REDIS_EXPIRED_KEYS)))
                .usedMemoryRss(Integer.valueOf(map.get(REDIS_USED_MEMORY_RSS)==null?"0":map.get(REDIS_USED_MEMORY_RSS)))
                .usedMemory(Integer.valueOf(map.get(REDIS_USED_MEMORY)==null?"0":map.get(REDIS_USED_MEMORY)))
                .blockedClients(Integer.valueOf(map.get(REDIS_BLOCKED_CLIENTS)==null?"0":map.get(REDIS_BLOCKED_CLIENTS)))
                .connectedSlaves(Integer.valueOf(map.get(REDIS_CONNECTED_SLAVES)==null?"0":map.get(REDIS_CONNECTED_SLAVES)))
                .connectedClients(Integer.valueOf(map.get(REDIS_CONNECTED_CLIENTS)==null?"0":map.get(REDIS_CONNECTED_CLIENTS)))
                .ip(map.get(XmlParse.HOST_IP)==null?"null":map.get(XmlParse.HOST_IP))
                .build();
    }
}
