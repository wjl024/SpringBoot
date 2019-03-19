package com.spring.mybatis.springbootdemo.controller;

import com.spring.mybatis.springbootdemo.entity.Course;
import com.spring.mybatis.springbootdemo.service.CourseService;
import com.spring.mybatis.springbootdemo.util.RandomUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CourseController {
    @Resource
    private CourseService courseService;
    @RequestMapping(value = "/courses",method = RequestMethod.GET)
    public List<Course> selectAll() {
        return courseService.selectAll();
    }

    @RequestMapping(value = "/courses/{id}",method = RequestMethod.GET)
    public Course getOne(@PathVariable("id") long id){
        return courseService.getOne(id);
    }

    @RequestMapping(value = "/course/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") long id){
        courseService.delete(id);
    }

    /**
     * 新增班课
     *
     * @param course
     */
    @RequestMapping(value = "/course", method = RequestMethod.POST)
    public void addCourse(@RequestBody Course course) {
        course.setCourseCode(RandomUtil.getRandomCode());
        courseService.insert(course);
    }

    /**
     * 更新班课
     *
     * @param course
     */
    @RequestMapping(value = "/course", method = RequestMethod.PUT)
    public void updateCourse(@RequestBody Course course) {
        courseService.update(course);
    }
}