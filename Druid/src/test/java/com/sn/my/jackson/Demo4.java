package com.sn.my.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * sout
 * psvm
 * fori
 */
public class Demo4 {
    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> studentDataMap = new HashMap<String,Object>();
        int[] marks = {1,2,3};
        Student student = new Student();
        student.setAge(1);
        student.setName("zhen");
        studentDataMap.put("student",student);
        studentDataMap.put("name","Mahesh Kumar");
        studentDataMap.put("verifield",Boolean.FALSE);
        studentDataMap.put("marks",marks);

        objectMapper.writeValue(new File("demo4.json"),studentDataMap);
        studentDataMap = objectMapper.readValue(new File("demo4.json"),Map.class);
        System.out.println(studentDataMap.get("student"));
        System.out.println(studentDataMap.get("name"));
        System.out.println(studentDataMap.get("verifield"));
        System.out.println(studentDataMap.get("marks"));




    }
}
