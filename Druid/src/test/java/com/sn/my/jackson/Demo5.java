package com.sn.my.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Demo5 {
    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map userDataMap = new HashMap();
        UserData userData = new UserData();
        int[] marks = {1,2,4};
        Student student = new Student();
        student.setAge(10);
        student.setName("Mahesh");
        userData.setStudent(student);
        userData.setName("Mahesh Kumar");
        userData.setVerified(Boolean.FALSE);
        userData.setMarks(marks);


        TypeReference reference = new TypeReference<Map<String,UserData>>() {
        };


        userDataMap.put("studentData1",userData);
        objectMapper.writeValue(new File("demo5.json"),userDataMap);
        userDataMap = objectMapper.readValue(new File("demo5.json"),reference );
        UserData ud = (UserData)userDataMap.get("studentData1");


        System.out.println(Arrays.toString(ud.getMarks()));


    }
}
