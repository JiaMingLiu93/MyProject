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
public class HostMonitorInfo {

    public static final String LOAD_FIFTEEN = "load_fifteen";
    public static final String LOAD_ONE = "load_one";
    public static final String LOAD_FIVE = "load_FIVE";
    public static final String CPU_IDLE = "cpu_idle";
    public static final String CPU_USER = "cpu_user";
    public static final String CPU_SYSTEM = "cpu_system";
    public static final String CPU_WIO = "cpu_wio";
    public static final String MEM_TOTAL = "mem_total";
    public static final String SWAP_FREE = "swap_free";
    public static final String BYTES_IN = "bytes_in";
    public static final String BYTES_OUT = "bytes_out";
    public static final String DISK_TOTAL = "disk_total";
    public static final String DISK_FREE = "disk_free";
    public static final String MEM_FREE = "mem_free";
    public static final String SWAP_TOTAL = "swap_total";

    private float loadOne;
    private float loadFive;
    private float loadFifteen;
    private float cpuIdle;
    private float cpuUser;
    private float cpuSystem;
    private float cpuWio;
    private float memTotal;
    private float memFree;
    private float swapTotal;
    private float swapFree;
    private float bytesIn;
    private float bytesOut;
    private double diskTotal;
    private double diskFree;
    private String ip;

    public static HostMonitorInfo getInstanceOfMap(Map<String,String> map){
        return HostMonitorInfo.builder()
                .bytesIn(Float.valueOf(map.get(BYTES_IN)==null?"0":map.get(BYTES_IN)))
                .bytesOut(Float.valueOf(map.get(BYTES_OUT)==null?"0":map.get(BYTES_OUT)))
                .cpuIdle(Float.valueOf(map.get(CPU_IDLE)==null?"0":map.get(CPU_IDLE)))
                .cpuSystem(Float.valueOf(map.get(CPU_SYSTEM)==null?"0":map.get(CPU_SYSTEM)))
                .cpuUser(Float.valueOf(map.get(CPU_USER)==null?"0":map.get(CPU_USER)))
                .cpuWio(Float.valueOf(map.get(CPU_WIO)==null?"0":map.get(CPU_WIO)))
                .diskFree(Double.valueOf(map.get(DISK_FREE)==null?"0":map.get(DISK_FREE)))
                .diskTotal(Double.valueOf(map.get(DISK_TOTAL)==null?"0":map.get(DISK_TOTAL)))
                .memFree(Float.valueOf(map.get(MEM_FREE)==null?"0":map.get(MEM_FREE)))
                .memTotal(Float.valueOf(map.get(MEM_TOTAL)==null?"0":map.get(MEM_TOTAL)))
                .swapFree(Float.valueOf(map.get(SWAP_FREE)==null?"0":map.get(SWAP_FREE)))
                .swapTotal(Float.valueOf(map.get(SWAP_TOTAL)==null?"0":map.get(SWAP_TOTAL)))
                .loadFifteen(Float.valueOf(map.get(LOAD_FIFTEEN)==null?"0":map.get(LOAD_FIFTEEN)))
                .loadFive(Float.valueOf(map.get(LOAD_FIVE)==null?"0":map.get(LOAD_FIVE)))
                .loadOne(Float.valueOf(map.get(LOAD_ONE)==null?"0":map.get(LOAD_ONE)))
                .ip(map.get(XmlParse.HOST_IP)==null?"null":map.get(XmlParse.HOST_IP))
                .build();

    }
}
