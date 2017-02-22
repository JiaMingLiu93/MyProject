package com.liu.design.filter;

import java.util.List;

/**
 * Created by Jam on 2017/2/22.
 */
public interface Criteria {
    public List<People> meetCriteria(List<People> persons);
}
