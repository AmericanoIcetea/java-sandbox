package com.github.americanoicetea.java.elasticsearch.index;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.WriteTypeHint;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.americanoicetea.java.elasticsearch.index.subindex.Contact;
import com.github.americanoicetea.java.elasticsearch.index.subindex.SemesterCourseEnrollment;

@Document(indexName = "student-index", writeTypeHint = WriteTypeHint.FALSE)
public class StudentIndex {

    public enum StudentStatus {
        ACTIVE,
        DROP_OUT,
        GRADUATED,
        RESIGN
    }

    public enum Degree {
        BACHELOR,
        MASTER,
        PHD
    }

    @Id
    @ReadOnlyProperty
    private String studentID;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Field(type = FieldType.Date, format = DateFormat.date)
    private LocalDate dob;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Field(type = FieldType.Date, format = DateFormat.date)
    private LocalDate enrollSince;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Field(type = FieldType.Date, format = DateFormat.date)
    private LocalDate graduationDealine;
    @Field(type = FieldType.Nested)
    private List<SemesterCourseEnrollment> semesterCourseEnrollments;
    @Field(type = FieldType.Nested)
    private Contact contact;
    private String major;
    @Field(type = FieldType.Keyword)
    private Degree degree;

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public List<SemesterCourseEnrollment> getSemesterCourseEnrollments() {
        return semesterCourseEnrollments;
    }

    public void setSemesterCourseEnrollments(List<SemesterCourseEnrollment> semesterCourseEnrollments) {
        this.semesterCourseEnrollments = semesterCourseEnrollments;
    }

    public LocalDate getEnrollSince() {
        return enrollSince;
    }

    public void setEnrollSince(LocalDate enrollSince) {
        this.enrollSince = enrollSince;
    }

    public LocalDate getGraduationDealine() {
        return graduationDealine;
    }

    public void setGraduationDealine(LocalDate graduationDealine) {
        this.graduationDealine = graduationDealine;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Double getGpax() {
        if (!CollectionUtils.isEmpty(semesterCourseEnrollments)) {
            double totalGpa = semesterCourseEnrollments.stream()
                    .mapToDouble(semester -> semester.getGpa() * semester.getTotalCredit()).sum();
            int totalCredit = getTotalCredit();
            return totalCredit != 0 ? totalGpa / totalCredit : 0;
        }
        return null;
    }

    public int getTotalCredit() {
        if (!CollectionUtils.isEmpty(semesterCourseEnrollments)) {
            return semesterCourseEnrollments.stream().mapToInt(semester -> semester.getTotalCredit()).sum();
        }
        return 0;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

}
