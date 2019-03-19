package com.spring.mybatis.springbootdemo.service.impl;

import com.spring.mybatis.springbootdemo.entity.Course;
import com.spring.mybatis.springbootdemo.service.CourseService;
import com.spring.mybatis.springbootdemo.util.RandomUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceImplTest {
    @Resource
    private CourseService courseService;
    @Test
    public void selectAll() {
        List<Course> courseList=courseService.selectAll();
        courseList.forEach(course -> System.out.println(course));
    }
    @Test
    public void getOne(){
        Course course=courseService.getOne(1L);
        System.out.println(course);
    }
    @Test
    public void delete(){
        courseService.delete(1L);
    }
    @Test
    public void insert(){
        Course course=new Course();
        course.setCourseName("微信小程序");
        course.setCourseClass("软件1721");
        course.setUserId(1L);
        course.setCover("101.jpg");
        course.setFinished((short)0);
        course.setCourseCode(RandomUtil.getRandomCode());
        courseService.insert(course);
    }
    @Test
    public void update(){
        Course course=courseService.getOne(5L);
        course.setCover("999.jpg");
        course.setFinished((short) 1);
        courseService.update(course);
    }

}