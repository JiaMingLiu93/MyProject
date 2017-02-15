package com.liu.parsexml;

import lombok.Data;

import java.util.List;

/**
 * Created by jam on 2017/2/9.
 */
@Data
public class Ganglia {
    private List<Cluster> clusters;
    private String version;
    private String source;
}
