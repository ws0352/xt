package cn.bjfu.ws.PO;

import cn.bjfu.ws.annotation.ExcelColumn;
import lombok.Data;

import java.util.Date;

@Data
public class StudentPO {
        /**
         *
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database column student_info.id
         *
         * @mbg.generated Wed Mar 04 13:44:20 CST 2020
         */
        @ExcelColumn(value = "序号", col = 1)
        private Long id;

        @ExcelColumn(value = "学号", col = 2)
        private Long number;

        @ExcelColumn(value = "姓名", col = 3)
        private String name;

        @ExcelColumn(value = "学生类别", col = 4)
        private String type;

        @ExcelColumn(value = "专业", col = 5)
        private String major;

        @ExcelColumn(value = "班级", col = 6)
        private String className;

        @ExcelColumn(value = "性别", col = 7)
        private String sex;

        @ExcelColumn(value = "导师/班主任", col = 8)
        private String master;

        @ExcelColumn(value = "辅导员", col = 9)
        private String instructor;

        @ExcelColumn(value = "籍贯", col = 10)
        private String nativePlace;

        @ExcelColumn(value = "生源地", col = 11)
        private String studentOrigin;

        @ExcelColumn(value = "民族", col = 12)
        private String nation;

        @ExcelColumn(value = "宗教信仰（没有填无）", col = 13)
        private String religion;

        @ExcelColumn(value = "政治面貌", col = 14)
        private String politicsStatus;

        @ExcelColumn(value = "宿舍号（如：10#1201）", col = 15)
        private String dormitory;

        @ExcelColumn(value = "身份证号", col = 16)
        private String idNumber;

        @ExcelColumn(value = "联系电话", col = 17)
        private Long phoneNumber;

        @ExcelColumn(value = "紧急联系人（关系）", col = 18)
        private String emergencyContact;


        @ExcelColumn(value = "紧急联系电话", col = 19)
        private String emergencyPhone;

        @ExcelColumn(value = "奖惩状况", col = 20)
        private String ewardsPunishments;


        @ExcelColumn(value = "心理情况", col = 21)
        private String mentalState;

        @ExcelColumn(value = "辅导情况（图片+文字）", col = 22)
        private String tutoringCase;

        @ExcelColumn(value = "备注（其他情况）", col = 23)
        private String remark;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Long getNumber() {
                return number;
        }

        public void setNumber(Long number) {
                this.number = number;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getType() {
                return type;
        }

        public void setType(String type) {
                this.type = type;
        }

        public String getMajor() {
                return major;
        }

        public void setMajor(String major) {
                this.major = major;
        }

        public String getClassName() {
                return className;
        }

        public void setClassName(String className) {
                this.className = className;
        }

        public String getSex() {
                return sex;
        }

        public void setSex(String sex) {
                this.sex = sex;
        }

        public String getMaster() {
                return master;
        }

        public void setMaster(String master) {
                this.master = master;
        }

        public String getInstructor() {
                return instructor;
        }

        public void setInstructor(String instructor) {
                this.instructor = instructor;
        }

        public String getNativePlace() {
                return nativePlace;
        }

        public void setNativePlace(String nativePlace) {
                this.nativePlace = nativePlace;
        }

        public String getStudentOrigin() {
                return studentOrigin;
        }

        public void setStudentOrigin(String studentOrigin) {
                this.studentOrigin = studentOrigin;
        }

        public String getNation() {
                return nation;
        }

        public void setNation(String nation) {
                this.nation = nation;
        }

        public String getReligion() {
                return religion;
        }

        public void setReligion(String religion) {
                this.religion = religion;
        }

        public String getPoliticsStatus() {
                return politicsStatus;
        }

        public void setPoliticsStatus(String politicsStatus) {
                this.politicsStatus = politicsStatus;
        }

        public String getDormitory() {
                return dormitory;
        }

        public void setDormitory(String dormitory) {
                this.dormitory = dormitory;
        }

        public String getIdNumber() {
                return idNumber;
        }

        public void setIdNumber(String idNumber) {
                this.idNumber = idNumber;
        }

        public Long getPhoneNumber() {
                return phoneNumber;
        }

        public void setPhoneNumber(Long phoneNumber) {
                this.phoneNumber = phoneNumber;
        }

        public String getEmergencyContact() {
                return emergencyContact;
        }

        public void setEmergencyContact(String emergencyContact) {
                this.emergencyContact = emergencyContact;
        }

        public String getEmergencyPhone() {
                return emergencyPhone;
        }

        public void setEmergencyPhone(String emergencyPhone) {
                this.emergencyPhone = emergencyPhone;
        }

        public String getEwardsPunishments() {
                return ewardsPunishments;
        }

        public void setEwardsPunishments(String ewardsPunishments) {
                this.ewardsPunishments = ewardsPunishments;
        }

        public String getMentalState() {
                return mentalState;
        }

        public void setMentalState(String mentalState) {
                this.mentalState = mentalState;
        }

        public String getTutoringCase() {
                return tutoringCase;
        }

        public void setTutoringCase(String tutoringCase) {
                this.tutoringCase = tutoringCase;
        }

        public String getRemark() {
                return remark;
        }

        public void setRemark(String remark) {
                this.remark = remark;
        }

        @Override
    public String toString() {
        return "StudentPO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", number=" + number +
                ", major='" + major + '\'' +
                ", className='" + className + '\'' +
                ", sex='" + sex + '\'' +
                ", master='" + master + '\'' +
                ", instructor='" + instructor + '\'' +
                ", nativePlace='" + nativePlace + '\'' +
                ", studentOrigin='" + studentOrigin + '\'' +
                ", nation='" + nation + '\'' +
                ", religion='" + religion + '\'' +
                ", politicsStatus='" + politicsStatus + '\'' +
                ", dormitory='" + dormitory + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", idNumber=" + idNumber +
                ", emergencyContact='" + emergencyContact + '\'' +
                ", emergencyPhone='" + emergencyPhone + '\'' +
                ", ewardsPunishments='" + ewardsPunishments + '\'' +
                ", mentalState='" + mentalState + '\'' +
                ", tutoringCase='" + tutoringCase + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
