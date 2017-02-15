package com.liu.parsexml;

import lombok.Data;

/**
 * Created by jam on 2017/2/9.
 */
@Data
public class Metric {
    private ExtraData extraData;
    private String slope;
    private String dMax;
    private String tMax;
    private String tN;
    private String units;
    private String type;
    private String val;
    private String name;
}
