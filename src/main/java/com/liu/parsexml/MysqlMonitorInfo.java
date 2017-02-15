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
public class MysqlMonitorInfo {
    public static final String MYSQL_THREADS_CONNECTED = "mysql_threads_connected";
    public static final String MYSQL_INNODB_DATA_WRITES = "mysql_innodb_data_writes";
    public static final String MYSQL_INNODB_DATA_READS = "mysql_innodb_data_reads";
    public static final String MYSQL_INNODB_BUFFER_POOL_PAGES_FREE = "mysql_innodb_buffer_pool_pages_free";
    public static final String MYSQL_INNODB_BUFFER_POOL_PAGES_TOTAL = "mysql_innodb_buffer_pool_pages_total";
    public static final String MYSQL_TABLE_LOCKS_WAITED = "mysql_table_locks_waited";
    public static final String MYSQL_MAX_CONNECTIONS = "mysql_max_connections";

    private int threadsConnected;
    private float tableLocksWaited;
    private int maxConnections;
    private int innodbBufferPoolPagesTotal;
    private int innodbBufferPoolPagesFree;
    private float innodbDataReads;
    private float innodbDataWrites;
    private String ip;

    public static MysqlMonitorInfo getInstanceOfMap(Map<String, String> map) {
        return MysqlMonitorInfo.builder()
                .innodbBufferPoolPagesFree(Integer.valueOf(map.get(MYSQL_INNODB_BUFFER_POOL_PAGES_FREE)==null?"0":map.get(MYSQL_INNODB_BUFFER_POOL_PAGES_FREE)))
                .innodbBufferPoolPagesTotal(Integer.valueOf(map.get(MYSQL_INNODB_BUFFER_POOL_PAGES_TOTAL)==null?"0":map.get(MYSQL_INNODB_BUFFER_POOL_PAGES_TOTAL)))
                .innodbDataReads(Float.valueOf(map.get(MYSQL_INNODB_DATA_READS)==null?"0":map.get(MYSQL_INNODB_DATA_READS)))
                .innodbDataWrites(Float.valueOf(map.get(MYSQL_INNODB_DATA_WRITES)==null?"0":map.get(MYSQL_INNODB_DATA_WRITES)))
                .threadsConnected(Integer.valueOf(map.get(MYSQL_THREADS_CONNECTED)==null?"0":map.get(MYSQL_THREADS_CONNECTED)))
                .tableLocksWaited(Float.valueOf(map.get(MYSQL_TABLE_LOCKS_WAITED)==null?"0":map.get(MYSQL_TABLE_LOCKS_WAITED)))
                .maxConnections(Integer.valueOf(map.get(MYSQL_MAX_CONNECTIONS)==null?"0":map.get(MYSQL_MAX_CONNECTIONS)))
                .ip(map.get(XmlParse.HOST_IP)==null?"null":map.get(XmlParse.HOST_IP))
                .build();
    }
}
