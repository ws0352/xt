package cn.bjfu.ws.service;

import cn.bjfu.ws.PO.StudentPO;
import cn.bjfu.ws.model.StudentInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentInfoService {
    Boolean batchSaveStudent(StudentPO studentPO);
    StudentInfo queryStudentByUsername(Long number);
    StudentInfo saveStudent(StudentInfo studentInfo);
    Boolean delStudent(Long id);
    StudentInfo updateStudent(StudentInfo studentInfo);
    List<StudentInfo> searchAll();
    StudentInfo queryStudentById(Long id);
}
