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
public class ZookeeperMonitorInfo {

    public static final String ZOOKEEPER_MODE = "zookeeper_mode";
    public static final String ZOOKEEPER_OUTSTANDING_REQUEST = "zookeeper_outstanding_request";
    public static final String ZOOKEEPER_NODES = "zookeeper_nodes";
    public static final String ZOOKEEPER_CONNECTIONS = "zookeeper_connections";

    private String mode;
    private int outstandingRequest;
    private int nodes;
    private int connections;
    private String ip;

    public static ZookeeperMonitorInfo getInstanceOfMap(Map<String, String> map) {
        return ZookeeperMonitorInfo.builder()
                .mode(map.get(ZOOKEEPER_MODE)==null?"null":map.get(ZOOKEEPER_MODE))
                .outstandingRequest(Integer.valueOf(map.get(ZOOKEEPER_OUTSTANDING_REQUEST)==null?"0":map.get(ZOOKEEPER_OUTSTANDING_REQUEST)))
                .nodes(Integer.valueOf(map.get(ZOOKEEPER_NODES)==null?"0":ZOOKEEPER_NODES))
                .connections(Integer.valueOf(map.get(ZOOKEEPER_CONNECTIONS)==null?"0":map.get(ZOOKEEPER_CONNECTIONS)))
                .ip(map.get(XmlParse.HOST_IP)==null?"null":map.get(XmlParse.HOST_IP))
                .build();
    }
}
