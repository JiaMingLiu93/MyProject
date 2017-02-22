package com.liu.design.filter;

import java.util.List;

/**
 * Created by Jam on 2017/2/22.
 */
public class OrCriteria implements Criteria {private Criteria criteria;
    private Criteria otherCriteria;

    public OrCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }
    @Override
    public List<People> meetCriteria(List<People> persons) {
        List<People> firstCriteriaItems = criteria.meetCriteria(persons);
        List<People> otherCriteriaItems = otherCriteria.meetCriteria(persons);

        for (People person : otherCriteriaItems) {
            if(!firstCriteriaItems.contains(person)){
                firstCriteriaItems.add(person);
            }
        }
        return firstCriteriaItems;
    }
}
