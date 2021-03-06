package com.liu.design.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jam on 2017/2/22.
 */
public class CriteriaSingle implements Criteria{

    @Override
    public List<People> meetCriteria(List<People> persons) {
        List<People> singlePersons = new ArrayList<People>();

        for (People person : persons) {
            if(person.getMaritalStatus().equalsIgnoreCase("SINGLE")){
                singlePersons.add(person);
            }
        }
        return singlePersons;
    }
}
