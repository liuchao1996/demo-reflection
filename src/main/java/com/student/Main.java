package com.student;

import com.student.entity.Student;
import com.student.util.SimpleFormatter;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Student student = new Student("小明",new Date(),9.5);
        String format = SimpleFormatter.format(student);

        System.out.println(format);

    }

}
