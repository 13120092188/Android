package com.a044.a051.homework.gymclub.httprequest;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class LoginResult {
    private String loginStatus;
    private String courses;
    public int getResult(){
        return Integer.parseInt(loginStatus);
    }
    public TreeMap<String,String> getCourses(){
        TreeMap<String,String> map = new TreeMap<>();
        if(courses.equals("null")){
            return map;
        }
        String[] coursesElements = courses.split("\t");
        System.out.println(courses);
        for(int i = 0;i<coursesElements.length;i++) {
            String[] details = coursesElements[i].split(":");
            map.put(details[0], details[1]);
        }
        return map;
    }
}
