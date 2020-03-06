package cn.bjfu.ws.dao;

import cn.bjfu.ws.model.StudentInfo;
import cn.bjfu.ws.model.StudentInfoExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_info
     *
     * @mbg.generated Wed Mar 04 23:20:23 CST 2020
     */
    long countByExample(StudentInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_info
     *
     * @mbg.generated Wed Mar 04 23:20:23 CST 2020
     */
    int deleteByExample(StudentInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_info
     *
     * @mbg.generated Wed Mar 04 23:20:23 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_info
     *
     * @mbg.generated Wed Mar 04 23:20:23 CST 2020
     */
    int insert(StudentInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_info
     *
     * @mbg.generated Wed Mar 04 23:20:23 CST 2020
     */
    int insertSelective(StudentInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_info
     *
     * @mbg.generated Wed Mar 04 23:20:23 CST 2020
     */
    List<StudentInfo> selectByExampleWithBLOBs(StudentInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_info
     *
     * @mbg.generated Wed Mar 04 23:20:23 CST 2020
     */
    List<StudentInfo> selectByExample(StudentInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_info
     *
     * @mbg.generated Wed Mar 04 23:20:23 CST 2020
     */
    StudentInfo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_info
     *
     * @mbg.generated Wed Mar 04 23:20:23 CST 2020
     */
    int updateByExampleSelective(@Param("record") StudentInfo record, @Param("example") StudentInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_info
     *
     * @mbg.generated Wed Mar 04 23:20:23 CST 2020
     */
    int updateByExampleWithBLOBs(@Param("record") StudentInfo record, @Param("example") StudentInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_info
     *
     * @mbg.generated Wed Mar 04 23:20:23 CST 2020
     */
    int updateByExample(@Param("record") StudentInfo record, @Param("example") StudentInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_info
     *
     * @mbg.generated Wed Mar 04 23:20:23 CST 2020
     */
    int updateByPrimaryKeySelective(StudentInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_info
     *
     * @mbg.generated Wed Mar 04 23:20:23 CST 2020
     */
    int updateByPrimaryKeyWithBLOBs(StudentInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_info
     *
     * @mbg.generated Wed Mar 04 23:20:23 CST 2020
     */
    int updateByPrimaryKey(StudentInfo record);
}