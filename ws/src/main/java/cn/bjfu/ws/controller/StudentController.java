package cn.bjfu.ws.controller;

import cn.bjfu.ws.PO.StudentPO;
import cn.bjfu.ws.exception.Result;
import cn.bjfu.ws.exception.exception.error.CommonErrorCode;
import cn.bjfu.ws.model.StudentInfo;
import cn.bjfu.ws.service.StudentInfoService;
import cn.bjfu.ws.utils.ExcelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSON;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class StudentController {

    @Autowired
    private StudentInfoService studentInfoService;

    private Logger logger = LoggerFactory.getLogger(StudentController.class);

    @RequestMapping(value = "/readExcel", method = RequestMethod.POST)
    public Result readExcel(@RequestBody MultipartFile file){
        long t1 = System.currentTimeMillis();
        List<StudentPO> list = ExcelUtils.readExcel("", StudentPO.class, file);
        long t2 = System.currentTimeMillis();
        System.out.println(String.format("read over! cost:%sms", (t2 - t1)));

        list.forEach(
                b -> studentInfoService.batchSaveStudent(b)
        );
        logger.info("数据批量插入");
        return Result.ofSuccess();
    }

    @RequestMapping(value = "/user-add", method = RequestMethod.POST)
    public Result addStudent(@RequestBody StudentInfo studentInfo){
        if(studentInfo == null){
            return Result.ofFail(CommonErrorCode.ERROR_FORMAT);
        }
        StudentInfo studentInfo1 = studentInfoService.saveStudent(studentInfo);
        if (studentInfo1 != null){
            logger.info("数据已经插入");
            return Result.ofSuccess(studentInfo1);
        }else{
            return Result.ofFail(CommonErrorCode.ERROR_FORMAT);
        }
    }

    @RequestMapping(value = "/user-del", method = RequestMethod.DELETE)
    public Result delStudent(@RequestParam Long id){
        if(id == 0){
            return Result.ofFail(CommonErrorCode.ERROR_FORMAT);
        }
       Boolean isSuccess = studentInfoService.delStudent(id);
        if (isSuccess == true){
            logger.info("数据已经删除");
            return Result.ofSuccess();
        }else{
            return Result.ofFail(CommonErrorCode.ERROR_FORMAT);
        }
    }

    @RequestMapping(value = "/user-update", method = RequestMethod.PUT)
    public Result updateStudent(@RequestBody StudentInfo studentInfo){
        if(studentInfo == null){
            return Result.ofFail(CommonErrorCode.ERROR_FORMAT);
        }
        System.out.println(studentInfo.getName() + studentInfo.getUid());
        StudentInfo studentInfo1 = studentInfoService.updateStudent(studentInfo);
        if (studentInfo1 != null){
            logger.info("数据已经修改");
            return Result.ofSuccess(studentInfo1);
        }else{
            return Result.ofFail(CommonErrorCode.ERROR_FORMAT);
        }
    }

    @RequestMapping(value = "/user-query", method = RequestMethod.GET)
    public Result queryStudent(){

        List<StudentInfo> studentInfos = studentInfoService.searchAll();
        if (studentInfos != null && studentInfos.size() > 0){
            logger.info("查询所有数据");
            return Result.ofSuccess(studentInfos);
        }else{
            return Result.ofFail(CommonErrorCode.ERROR_FORMAT);
        }
    }
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Result queryStudentById(@RequestParam Long id){
        if(id == 0){
            return Result.ofFail(CommonErrorCode.ERROR_FORMAT);
        }
        StudentInfo studentInfos = studentInfoService.queryStudentById(id);
        if (studentInfos != null ){
            logger.info("查询所有数据");
            return Result.ofSuccess(studentInfos);
        }else{
            return Result.ofFail(CommonErrorCode.ERROR_FORMAT);
        }
    }
}
