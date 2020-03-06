package cn.bjfu.ws.service.Impl;

import cn.bjfu.ws.PO.StudentPO;
import cn.bjfu.ws.dao.StudentInfoMapper;
import cn.bjfu.ws.dao.UserInfoMapper;
import cn.bjfu.ws.model.StudentInfo;
import cn.bjfu.ws.model.StudentInfoExample;
import cn.bjfu.ws.model.UserInfo;
import cn.bjfu.ws.model.UserInfoExample;
import cn.bjfu.ws.service.StudentInfoService;
import cn.bjfu.ws.utils.IdWorker;
import cn.bjfu.ws.utils.MD5Util;
import com.sun.org.apache.xpath.internal.operations.String;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class StudentInfoServiceImpl implements StudentInfoService {

    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public Boolean batchSaveStudent(StudentPO studentPO) {
        StudentInfo studentInfo = new StudentInfo();
        if(studentPO == null){
            return false;
        }
        Long id = IdWorker.getId();
        studentInfo.setId(id);
        Timestamp d = new Timestamp(System.currentTimeMillis());
        studentInfo.setGmtCreate(d);
        studentInfo.setGmtModified(d);
        studentInfo.setClassName(studentPO.getClassName());
        studentInfo.setDormitory(studentPO.getDormitory());
        studentInfo.setEmergencyContact(studentPO.getEmergencyContact());
        studentInfo.setEmergencyPhone(studentPO.getEmergencyPhone());
        studentInfo.setEwardsPunishments(studentPO.getEwardsPunishments());
        studentInfo.setId(studentPO.getId());
        studentInfo.setIdNumber(studentPO.getIdNumber());
        studentInfo.setInstructor(studentPO.getInstructor());
        studentInfo.setMajor(studentPO.getMajor());
        studentInfo.setMaster(studentPO.getMaster());
        studentInfo.setMentalState(studentPO.getMentalState());
        studentInfo.setName(studentPO.getName());
        studentInfo.setNation(studentPO.getNation());
        studentInfo.setNativePlace(studentPO.getNativePlace());
        studentInfo.setNumber(studentPO.getNumber());
        studentInfo.setPhoneNumber(studentPO.getPhoneNumber());
        studentInfo.setPoliticsStatus(studentPO.getPoliticsStatus());
        studentInfo.setReligion(studentPO.getReligion());
        studentInfo.setRemark(studentPO.getRemark());
        studentInfo.setSex(studentPO.getSex());
        studentInfo.setStudentOrigin(studentPO.getStudentOrigin());
        studentInfo.setTutoringCase(studentPO.getTutoringCase());
        studentInfo.setType(studentPO.getType());

        UserInfo userInfo = new UserInfo();

        userInfo.setId(id);
        userInfo.setGmtCreate(d);
        userInfo.setGmtModified(d);
        userInfo.setUserName(studentInfo.getNumber().toString());
        Pattern pattern = Pattern.compile("(\\d{6})[a-zA-Z]?$");
        Matcher matcher = pattern.matcher(studentInfo.getIdNumber());
        while(matcher.find()){
            System.out.println(matcher.group(1));
            userInfo.setPassword(MD5Util.string2MD5(matcher.group(1)));
        }
        UserInfo userInfo1 = userService.queryUserByUsername(userInfo.getUserName());
        if(userInfo1 != null && queryStudentByUsername(studentInfo.getNumber()) != null){
            return false;
        }
        userInfoMapper.insert(userInfo);
        studentInfo.setUid(userInfo.getId());
        studentInfoMapper.insert(studentInfo);
        return true;
    }

    @Override
    public StudentInfo queryStudentByUsername(Long number) {
        StudentInfoExample studentInfoExample = new StudentInfoExample();
        StudentInfoExample.Criteria criteria = studentInfoExample.createCriteria();
        criteria.andNumberEqualTo(number);
        List<StudentInfo> user1 = this.studentInfoMapper.selectByExampleWithBLOBs(studentInfoExample);
        if(user1.isEmpty() ||  user1 == null){
            return null;
        }
        return user1.get(0);
    }

    @Override
    public StudentInfo saveStudent(StudentInfo studentInfo) {
        StudentInfo studentInfo1 = new StudentInfo();
        if(studentInfo == null){
            return null;
        }
        Long id = IdWorker.getId();
        studentInfo.setId(id);
        Timestamp d = new Timestamp(System.currentTimeMillis());
        studentInfo.setGmtCreate(d);
        studentInfo.setGmtModified(d);
        UserInfo userInfo = new UserInfo();
        Long uid = IdWorker.getId();
        userInfo.setId(uid);
        userInfo.setGmtCreate(d);
        userInfo.setGmtModified(d);
        userInfo.setUserName(studentInfo.getNumber()+"");
        if(null != studentInfo.getNumber()){
            userInfo.setUserName(studentInfo.getNumber()+"");
        }
        if(studentInfo.getIdNumber()  != null){
            Pattern pattern = Pattern.compile("(\\d{6})[a-zA-Z]?$");
            Matcher matcher = pattern.matcher(studentInfo.getIdNumber());
            while(matcher.find()){
                System.out.println(matcher.group(1));
                userInfo.setPassword(MD5Util.string2MD5(matcher.group(1)));
            }
        }else {
            userInfo.setPassword(MD5Util.string2MD5("123456"));
        }

        UserInfo userInfo1 = userService.queryUserByUsername(userInfo.getUserName());
        if(userInfo1 != null && queryStudentByUsername(studentInfo.getNumber()) != null){
            return null;
        }
        userInfoMapper.insert(userInfo);
        studentInfo.setUid(userInfo.getId());
        studentInfoMapper.insert(studentInfo);
        return studentInfoMapper.selectByPrimaryKey(studentInfo.getId());
    }

    @Override
    public Boolean delStudent(Long id) {
        StudentInfo studentInfo = studentInfoMapper.selectByPrimaryKey(id);
        userInfoMapper.deleteByPrimaryKey(studentInfo.getUid());
        studentInfoMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public StudentInfo updateStudent(StudentInfo studentInfo) {
        if(studentInfo.getNumber() != null ){
            UserInfo userInfo = userService.queryUserByUsername(studentInfo.getNumber()+"");
//            System.out.println( studentInfo.getUid());
            userInfo.setUserName(studentInfo.getNumber()+"");
            userService.updateUser(userInfo);
        }
        if(studentInfo.getIdNumber() != null){
            UserInfo userInfo = userService.queryUserByUsername(studentInfo.getNumber()+"");
            Pattern pattern = Pattern.compile("(\\d{6})[a-zA-Z]?$");
            Matcher matcher = pattern.matcher(studentInfo.getIdNumber());
            while(matcher.find()){
                userInfo.setPassword(MD5Util.string2MD5(matcher.group(1)));
            }
            userService.updateUser(userInfo);
        }
        StudentInfoExample example = new StudentInfoExample();
        StudentInfoExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(studentInfo.getId());
        int num = this.studentInfoMapper.updateByExampleSelective(studentInfo, example);
        if(num == 0){
            return null;
        }
        StudentInfo studentInfo1 = studentInfoMapper.selectByPrimaryKey(studentInfo.getId());
        return studentInfo1;
    }

    @Override
    public List<StudentInfo> searchAll() {
        StudentInfoExample example = new StudentInfoExample();
        StudentInfoExample.Criteria criteria = example.createCriteria();
        criteria.getAllCriteria();
        List<StudentInfo> studentInfos = this.studentInfoMapper.selectByExampleWithBLOBs(example);
        return studentInfos;
    }

    @Override
    public StudentInfo queryStudentById(Long id) {
        StudentInfoExample studentInfoExample = new StudentInfoExample();
        StudentInfoExample.Criteria criteria = studentInfoExample.createCriteria();
        criteria.andIdEqualTo(id);
        List<StudentInfo> user1 = this.studentInfoMapper.selectByExampleWithBLOBs(studentInfoExample);
        if(user1.isEmpty() ||  user1 == null){
            return null;
        }
        return user1.get(0);
    }


}
