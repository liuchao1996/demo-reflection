package com.student.entity;

import com.student.ann.Format;
import com.student.ann.Label;

import java.util.Date;

/**
 * @author lc
 */
public class Student {
    @Label("姓名")
    String name;
    @Label("出生日期")
    @Format(pattern="yyyy/MM/dd")
    Date born;
    @Label("分数")
    double score;

    public Student(String name, Date born, double score) {
        this.name = name;
        this.born = born;
        this.score = score;
    }

    public Student() {
    }
}
