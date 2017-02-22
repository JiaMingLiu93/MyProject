package com.liu.design.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jam on 2017/2/22.
 *
 * Filter pattern or Criteria pattern is a design pattern that enables developers to filter a set of objects using
 * different criteria and chaining them in a decoupled way through logical operations. This type of design pattern
 * comes under structural pattern as this pattern combines multiple criteria to obtain single criteria.
 */
public class CriteriaPatternDemo {
    public static void main(String[] args) {
        List<People> persons = new ArrayList<People>();

        persons.add(new People("Robert","Male", "Single"));
        persons.add(new People("John", "Male", "Married"));
        persons.add(new People("Laura", "Female", "Married"));
        persons.add(new People("Diana", "Female", "Single"));
        persons.add(new People("Mike", "Male", "Single"));
        persons.add(new People("Bobby", "Male", "Single"));

        Criteria male = new CriteriaMale();
        Criteria female = new CriteriaFemale();
        Criteria single = new CriteriaSingle();
        Criteria singleMale = new AndCriteria(single, male);
        Criteria singleOrFemale = new OrCriteria(single, female);

        System.out.println("Males: ");
        printPersons(male.meetCriteria(persons));

        System.out.println("\nFemales: ");
        printPersons(female.meetCriteria(persons));

        System.out.println("\nSingle Males: ");
        printPersons(singleMale.meetCriteria(persons));

        System.out.println("\nSingle Or Females: ");
        printPersons(singleOrFemale.meetCriteria(persons));
    }

    public static void printPersons(List<People> persons){

        for (People person : persons) {
            System.out.println("Person : [ Name : " + person.getName() + ", Gender : " + person.getGender() + ", Marital Status : " + person.getMaritalStatus() + " ]");
        }
    }
}
