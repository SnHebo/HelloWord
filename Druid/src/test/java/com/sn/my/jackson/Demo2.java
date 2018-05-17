package com.sn.my.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Demo2 {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "{\"name\":\"Mahesh\", \"age\":21}";


        Student student = mapper.readValue(jsonString,Student.class);
        System.out.println(student);

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        System.out.println(mapper.writeValueAsString(student));
    }
}
