package com.spring.mybatis.springbootdemo.service.impl;

import com.spring.mybatis.springbootdemo.entity.Course;
import com.spring.mybatis.springbootdemo.mapper.CourseMapper;
import com.spring.mybatis.springbootdemo.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseMapper courseMapper;

    @Override
    public List<Course> selectAll() {
        return courseMapper.selectAll();
    }

    @Override
    public Course getOne(long courseId) {
        return courseMapper.getOne(courseId);
    }

    @Override
    public void delete(long courseId) {
         courseMapper.delete(courseId);
    }

    @Override
    public Course insert(Course course) {
        courseMapper.insert(course);
        return course;
    }

    @Override
    public void update(Course course) {
        courseMapper.update(course);
    }
}
