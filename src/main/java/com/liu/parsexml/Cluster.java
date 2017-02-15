package com.liu.parsexml;

import lombok.Data;

import java.util.List;

/**
 * Created by jam on 2017/2/9.
 */
@Data
public class Cluster {
    private List<Host> hosts;
    private String name;
    private String localtime;
    private String owner;
    private String latLong;
    private String url;
}
