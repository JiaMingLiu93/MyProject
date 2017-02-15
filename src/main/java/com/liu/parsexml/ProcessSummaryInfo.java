package com.liu.parsexml;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by jam on 2017/2/13.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessSummaryInfo {
    public static final String GENERIC_THREADS = "_generic_threads";
    public static final String GENERIC_TCP_CONNECTS = "_generic_tcp_connects";
    public static final String GENERIC_MEMORY = "_generic_memory";
    public static final String GENERIC_CPU = "_generic_cpu";
    public static final String GENERIC_UPTIME = "_generic_uptime";

    public static String[] prefixs;

    public static final String CONFIGURE_PROPERTIES = "configure.properties";

    public static final String PROCESS_SUMMARY_INFO_PREFIX = "process_summary_info_prefix";

    static {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(CONFIGURE_PROPERTIES);
            properties.load(fileInputStream);
            String prefix = properties.getProperty(PROCESS_SUMMARY_INFO_PREFIX);
            prefixs = prefix.split(",");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String name;
    private int genericThreads;
    private int genericTcpConnects;
    private int genericMemory;
    private int genericCpu;
    private int genericUptime;
    private String ip;

    public static List<ProcessSummaryInfo> getInstanceOfMap(Map<String, String> map) {
        ArrayList<ProcessSummaryInfo> processSummaryInfos = Lists.newArrayList();
        Arrays.asList(prefixs).forEach(prefix->{
            ProcessSummaryInfo processSummaryInfo = ProcessSummaryInfo.builder()
                    .name(prefix)
                    .genericThreads(Integer.valueOf(map.get(prefix + GENERIC_THREADS) == null ? "0" : map.get(prefix + GENERIC_THREADS)))
                    .genericTcpConnects(Integer.valueOf(map.get(prefix + GENERIC_TCP_CONNECTS) == null ? "0" : map.get(prefix + GENERIC_TCP_CONNECTS)))
                    .genericMemory(Integer.valueOf(map.get(prefix + GENERIC_MEMORY) == null ? "0" : map.get(prefix + GENERIC_MEMORY)))
                    .genericCpu(Integer.valueOf(map.get(prefix + GENERIC_CPU) == null ? "0" : map.get(prefix + GENERIC_CPU)))
                    .genericUptime(Integer.valueOf(map.get(prefix + GENERIC_UPTIME) == null ? "0" : map.get(prefix + GENERIC_UPTIME)))
                    .ip(map.get(XmlParse.HOST_IP) == null ? "null" : map.get(XmlParse.HOST_IP))
                    .build();
            processSummaryInfos.add(processSummaryInfo);

        });

        return processSummaryInfos;
    }
}
