package com.spring.mybatis.springbootdemo.service;

import com.spring.mybatis.springbootdemo.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> selectAll();
    Course getOne(long courseId);
    void delete(long courseId);
    Course insert(Course course);
    void update(Course course);
}
