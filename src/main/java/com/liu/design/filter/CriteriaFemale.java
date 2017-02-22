package com.liu.design.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jam on 2017/2/22.
 */
public class CriteriaFemale implements Criteria{
    @Override
    public List<People> meetCriteria(List<People> persons) {
        List<People> femalePersons = new ArrayList<People>();

        for (People person : persons) {
            if(person.getGender().equalsIgnoreCase("FEMALE")){
                femalePersons.add(person);
            }
        }
        return femalePersons;
    }
}
