package com.sn.my.jackson;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Demo3 {
    public static void main(String[] args) throws Exception {
        Demo3 demo3 = new Demo3();
        Student student = new Student();
        student.setAge(10);
        student.setName("Mahesh");
        demo3.write(student);
        System.out.println(demo3.read());
    }

    public void write(Student student) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("student.json"),student);
    }

    public Student read() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return  objectMapper.readValue(new File("student.json"),Student.class);
        } catch (JsonParseException jpe) {
            jpe.printStackTrace();
            return null;
        }  catch (JsonMappingException jme) {
            jme.printStackTrace();
            return null;
        }catch (IOException io) {
            io.printStackTrace();
            return null;
        }
    }
}
