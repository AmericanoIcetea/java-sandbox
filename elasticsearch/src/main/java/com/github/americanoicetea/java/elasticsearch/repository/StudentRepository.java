package com.github.americanoicetea.java.elasticsearch.repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.github.americanoicetea.java.elasticsearch.custom.StudentComplexSearchCustomInterface;
import com.github.americanoicetea.java.elasticsearch.index.StudentIndex;
import com.github.americanoicetea.java.elasticsearch.index.StudentIndex.Degree;
import com.github.americanoicetea.java.elasticsearch.index.StudentIndex.StudentStatus;

public interface StudentRepository extends ElasticsearchRepository<StudentIndex, String>, StudentComplexSearchCustomInterface {
    /**
     * search by first name
     * 
     * @param firstName
     * @return n students
     */
    List<StudentIndex> findByFirstName(String firstName);

    /**
     * search by last name
     * 
     * @param lastName
     * @return n students
     */
    List<StudentIndex> findByLastName(String lastName);

    /**
     * search student by major
     * 
     * @param major
     * @return n student
     */
    List<StudentIndex> findByMajor(String major);

    /**
     * search by first name and last name
     * 
     * @param firstName
     * @param lastName
     * @return n students
     */
    List<StudentIndex> findByLastNameAndLastName(String firstName, String lastName);
  
    /**
     * search by first name or last name
     * 
     * @param firstName
     * @param lastName
     * @return n students
     */
    List<StudentIndex> findByLastNameOrLastName(String firstName, String lastName);

    /**
     * search student which born after specific date
     * 
     * @param date
     * @return n students
     */
    List<StudentIndex> findByDobBetween(LocalDate from,LocalDate to);
    
    /**
     * search student which born before specific date
     * 
     * @param date
     * @return n students
     */
    List<StudentIndex> findByDobBefore(LocalDate date);

    /**
     * search student which enroll before specific date
     * 
     * @param date
     * @return n students
     */
    List<StudentIndex> findByEnrollSinceAfter(LocalDate date);

    /**
     * search student which has degree in list
     * 
     * @param degree
     * @return n students
     */
    List<StudentIndex> findByDegreeIn(Collection<Degree> degree);

    /**
     * search student which student status not in
     * 
     * @param status
     * @return
     */
    List<StudentIndex> findByStatusNotIn(Collection<StudentStatus> status);

    /**
     * search student by major wildcard ?*
     * 
     * @param major
     * @return  n students
     */
    List<StudentIndex> findByMajorLike(String major);

    /**
     * search by first name and last name order by date of birth
     * 
     * @param firstName
     * @param lastName
     * @return n students
     */
    List<StudentIndex> findByLastNameAndLastNameOrderByDobDesc(String firstName, String lastName);

    /**
     * find student's gpax greater or equal to specific gpax
     * 
     * @param gpax
     * @return n students
     */
    List<StudentIndex> findByGpaxGreaterThanEqual(Double gpax);
    
    /**
     * find student's gpax less or equal to specific gpax
     * 
     * @param gpax
     * @return n students
     */
    List<StudentIndex> findByGpaxLessThanEqual(Double gpax);
}
