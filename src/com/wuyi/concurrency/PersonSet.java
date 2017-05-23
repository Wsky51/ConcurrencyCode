package com.wuyi.concurrency;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by LENOVO on 2017/5/18.
 */

//ThreadSafe
public class PersonSet {
    private final Set<Person> mySet=new HashSet <>();
    public synchronized void addPerson(Person p){
        mySet.add(p);
    }
    public synchronized boolean containsPerson(Person p){
        return mySet.contains(p);
    }
}

class Person{}