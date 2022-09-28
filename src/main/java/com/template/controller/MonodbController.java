package com.template.controller;

import com.template.common.BaseResult;
import com.template.common.ResultUtil;
import com.template.entity.mg.Student;
import com.template.mapper.mg.StudentDaoTypeTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class MonodbController {

    @Autowired
    private StudentDaoTypeTwo studentDaoTypeTwo;

    /**
     * 测试sharding jdbc单库操作
     */
    @PostMapping("/doc/add/two")
    public BaseResult addTwo() {
        // 插入10行
        for (int count = 0; count < 10; count++) {
            Student student = new Student()
                    .setStudentId("study_"+count) //如果自己不去设置id则系统会分配给一个id
                    .setStudentName("Echo"+count)
                    .setStudentAge(count)
                    .setStudentScore(98.5-count)
                    .setStudentBirthday(LocalDateTime.now());
            studentDaoTypeTwo.addOneStudent(student);
        }
        return ResultUtil.ok();
    }

    /**
     * 测试sharding jdbc单库操作
     */
    @DeleteMapping("/doc/delete/two")
    public BaseResult deleteTwoById() {
        //删除id为student_0的学生
        studentDaoTypeTwo.deleteOneStudentByStudentId("study_0");
        return ResultUtil.ok();
    }

    /**
     * 测试sharding jdbc单库操作
     */
    @GetMapping("/doc/find/two")
    public BaseResult getOneStudentByStudentId() {
        Student student = studentDaoTypeTwo.getOneStudentByStudentId("study_1");
        return ResultUtil.ok(student);
    }

    /**
     * 测试sharding jdbc单库操作
     */
    @PostMapping("/doc/update/two")
    public BaseResult updateOneStudent() {
        //        修改id为study_1的Student年龄为21
        Student student = studentDaoTypeTwo.getOneStudentByStudentId("study_2");
        student.setStudentAge(21);
        studentDaoTypeTwo.updateOneStudent(student);
        return ResultUtil.ok();
    }

    /**
     * 测试sharding jdbc单库操作
     */
    @GetMapping("/doc/find/all/two")
    public BaseResult getAllStudent() {
        List<Student> studentList = studentDaoTypeTwo.getAllStudent();
        return ResultUtil.ok(studentList);
    }

}
