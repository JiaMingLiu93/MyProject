package com.liu.design.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jam on 2017/2/22.
 */
public class CriteriaMale implements Criteria{
    @Override
    public List<People> meetCriteria(List<People> persons) {
        List<People> malePersons = new ArrayList<People>();

        for (People person : persons) {
            if(person.getGender().equalsIgnoreCase("MALE")){
                malePersons.add(person);
            }
        }
        return malePersons;
    }
}
