package com.github.americanoicetea.java.elasticsearch.index.subindex;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SemesterCourseEnrollment {

    private String semesterID;
    @Field(type = FieldType.Date, format = DateFormat.date)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @Field(type = FieldType.Date, format = DateFormat.date)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @Field(type = FieldType.Nested)
    private List<Course> courses;

    public String getSemesterID() {
        return semesterID;
    }

    public void setSemesterID(String semesterID) {
        this.semesterID = semesterID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Double getGpa() {
        if (!CollectionUtils.isEmpty(courses)) {
            if (!courses.stream()
                    .anyMatch(course -> course.getGrade() == null || course.getGrade().equals(Course.Grade.PENDING))) {
                double totalGrade = courses.stream().mapToDouble(course -> {
                    return Course.getGradeNumberMap().get(course.getGrade()) * course.getCredit();
                }).sum();
                int totalCredit = getTotalCredit();
                return totalCredit != 0 ? totalGrade / totalCredit : 0;
            }
        }
        return null;
    }

    public int getTotalCredit() {
        if (!CollectionUtils.isEmpty(courses)) {
            return courses.stream().mapToInt(course -> course.getCredit()).sum();
        }
        return 0;
    }
}
