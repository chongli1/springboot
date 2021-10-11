package com.xiexin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiexin.bean.Student;
import com.xiexin.bean.StudentExample;
import com.xiexin.respcode.Result;
import com.xiexin.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//针对student做的增删改查
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    //全查  主意不用 map，公司中 都用一个类，好几个类组成的类，叫做 统一相应类，每个公司都有自己的
//    public Map selectAll(){
//        return null;
//    }
    @RequestMapping("/selectAll")  // /student/selectAll
    public Result selectAll(){
        List<Student> students = studentService.selectByExample(null);  //select * form student
//        Result result = new Result(students);
//        return result;
        //或
        return new Result(students);
    }

    //分页查询  需要传的参数 page 和 pagesize（或 limit）  需要用到的注解：@RequestParam (value = "page",defaultValue = "1",required = true)
    @RequestMapping("/selectPageAll")   // /student/selectPageAll
    public Result selectPageAll(Student student, String studentName, @RequestParam(value = "page",defaultValue = "1",required = true) Integer page,
                                @RequestParam(value = "limit",defaultValue = "5",required = true) Integer pagesize){

        System.out.println("student = " + student);
        System.out.println("studentName = " + studentName);
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        //使用 pagehelper 分页
        PageHelper.startPage(page,pagesize);
        if(null!=student.getStudentSex()&&!"".equals(student.getStudentSex())&& null!=student.getStudentName()&&!"".equals(student.getStudentName())){
            criteria.andStudentSexEqualTo(student.getStudentSex());
            criteria.andStudentNameEqualTo(student.getStudentName());
        }
//        if(null!=student.getStudentName()&&!"".equals(student.getStudentName())){
//            criteria.andStudentNameEqualTo(student.getStudentName());
//        }

        List<Student> students = studentService.selectByExample(example);
        PageInfo pageInfo = new PageInfo(students);
        Result result = new Result(pageInfo);
        return result;

    }
}
