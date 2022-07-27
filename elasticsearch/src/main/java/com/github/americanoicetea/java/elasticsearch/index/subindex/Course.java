package com.github.americanoicetea.java.elasticsearch.index.subindex;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Course {
    public enum Grade {
        A,
        B_PLUS,
        B,
        C_PLUS,
        C,
        D_PLUS,
        D,
        F,
        W,
        PENDING
    }
    
    private static final Map<Grade,Double> GRADE_NUMBER_MAP =  new HashMap<Grade,Double>();
    
    static {
        GRADE_NUMBER_MAP.put(Grade.A, 4.0);
        GRADE_NUMBER_MAP.put(Grade.B_PLUS, 3.5);
        GRADE_NUMBER_MAP.put(Grade.B, 3.0);
        GRADE_NUMBER_MAP.put(Grade.C_PLUS, 2.5);
        GRADE_NUMBER_MAP.put(Grade.C, 2.0);
        GRADE_NUMBER_MAP.put(Grade.D, 1.5);
        GRADE_NUMBER_MAP.put(Grade.D_PLUS, 1.0);
        GRADE_NUMBER_MAP.put(Grade.F, 0.0);
        GRADE_NUMBER_MAP.put(Grade.W, -1.0);
        GRADE_NUMBER_MAP.put(Grade.PENDING, -2.0);
    }

    private String courseID;
    private String name;
    private int credit;
    private Grade grade;

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public static Map<Grade, Double> getGradeNumberMap() {
        return Collections.unmodifiableMap(GRADE_NUMBER_MAP);
    }


}
