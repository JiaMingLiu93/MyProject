package com.liu.parsexml;

import lombok.Data;

import java.util.List;

/**
 * Created by jam on 2017/2/9.
 */
@Data
public class Host {
    private List<Metric> metrics;
    private String name;
    private String ip;
    private String tags;
    private String reported;
    private String tN;
    private String tMax;
    private String dMax;
    private String location;
    private String gmondStarted;
}
