package DB;

import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

import model.*;

public class DB {
    private static final Object UTC= TimeZone.getTimeZone("UTC");
    Connection ct;
    PreparedStatement pstmt;
    String url;
    String user;
    String password;
    public DB(){
        url= "jdbc:mysql://localhost:3306/teacherwork?characterEncoding=UTF-8"+"&serverTimezone=UTC";
        user ="root";
        password ="1234";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            ct=DriverManager.getConnection(url,user,password);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //查询学生信息
    public ArrayList findStuInfoInClass(Teacher teacher){
        try{
            pstmt=ct.prepareStatement("select * from Student where cls_id = (select cls_id from teacher where tea_id = ?)");
            pstmt.setInt(1,Integer.parseInt(teacher.getId()));
            ArrayList stuAL=new ArrayList();

            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                Student stu=new Student();
                stu.setId(rs.getString(1));
                stu.setName(rs.getString(2));
                stu.setAge(rs.getInt(3));
                stu.setSex(rs.getString(4));
                stu.setEthnicity(rs.getString(5));
                stu.setPoli_status(rs.getString(6));
                stu.setPosition(rs.getString(7));
                stu.setCls_id(rs.getInt(8));
                stu.setDor_id(rs.getString(9));
                stuAL.add(stu);
            }
            return stuAL;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList findAllStudent() {
        try{
            pstmt=ct.prepareStatement("select * from Student ");
            ArrayList stuAL=new ArrayList();
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                Student stu=new Student();
                stu.setId(rs.getString(1));
                stu.setName(rs.getString(2));
                stu.setAge(rs.getInt(3));
                stu.setSex(rs.getString(4));
                stu.setEthnicity(rs.getString(5));
                stu.setPoli_status(rs.getString(6));
                stu.setPosition(rs.getString(7));
                stu.setCls_id(rs.getInt(8));
                stu.setDor_id(rs.getString(9));
                stuAL.add(stu);
            }
            return stuAL;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList findAllTeacher() {
        try{
            pstmt=ct.prepareStatement("select * from teacher ");
            ArrayList teaAL=new ArrayList();
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                Teacher tea = new Teacher();
                tea.setId(rs.getString(1));
                tea.setName(rs.getString(2));
                tea.setPassword(rs.getString(3));
                tea.setEmail(rs.getString(4));
                tea.setCls_id(rs.getInt(5));
                teaAL.add(tea);
            }
            return teaAL;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //查询学生信息
    public ArrayList findAttInfoInClass(Teacher teacher){
        try{
            pstmt=ct.prepareStatement("select * from attendance where stu_id IN(select stu_id from Student where cls_id = (select cls_id from teacher where tea_id = ?))");
            pstmt.setInt(1,Integer.parseInt(teacher.getId()));
            ArrayList attAL=new ArrayList();
            ResultSet rs=pstmt.executeQuery();

            while(rs.next()){
                Attendance att = new Attendance();
                att.setStu_id(rs.getString(1));
                att.setDate(rs.getDate(2));
                att.setIfatt(rs.getString(3));
                attAL.add(att);

            }
            return attAL;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList findActivityInfoInClass(Teacher teacher) {
        try{
            pstmt=ct.prepareStatement("select * from activity where cls_id = ?");
            pstmt.setInt(1,teacher.getCls_id());
            ArrayList ActivityAL=new ArrayList();
            ResultSet rs=pstmt.executeQuery();

            while(rs.next()){
                Activity activity = new Activity();
                activity.setCls_id(rs.getInt(1));
                activity.setName(rs.getString(2));
                activity.setDate(rs.getDate(3));
                activity.setPlace(rs.getString(4));
                activity.setContent(rs.getString(5));
                ActivityAL.add(activity);

            }
            return ActivityAL;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList findPlanInfoInClass(Teacher teacher) {
        try{
            pstmt=ct.prepareStatement("select * from plan where tea_id = ?");
            pstmt.setString(1,teacher.getId());
            ArrayList PlanAL=new ArrayList();
            ResultSet rs=pstmt.executeQuery();

            while(rs.next()){
                Plan plan = new Plan();
                plan.setTea_id(rs.getString(1));
                plan.setTime(rs.getTimestamp(2));
                plan.setPlan(rs.getString(3));
                PlanAL.add(plan);
            }
            return PlanAL;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList findSummaryInfoInClass(Teacher teacher) {
        try{
            pstmt=ct.prepareStatement("select * from summary where tea_id = ?");
            pstmt.setString(1,teacher.getId());
            ArrayList SummaryAL=new ArrayList();
            ResultSet rs=pstmt.executeQuery();

            while(rs.next()){
                Summary summary = new Summary();
                summary.setTea_id(rs.getString(1));
                summary.setDate(rs.getDate(2));
                summary.setSummary(rs.getString(3));
                SummaryAL.add(summary);
            }
            return SummaryAL;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList findMeetingInfoInClass(Teacher teacher) {
        try{
            pstmt=ct.prepareStatement("select * from meetings where cls_id = ?");
            pstmt.setInt(1,teacher.getCls_id());

            ArrayList MeetingAL=new ArrayList();
            ResultSet rs=pstmt.executeQuery();

            while(rs.next()) {
                Meetings meetings = new Meetings();
                meetings.setCls_id(rs.getInt(1));
                meetings.setDate(rs.getDate(2));
                meetings.setTheme(rs.getString(3));
                meetings.setContent(rs.getString(4));
                MeetingAL.add(meetings);
            }
            return MeetingAL;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList findTalkingInfoInClass(Teacher teacher) {
        try{
            pstmt=ct.prepareStatement("select * from stutalk where stu_id in(select stu_id from student where cls_id = ?)");
            pstmt.setInt(1,teacher.getCls_id());

            ArrayList TalkingAL=new ArrayList();
            ResultSet rs=pstmt.executeQuery();

            while(rs.next()) {
                Stutalk talking = new Stutalk();
                talking.setStu_id(rs.getString(1));
                talking.setDate(rs.getDate(2));
                talking.setContent(rs.getString(3));
                TalkingAL.add(talking);
            }
            return TalkingAL;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList findCommentInClass(Teacher teacher) {
        try{
            pstmt=ct.prepareStatement("select * from comment where stu_id in(select stu_id from student where cls_id = ?)");
            pstmt.setInt(1,teacher.getCls_id());

            ArrayList CommentAL=new ArrayList();
            ResultSet rs=pstmt.executeQuery();

            while(rs.next()) {
                Comment comment = new Comment();
                comment.setStu_id(rs.getString(1));
                comment.setDate(rs.getDate(2));
                comment.setSelf_cmt(rs.getString(3));
                comment.setCls_adv(rs.getString(4));
                CommentAL.add(comment);
            }
            return CommentAL;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList findVisitInClass(Teacher teacher) {
        try{
            pstmt=ct.prepareStatement("select * from visit where stu_id in(select stu_id from student where cls_id = ?)");
            pstmt.setInt(1,teacher.getCls_id());

            ArrayList VisitAL=new ArrayList();
            ResultSet rs=pstmt.executeQuery();

            while(rs.next()) {
                Visit visit = new Visit();
                visit.setStu_id(rs.getString(1));
                visit.setDate(rs.getDate(2));
                visit.setContent(rs.getString(3));
                VisitAL.add(visit);
            }
            return VisitAL;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //查询学生成绩信息
    public ArrayList findStuGrade(Teacher teacher){
        try{
            pstmt=ct.prepareStatement("select * from grade where stu_id in(select stu_id from student where cls_id = (select cls_id from teacher where tea_id = ?))");
            pstmt.setInt(1,Integer.parseInt(teacher.getId()));

            ArrayList gradeAL=new ArrayList();
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                Grade grade=new Grade();
                grade.setStu_id(rs.getString(1));
                grade.setDate(rs.getDate(2));
                grade.setChinse(rs.getFloat(3));
                grade.setMath(rs.getFloat(4));
                grade.setEnglish(rs.getFloat(5));

                gradeAL.add(grade);
            }
            return gradeAL;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList findReward(Teacher teacher) {
        try{
            pstmt=ct.prepareStatement("select * from reward where stu_id in(select stu_id from student where cls_id = (select cls_id from teacher where tea_id = ?))");
            pstmt.setInt(1,Integer.parseInt(teacher.getId()));

            ArrayList RewardAL=new ArrayList();
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                Reward reward = new Reward();
                reward.setStu_id(rs.getString(1));
                reward.setDate(rs.getDate(2));
                reward.setType(rs.getString(3).charAt(0));
                reward.setReason(rs.getString(4));
                RewardAL.add(reward);
            }
            return RewardAL;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList findFund(Teacher teacher) {
        try{
            pstmt=ct.prepareStatement("select * from fund where cls_id = ?");
            pstmt.setInt(1,teacher.getCls_id());

            ArrayList FundAL=new ArrayList();
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                Fund fund = new Fund();
                fund.setCls_id(rs.getInt(1));
                fund.setDate(rs.getDate(2));
                fund.setAmount(rs.getFloat(3));
                fund.setBalance(rs.getFloat(4));
                fund.setRemark(rs.getString(5));
                FundAL.add(fund);
            }
            return FundAL;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public Admin checkAdmin(String username, String password){
        try{
            pstmt=ct.prepareStatement("select * from admin where username=? and password=?");
            pstmt.setString(1,username);
            pstmt.setString(2, password);
            ResultSet rs=pstmt.executeQuery();
            Admin admin=new Admin();
            while(rs.next()){
                admin.setUsername(rs.getString(1));
                admin.setPassword(rs.getString(2));
                admin.setEmail(rs.getString(3));
                return admin;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Teacher checkTeacher(String id, String password){
        try{
            pstmt=ct.prepareStatement("select * from teacher where tea_id=? and pwd=?");
            pstmt.setString(1,id);
            pstmt.setString(2, password);
            ResultSet rs=pstmt.executeQuery();
            Teacher teacher=new Teacher();
            while(rs.next()){
                teacher.setId(rs.getString(1));
                teacher.setName(rs.getString(2));
                teacher.setPassword(rs.getString(3));
                teacher.setEmail(rs.getString(4));
                teacher.setCls_id(rs.getInt(5));
                return teacher;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Teacher checkTeacher(String id){
        try{
            pstmt=ct.prepareStatement("select * from teacher where tea_id=?");
            pstmt.setString(1,id);
            ResultSet rs=pstmt.executeQuery();
            Teacher teacher=new Teacher();
            while(rs.next()){
                teacher.setId(rs.getString(1));
                teacher.setName(rs.getString(2));
                teacher.setPassword(rs.getString(3));
                teacher.setEmail(rs.getString(4));
                teacher.setCls_id(rs.getInt(5));
                return teacher;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public User checkUser(String id, String email){
        try{
            pstmt=ct.prepareStatement("select * from teacher where tea_id=? and mail=?");
            pstmt.setString(1,id);
            pstmt.setString(2, email);
            ResultSet rs=pstmt.executeQuery();
            Teacher teacher=new Teacher();
            while(rs.next()){
                teacher.setId(rs.getString(1));
                teacher.setName(rs.getString(2));
                teacher.setPassword(rs.getString(3));
                teacher.setEmail(rs.getString(4));
                return teacher;
            }
            pstmt=ct.prepareStatement("select * from admin where username=? and a_email=?");
            pstmt.setString(1,id);
            pstmt.setString(2, email);
            rs=pstmt.executeQuery();
            Admin admin=new Admin();
            while(rs.next()){
                admin.setUsername(rs.getString(1));
                admin.setPassword(rs.getString(2));
                admin.setEmail(rs.getString(3));
                return admin;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Student checkStudent(String id){
        try{
            pstmt=ct.prepareStatement("select * from student where stu_id=?");
            pstmt.setString(1,id);
            ResultSet rs=pstmt.executeQuery();
            Student stu=new Student();
            while(rs.next()){
                stu.setId(rs.getString(1));
                stu.setName(rs.getString(2));
                stu.setAge(rs.getInt(3));
                stu.setSex(rs.getString(4));
                stu.setEthnicity(rs.getString(5));
                stu.setPoli_status(rs.getString(6));
                stu.setPosition(rs.getString(7));
                stu.setCls_id(rs.getInt(8));
                stu.setDor_id(rs.getString(9));
                return stu;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Attendance checkATT(String stu_id, String date){
        try{
            pstmt=ct.prepareStatement("select * from attendance where stu_id=? and date = ?");
            pstmt.setString(1,stu_id);
            pstmt.setString(2,date);
            ResultSet rs=pstmt.executeQuery();
            Attendance att=new Attendance();
            while(rs.next()){
                att.setStu_id(rs.getString(1));
                att.setDate(rs.getDate(2));
                att.setIfatt(rs.getString(3));
                return att;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public Activity checkActivity(int cls_id, String name, String date, String place, String content) {
        try{
            pstmt=ct.prepareStatement("select * from activity where cls_id = ? and name = ? and date = ? and place = ? and content = ?");
            pstmt.setInt(1,cls_id);
            pstmt.setString(2,name);
            pstmt.setString(3,date);
            pstmt.setString(4,place);
            pstmt.setString(5,content);
            ResultSet rs=pstmt.executeQuery();
            Activity activity=new Activity();
            while(rs.next()){
                activity.setCls_id(rs.getInt(1));
                activity.setName(rs.getString(2));
                activity.setDate(rs.getDate(3));
                activity.setPlace(rs.getString(4));
                activity.setContent(rs.getString(5));
                return activity;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Reward checkReward(String stu_id, String date, String type, String reason) {
        try{
            pstmt=ct.prepareStatement("select * from reward where stu_id = ? and date = ? and type = ? and reason = ?");
            pstmt.setString(1,stu_id);
            pstmt.setString(2,date);
            pstmt.setString(3,type);
            pstmt.setString(4,reason);
            ResultSet rs=pstmt.executeQuery();
            Reward reward = new Reward();

            while(rs.next()){
                reward.setStu_id(rs.getString(1));
                reward.setDate(rs.getDate(2));
                reward.setType(rs.getString(3).charAt(0));
                reward.setReason(rs.getString(4));
                return reward;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Fund checkFund(int cls_id, String date) {
        try{
            pstmt=ct.prepareStatement("select * from fund where cls_id = ? and date = ? ");
            pstmt.setInt(1,cls_id);
            pstmt.setString(2,date);
            ResultSet rs=pstmt.executeQuery();
            Fund fund = new Fund();

            while(rs.next()){
                fund.setCls_id(rs.getInt(1));
                fund.setDate(rs.getDate(2));
                fund.setAmount(rs.getFloat(3));
                fund.setBalance(rs.getFloat(4));
                fund.setRemark(rs.getString(5));
                return fund;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Plan checkPlan(String tea_id, String time) {
        try{
            Timestamp timestamp = Timestamp.valueOf(time);
            pstmt=ct.prepareStatement("select * from plan where tea_id = ? and time = ? ");
            pstmt.setString(1,tea_id);
            pstmt.setTimestamp(2,timestamp);
            ResultSet rs=pstmt.executeQuery();
            Plan plan = new Plan();
            while(rs.next()){
                plan.setTea_id(rs.getString(1));
                plan.setTime(rs.getTimestamp(2));
                plan.setPlan(rs.getString(3));
                return plan;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Summary checkSummary(String tea_id, String date) {
        try{
            pstmt=ct.prepareStatement("select * from summary where tea_id = ? and date = ? ");
            pstmt.setString(1,tea_id);
            pstmt.setString(2,date);
            ResultSet rs=pstmt.executeQuery();
            Summary summary = new Summary();
            while(rs.next()){
                summary.setTea_id(rs.getString(1));
                summary.setDate(rs.getDate(2));
                summary.setSummary(rs.getString(3));
                return summary;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Meetings checkMeeting(int cls_id, String date) {
        try{
            pstmt=ct.prepareStatement("select * from meetings where cls_id = ? and date = ? ");
            pstmt.setInt(1,cls_id);
            pstmt.setString(2,date);
            ResultSet rs=pstmt.executeQuery();
            Meetings meeting = new Meetings();
            while(rs.next()){
                meeting.setCls_id(rs.getInt(1));
                meeting.setDate(rs.getDate(2));
                meeting.setTheme(rs.getString(3));
                meeting.setContent(rs.getString(4));
                return meeting;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Stutalk checkTalking(String stu_id, String date) {
        try{
            pstmt=ct.prepareStatement("select * from stutalk where stu_id = ? and date = ? ");
            pstmt.setString(1,stu_id);
            pstmt.setString(2,date);
            ResultSet rs=pstmt.executeQuery();
            Stutalk talking = new Stutalk();
            while(rs.next()){
                talking.setStu_id(rs.getString(1));
                talking.setDate(rs.getDate(2));
                talking.setContent(rs.getString(3));
                return talking;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Comment checkComment(String stu_id, String date) {
        try{
            pstmt=ct.prepareStatement("select * from comment where stu_id = ? and date = ? ");
            pstmt.setString(1,stu_id);
            pstmt.setString(2,date);
            ResultSet rs=pstmt.executeQuery();
            Comment comment = new Comment();
            while(rs.next()){
                comment.setStu_id(rs.getString(1));
                comment.setDate(rs.getDate(2));
                comment.setSelf_cmt(rs.getString(3));
                comment.setCls_adv(rs.getString(4));
                return comment;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Visit checkVisit(String stu_id, String date) {
        try{
            pstmt=ct.prepareStatement("select * from visit where stu_id = ? and date = ? ");
            pstmt.setString(1,stu_id);
            pstmt.setString(2,date);
            ResultSet rs=pstmt.executeQuery();
            Visit visit = new Visit();
            while(rs.next()){
                visit.setStu_id(rs.getString(1));
                visit.setDate(rs.getDate(2));
                visit.setContent(rs.getString(3));
                return visit;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Grade checkGrade(String stu_id, String date) {
        try{
            pstmt=ct.prepareStatement("select * from grade where stu_id = ? and date = ? ");
            pstmt.setString(1,stu_id);
            pstmt.setString(2,date);
            ResultSet rs=pstmt.executeQuery();
            Grade grade = new Grade();
            while(rs.next()){
                grade.setStu_id(rs.getString(1));
                grade.setDate(rs.getDate(2));
                grade.setChinse(rs.getInt(3));
                grade.setMath(rs.getInt(4));
                grade.setEnglish(rs.getInt(5));
                return grade;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //修改密码
    public void updatePwd(String id,String pwd){
        try{
            pstmt=ct.prepareStatement("update teacher set pwd = ? where tea_id=?");
            pstmt.setString(1,pwd);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            pstmt=ct.prepareStatement("update admin set password = ? where username=?");
            pstmt.setString(1,pwd);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean updateStudent(String id,String name,int age,String sex,String ethnicity,String poli_status,String position,int cls_id,String dor_id){
        try{
            pstmt=ct.prepareStatement("update Student set name = ? , age = ? , sex = ? , ethnicity = ? , political_status = ? , position = ? , cls_id = ? , dor_id = ? where stu_id=?");
            pstmt.setString(1,name);
            pstmt.setInt(2,age);
            pstmt.setString(3, sex);
            pstmt.setString(4, ethnicity);
            pstmt.setString(5, poli_status);
            pstmt.setString(6, position);
            pstmt.setInt(7, cls_id);
            pstmt.setString(8, dor_id);
            pstmt.setString(9,id);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void updatePosition(String id,String position){
        try{
            pstmt=ct.prepareStatement("update Student set position = ? where stu_id=?");
            pstmt.setString(1,position);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void updateDor(String id,String dor_id){
        try{
            pstmt=ct.prepareStatement("update Student set dor_id = ? where stu_id=?");
            pstmt.setString(1,dor_id);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void updateAtt(String id,String date,String att){
        try{
            pstmt=ct.prepareStatement("update attendance set ifatt = ? where stu_id=? and date = ?");
            pstmt.setString(1,att);
            pstmt.setString(2, id);
            pstmt.setString(3, date);
            pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //修改成绩
    public boolean updateGrade(String id,String date,float chinese,float math,float english){
        try{
            pstmt=ct.prepareStatement("update grade set chinese = ?,math = ? ,english = ? where stu_id=? and date=?");
            pstmt.setFloat(1,chinese);
            pstmt.setFloat(2,math);
            pstmt.setFloat(3,english);
            pstmt.setString(4,id);
            pstmt.setString(5,date);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateActivity(int cls_id, String name, String date, String place, String content) {
        try{
            pstmt=ct.prepareStatement("update activity set date = ?,place = ? ,content = ? where cls_id=? and name = ?");
            pstmt.setString(1,date);
            pstmt.setString(2,place);
            pstmt.setString(3,content);
            pstmt.setInt(4,cls_id);
            pstmt.setString(5,name);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateReward(String stu_id, String date, String type, String reason) {
        try{
            pstmt=ct.prepareStatement("update reward set type = ? ,reason = ? where date=? and stu_id = ?");
            pstmt.setString(1,type);
            pstmt.setString(2,reason);
            pstmt.setString(3,date);
            pstmt.setString(4,stu_id);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateFund(int cls_id, String date, float amount, float balance, String remark) {
        try{
            pstmt=ct.prepareStatement("update fund set amount = ? ,balance = ? ,remark = ? where cls_id=? and date = ?");
            pstmt.setFloat(1,amount);
            pstmt.setFloat(2,balance);
            pstmt.setString(3,remark);
            pstmt.setInt(4,cls_id);
            pstmt.setString(5,date);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePlan(String tea_id, String time, String planstr) {
        try{
            pstmt=ct.prepareStatement("update plan set plan = ? where tea_id=? and time = ?");
            pstmt.setString(1,planstr);
            pstmt.setString(2,tea_id);
            Timestamp timestamp = Timestamp.valueOf(time);
            pstmt.setTimestamp(3,timestamp);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateSummary(String tea_id, String date, String summarystr) {
        try{
            pstmt=ct.prepareStatement("update summary set summary = ? where tea_id=? and date = ?");
            pstmt.setString(1,summarystr);
            pstmt.setString(2,tea_id);
            pstmt.setString(3,date);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateMeeting(int cls_id, String date, String theme, String content) {
        try{
            pstmt=ct.prepareStatement("update meetings set theme = ?,content = ? where cls_id=? and date = ?");
            pstmt.setString(1,theme);
            pstmt.setString(2,content);
            pstmt.setInt(3,cls_id);
            pstmt.setString(4,date);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateTalking(String stu_id, String date, String content) {
        try{
            pstmt=ct.prepareStatement("update stutalk set content = ? where stu_id=? and date = ?");
            pstmt.setString(1,content);
            pstmt.setString(2,stu_id);
            pstmt.setString(3,date);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //修改评论
    public boolean updateComment(String id,String date,String self_cmt,String cls_adv){
        try{
            pstmt=ct.prepareStatement("update comment set self_cmt = ?,cls_adv = ? where stu_id=? and date=?");
            pstmt.setString(1,self_cmt);
            pstmt.setString(2,cls_adv );
            pstmt.setString(3,id);
            pstmt.setString(4,date);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //修改家访
    public boolean updateVisit(String id,String date,String content){
        try{
            pstmt=ct.prepareStatement("update visit set content = ? where stu_id=? and date=?");
            pstmt.setString(1,content);
            pstmt.setString(2,id);
            pstmt.setString(3,date);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertTeacher(String name,String pwd){
        try{
            pstmt=ct.prepareStatement("insert into teacher (teacher_name, teacher_password) values(?,?)");
            pstmt.setString(1, name);
            pstmt.setString(2, pwd);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertTeacher(String tea_id,String name,String pwd,String mail,int cls_id){
        try{
            pstmt=ct.prepareStatement("insert into teacher values(?,?,?,?,?)");
            pstmt.setString(1, tea_id);
            pstmt.setString(2, name);
            pstmt.setString(3, pwd);
            pstmt.setString(4, mail);
            pstmt.setInt(5, cls_id);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertStudent(String id,String name,int age,String sex,String ethnicity,String poli_status,String position,int cls_id,String dor_id){
        try{
            pstmt=ct.prepareStatement("insert into student values(?,?,?,?,?,?,?,?,?)");
            pstmt.setString(1,id);
            pstmt.setString(2,name);
            pstmt.setInt(3,age);
            pstmt.setString(4, sex);
            pstmt.setString(5, ethnicity);
            pstmt.setString(6, poli_status);
            pstmt.setString(7, position);
            pstmt.setInt(8, cls_id);
            pstmt.setString(9, dor_id);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //插入成绩：
    public boolean insertGrade(String id,String date,float chinese,float math,float english){
        try{
            pstmt=ct.prepareStatement("insert into grade values(?,?,?,?,?)");
            pstmt.setString(1,id);
            pstmt.setString(2,date);
            pstmt.setFloat(3,chinese);
            pstmt.setFloat(4,math);
            pstmt.setFloat(5,english);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //插入出勤情况：
    public boolean insertATT(String id,String date,String ifatt){
        try{
            pstmt=ct.prepareStatement("insert into attendance values(?,?,?)");
            pstmt.setString(1,id);
            pstmt.setString(2,date);
            pstmt.setString(3,ifatt);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertActivity(int cls_id, String name, String date, String place, String content) {
        try{
            pstmt=ct.prepareStatement("insert into activity values(?,?,?,?,?)");
            pstmt.setInt(1,cls_id);
            pstmt.setString(2,name);
            pstmt.setString(3,date);
            pstmt.setString(4,place);
            pstmt.setString(5,content);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertReward(String stu_id, String date, String type, String reason) {
        try{
            pstmt=ct.prepareStatement("insert into reward values(?,?,?,?)");
            pstmt.setString(1,stu_id);
            pstmt.setString(2,date);
            pstmt.setString(3,type);
            pstmt.setString(4,reason);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertFund(int cls_id, String date, float amount, float balance, String remark) {
        try{
            pstmt=ct.prepareStatement("insert into fund values(?,?,?,?,?)");
            pstmt.setInt(1,cls_id);
            pstmt.setString(2,date);
            pstmt.setFloat(3,amount);
            pstmt.setFloat(4,balance);
            pstmt.setString(5,remark);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertPlan(String tea_id, String time, String planstr) {
        try{
            pstmt=ct.prepareStatement("insert into plan values(?,?,?)");
            pstmt.setString(1,tea_id);
            pstmt.setString(2,time);
            pstmt.setString(3,planstr);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertSummary(String tea_id, String date, String summarystr) {
        try{
            pstmt=ct.prepareStatement("insert into summary values(?,?,?)");
            pstmt.setString(1,tea_id);
            pstmt.setString(2,date);
            pstmt.setString(3,summarystr);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertMeeting(int cls_id, String date, String theme, String content) {
        try{
            pstmt=ct.prepareStatement("insert into meetings values(?,?,?,?)");
            pstmt.setInt(1,cls_id);
            pstmt.setString(2,date);
            pstmt.setString(3,theme);
            pstmt.setString(4,content);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertTalking(String stu_id, String date, String content) {
        try{
            pstmt=ct.prepareStatement("insert into stutalk values(?,?,?)");
            pstmt.setString(1,stu_id);
            pstmt.setString(2,date);
            pstmt.setString(3,content);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //添加评论
    public boolean insertComment(String id,String date,String self_cmt,String cls_adv){
        try{
            pstmt=ct.prepareStatement("insert into comment values(?,?,?,?)");
            pstmt.setString(1,id);
            pstmt.setString(2,date );
            pstmt.setString(3,self_cmt);
            pstmt.setString(4,cls_adv);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //添加家访
    public boolean insertVisit(String id,String date,String content){
        try{
            pstmt=ct.prepareStatement("insert into visit values(?,?,?)");
            pstmt.setString(1,id);
            pstmt.setString(2,date);
            pstmt.setString(3,content);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList SearchInCadre(String keyword,int cls_id){
        try{
            pstmt=ct.prepareStatement("select * from Student where cls_id = ? and (stu_id = ? or name = ? or position = ?)");
            pstmt.setInt(1,cls_id);
            pstmt.setString(2,keyword);
            pstmt.setString(3,keyword);
            pstmt.setString(4,keyword);

            ArrayList StuAL=new ArrayList();
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                Student stu=new Student();
                stu.setId(rs.getString(1));
                stu.setName(rs.getString(2));
                stu.setAge(rs.getInt(3));
                stu.setSex(rs.getString(4));
                stu.setEthnicity(rs.getString(5));
                stu.setPoli_status(rs.getString(6));
                stu.setPosition(rs.getString(7));
                stu.setCls_id(rs.getInt(8));
                stu.setDor_id(rs.getString(9));
                StuAL.add(stu);
            }
            return StuAL;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList SearchInDor(String keyword,int cls_id){
        try{
            pstmt=ct.prepareStatement("select * from Student where cls_id = ? and (stu_id = ? or name = ? or sex = ? or dor_id = ?)");
            pstmt.setInt(1,cls_id);
            pstmt.setString(2,keyword);
            pstmt.setString(3,keyword);
            pstmt.setString(4,keyword);
            pstmt.setString(5,keyword);

            ArrayList StuAL=new ArrayList();
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                Student stu=new Student();
                stu.setId(rs.getString(1));
                stu.setName(rs.getString(2));
                stu.setAge(rs.getInt(3));
                stu.setSex(rs.getString(4));
                stu.setEthnicity(rs.getString(5));
                stu.setPoli_status(rs.getString(6));
                stu.setPosition(rs.getString(7));
                stu.setCls_id(rs.getInt(8));
                stu.setDor_id(rs.getString(9));
                StuAL.add(stu);
            }
            return StuAL;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList SearchInATT(String keyword,int cls_id){
        try{
            pstmt=ct.prepareStatement("select * from attendance where stu_id IN(select stu_id from Student where cls_id = ? ) and (stu_id = ? or date = ? or ifatt = ? or stu_id IN(select stu_id from Student where name = ? ))");
            pstmt.setInt(1,cls_id);
            pstmt.setString(2,keyword);
            pstmt.setString(3,keyword);
            pstmt.setString(4,keyword);
            pstmt.setString(5,keyword);

            ArrayList AttAL=new ArrayList();
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                Attendance att = new Attendance();
                att.setStu_id(rs.getString(1));
                att.setDate(rs.getDate(2));
                att.setIfatt(rs.getString(3));
                AttAL.add(att);
            }
            return AttAL;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList SearchInActivity(String keyword, int cls_id) {
        try{
            pstmt=ct.prepareStatement("select * from activity where cls_id = ? and (name = ? or date = ? or place = ? or content = ?)");
            pstmt.setInt(1,cls_id);
            pstmt.setString(2,keyword);
            pstmt.setString(3,keyword);
            pstmt.setString(4,keyword);
            pstmt.setString(5,keyword);

            ArrayList ActivityAL=new ArrayList();
            ResultSet rs=pstmt.executeQuery();

            while(rs.next()){
                Activity activity = new Activity();
                activity.setCls_id(rs.getInt(1));
                activity.setName(rs.getString(2));
                activity.setDate(rs.getDate(3));
                activity.setPlace(rs.getString(4));
                activity.setContent(rs.getString(5));
                ActivityAL.add(activity);

            }
            return ActivityAL;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList SearchInPlan(String keyword, String tea_id) {
        try{
            pstmt=ct.prepareStatement("select * from plan where tea_id = ? and (time = ? or plan = ?)");
            pstmt.setString(1,tea_id);
            if(keyword.length()>5 && keyword.charAt(4) == '-'){
                Timestamp time = Timestamp.valueOf(keyword);
                pstmt.setTimestamp(2,time);
            }else {
                pstmt.setString(2,keyword);
            }
            pstmt.setString(3,keyword);
            ArrayList PlanAL=new ArrayList();
            ResultSet rs=pstmt.executeQuery();

            while(rs.next()){
                Plan plan = new Plan();
                plan.setTea_id(rs.getString(1));
                plan.setTime(rs.getTimestamp(2));
                plan.setPlan(rs.getString(3));
                PlanAL.add(plan);
            }
            return PlanAL;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList SearchInSummary(String keyword, String tea_id) {
        try{
            pstmt=ct.prepareStatement("select * from summary where tea_id = ? and (date = ? or summary = ?)");
            pstmt.setString(1,tea_id);
            pstmt.setString(2,keyword);
            pstmt.setString(3,keyword);

            ArrayList SummaryAL=new ArrayList();
            ResultSet rs=pstmt.executeQuery();

            while(rs.next()){
                Summary summary = new Summary();
                summary.setTea_id(rs.getString(1));
                summary.setDate(rs.getDate(2));
                summary.setSummary(rs.getString(3));
                SummaryAL.add(summary);
            }
            return SummaryAL;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList SearchInMeeting(String keyword, int cls_id) {
        try{
            pstmt=ct.prepareStatement("select * from meetings where cls_id = ? and (date = ? or theme = ? or content = ?)");
            pstmt.setInt(1,cls_id);
            pstmt.setString(2,keyword);
            pstmt.setString(3,keyword);
            pstmt.setString(4,keyword);

            ArrayList MeetingAL=new ArrayList();
            ResultSet rs=pstmt.executeQuery();

            while(rs.next()){
                Meetings meetings = new Meetings();
                meetings.setCls_id(rs.getInt(1));
                meetings.setDate(rs.getDate(2));
                meetings.setTheme(rs.getString(3));
                meetings.setContent(rs.getString(4));
                MeetingAL.add(meetings);
            }
            return MeetingAL;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList SearchInTalking(String keyword, int cls_id) {
        try{
            pstmt=ct.prepareStatement("select * from stutalk where stu_id in(select stu_id from student where cls_id = ?) and (stu_id = ? or date = ? or content = ? or stu_id = (select stu_id from student where name = ?))");
            pstmt.setInt(1,cls_id);
            pstmt.setString(2,keyword);
            pstmt.setString(3,keyword);
            pstmt.setString(4,keyword);
            pstmt.setString(5,keyword);

            ArrayList TalkingAL=new ArrayList();
            ResultSet rs=pstmt.executeQuery();

            while(rs.next()) {
                Stutalk talking = new Stutalk();
                talking.setStu_id(rs.getString(1));
                talking.setDate(rs.getDate(2));
                talking.setContent(rs.getString(3));
                TalkingAL.add(talking);
            }
            return TalkingAL;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList SearchStuInfo(String keyword, int cls_id) {
        try{
            pstmt=ct.prepareStatement("select * from Student where cls_id = ? and (stu_id = ? or name = ? or age = ? or sex = ? or ethnicity = ? or political_status = ? or position = ?)");
            pstmt.setInt(1,cls_id);
            pstmt.setString(2,keyword);
            pstmt.setString(3,keyword);
            pstmt.setString(4,keyword);
            pstmt.setString(5,keyword);
            pstmt.setString(6,keyword);
            pstmt.setString(7,keyword);
            pstmt.setString(8,keyword);

            ArrayList StuInfoAL=new ArrayList();
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                Student stu=new Student();
                stu.setId(rs.getString(1));
                stu.setName(rs.getString(2));
                stu.setAge(rs.getInt(3));
                stu.setSex(rs.getString(4));
                stu.setEthnicity(rs.getString(5));
                stu.setPoli_status(rs.getString(6));
                stu.setPosition(rs.getString(7));
                stu.setCls_id(rs.getInt(8));
                stu.setDor_id(rs.getString(9));
                StuInfoAL.add(stu);
            }
            return StuInfoAL;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList SearchStudent(String keyword) {
        try{
            pstmt=ct.prepareStatement("select * from Student where cls_id = ? or stu_id = ? or name = ? or age = ? or sex = ? or ethnicity = ? or political_status = ? ");
            pstmt.setString(1,keyword);
            pstmt.setString(2,keyword);
            pstmt.setString(3,keyword);
            pstmt.setString(4,keyword);
            pstmt.setString(5,keyword);
            pstmt.setString(6,keyword);
            pstmt.setString(7,keyword);

            ArrayList StuInfoAL=new ArrayList();
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                Student stu=new Student();
                stu.setId(rs.getString(1));
                stu.setName(rs.getString(2));
                stu.setAge(rs.getInt(3));
                stu.setSex(rs.getString(4));
                stu.setEthnicity(rs.getString(5));
                stu.setPoli_status(rs.getString(6));
                stu.setPosition(rs.getString(7));
                stu.setCls_id(rs.getInt(8));
                stu.setDor_id(rs.getString(9));
                StuInfoAL.add(stu);
            }
            return StuInfoAL;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList SearchTeacher(String keyword) {
        try{
            pstmt=ct.prepareStatement("select * from teacher where tea_id = ? or name = ? or mail = ? or cls_id = ? ");
            pstmt.setString(1,keyword);
            pstmt.setString(2,keyword);
            pstmt.setString(3,keyword);
            if(keyword.equals("0")){
                pstmt.setString(4,"null");
            }else {
                pstmt.setString(4,keyword);
            }

            ArrayList TeacherAL=new ArrayList();
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                Teacher teacher = new Teacher();
                teacher.setId(rs.getString(1));
                teacher.setName(rs.getString(2));
                teacher.setPassword(rs.getString(3));
                teacher.setEmail(rs.getString(4));
                teacher.setCls_id(rs.getInt(5));
                TeacherAL.add(teacher);
            }
            return TeacherAL;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList SearchComment(String keyword, int cls_id) {
        try{
            pstmt=ct.prepareStatement("select * from comment where stu_id in(select stu_id from student where cls_id = ?) and (stu_id = ? or stu_id = (select stu_id from student where name = ?) or date = ? or self_cmt = ? or cls_adv = ?)");
            pstmt.setInt(1,cls_id);
            pstmt.setString(2,keyword);
            pstmt.setString(3,keyword);
            pstmt.setString(4,keyword);
            pstmt.setString(5,keyword);
            pstmt.setString(6,keyword);

            ArrayList CommentAL=new ArrayList();
            ResultSet rs=pstmt.executeQuery();

            while(rs.next()) {
                Comment comment = new Comment();
                comment.setStu_id(rs.getString(1));
                comment.setDate(rs.getDate(2));
                comment.setSelf_cmt(rs.getString(3));
                comment.setCls_adv(rs.getString(4));
                CommentAL.add(comment);
            }
            return CommentAL;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList SearchVisit(String keyword, int cls_id) {
        try{
            pstmt=ct.prepareStatement("select * from visit where stu_id in(select stu_id from student where cls_id = ?) and (stu_id = ? or stu_id = (select stu_id from student where name = ?) or date = ? or content = ?)");
            pstmt.setInt(1,cls_id);
            pstmt.setString(2,keyword);
            pstmt.setString(3,keyword);
            pstmt.setString(4,keyword);
            pstmt.setString(5,keyword);

            ArrayList VisitAL=new ArrayList();
            ResultSet rs=pstmt.executeQuery();

            while(rs.next()) {
                Visit visit = new Visit();
                visit.setStu_id(rs.getString(1));
                visit.setDate(rs.getDate(2));
                visit.setContent(rs.getString(3));
                VisitAL.add(visit);
            }
            return VisitAL;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList SearchGrade(String keyword, int cls_id) {
        try{
            pstmt=ct.prepareStatement("select * from grade where stu_id in(select stu_id from student where cls_id = ?) and (stu_id = ? or date = ? or Chinese = ? or Math = ? or English = ?)");
            pstmt.setInt(1,cls_id);
            pstmt.setString(2,keyword);
            pstmt.setString(3,keyword);
            pstmt.setString(4,keyword);
            pstmt.setString(5,keyword);
            pstmt.setString(6,keyword);

            ArrayList gradeAL=new ArrayList();
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                Grade grade=new Grade();
                grade.setStu_id(rs.getString(1));
                grade.setDate(rs.getDate(2));
                grade.setChinse(rs.getFloat(3));
                grade.setMath(rs.getFloat(4));
                grade.setEnglish(rs.getFloat(5));
                gradeAL.add(grade);
            }
            return gradeAL;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList SearchInReward(String keyword, int cls_id) {
        try{
            pstmt=ct.prepareStatement("select * from reward where stu_id in(select stu_id from student where cls_id = ?) and (stu_id = ? or stu_id = (select stu_id from student where name = ?) or date = ? or type = ? or reason = ?)");
            pstmt.setInt(1,cls_id);
            pstmt.setString(2,keyword);
            pstmt.setString(3,keyword);
            pstmt.setString(4,keyword);
            pstmt.setString(5,keyword);
            pstmt.setString(6,keyword);

            ArrayList RewardAL=new ArrayList();
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                Reward reward = new Reward();
                reward.setStu_id(rs.getString(1));
                reward.setDate(rs.getDate(2));
                reward.setType(rs.getString(3).charAt(0));
                reward.setReason(rs.getString(4));
                RewardAL.add(reward);
            }
            return RewardAL;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList SearchInFund(String keyword, int cls_id) {
        try{
            pstmt=ct.prepareStatement("select * from fund where cls_id = ? and (date = ? or amount = ? or balance = ? or remark = ?)");
            pstmt.setInt(1,cls_id);
            pstmt.setString(2,keyword);
            pstmt.setString(3,keyword);
            pstmt.setString(4,keyword);
            pstmt.setString(5,keyword);

            ArrayList FundAL=new ArrayList();
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                Fund fund = new Fund();
                fund.setCls_id(rs.getInt(1));
                fund.setDate(rs.getDate(2));
                fund.setAmount(Float.parseFloat(rs.getString(3)));
                fund.setBalance(Float.parseFloat(rs.getString(4)));
                fund.setRemark(rs.getString(5));
                FundAL.add(fund);
            }
            return FundAL;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String getName(String id)  {
        String name=null;
        try {
            pstmt=ct.prepareStatement("select name from student where stu_id=?");
            pstmt.setString(1, id);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next())  {
                name=rs.getString(1);
            }
            return name;
        } catch(Exception e)  {
            e.printStackTrace();
            return null;
        }
    }

    public boolean DeletePlan(String tea_id, String time) {
        try{
            Timestamp timestamp = Timestamp.valueOf(time);
            pstmt=ct.prepareStatement("delete from plan where tea_id = ? and time = ?");
            pstmt.setString(1,tea_id);
            pstmt.setTimestamp(2,timestamp);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean DeleteStudent(String stu_id) {
        try{
            pstmt=ct.prepareStatement("delete from student where stu_id = ?");
            pstmt.setString(1,stu_id);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean DeleteTeacher(String tea_id) {
        try{
            pstmt=ct.prepareStatement("delete from teacher where tea_id = ?");
            pstmt.setString(1,tea_id);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public boolean updateTeaPwd(String pwd, Teacher teacher) {
        try{
            pstmt=ct.prepareStatement("update teacher set pwd = ? where tea_id=?");
            pstmt.setString(1,pwd);
            pstmt.setString(2, teacher.getId());
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public boolean updateAdminPwd(String pwd) {
        try{
            pstmt=ct.prepareStatement("update admin set pwd = ? where username='admin'");
            pstmt.setString(1,pwd);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public String getTeaPwd(String id) {
        String pwd=null;
        try{
            pstmt=ct.prepareStatement("select pwd from teacher where tea_id=? ");
            pstmt.setString(1, id);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next())  {
                pwd=rs.getString(1);
            }
            return pwd;
        } catch(Exception e)  {
            e.printStackTrace();
            return null;
        }

    }
    public String getAdminPwd() {
        String pwd=null;
        try{
            pstmt=ct.prepareStatement("select password from admin where username='admin' ");
            ResultSet rs=pstmt.executeQuery();
            while(rs.next())  {
                pwd=rs.getString(1);
            }
            return pwd;
        } catch(Exception e)  {
            e.printStackTrace();
            return null;
        }

    }

    public boolean updateEmail(String email, Teacher teacher) {
        try{
            pstmt=ct.prepareStatement("update teacher set mail = ? where tea_id=?");
            pstmt.setString(1,email);
            pstmt.setString(2, teacher.getId());
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateAEmail(String email) {
        try{
            pstmt=ct.prepareStatement("update admin set a_email = ? where username='admin'");
            pstmt.setString(1,email);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public boolean insertCmt(String comment, String date, String id) {
        try{
            pstmt=ct.prepareStatement("insert into syscmt values(?,?,?)");
            pstmt.setString(1,id);
            pstmt.setString(2,date);
            pstmt.setString(3,comment);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public InputStream getCurriculum(int cls_id) {
        try {
            pstmt = ct.prepareStatement("select curriculum from curriculum where cls_id=?");
            pstmt.setInt(1, cls_id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                InputStream inputStream = rs.getBinaryStream(1);
                return inputStream;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public boolean updateCurriculum(InputStream imageInputStream,int cls_id) {
        try{
            pstmt=ct.prepareStatement("update curriculum set curriculum=? where cls_id=?");
            pstmt.setBinaryStream(1,imageInputStream);
            pstmt.setInt(2,cls_id);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public InputStream getDuty(int cls_id) {
        try {
            pstmt = ct.prepareStatement("select dutyform from duty where cls_id=?");
            pstmt.setInt(1, cls_id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                InputStream inputStream = rs.getBinaryStream(1);
                return inputStream;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateDuty(InputStream imageInputStream, int cls_id) {
        try{
            pstmt=ct.prepareStatement("update duty set dutyform=? where cls_id=?");
            pstmt.setBinaryStream(1,imageInputStream);
            pstmt.setInt(2,cls_id);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertAdminCmt(String comment, String date) {
        try{
            pstmt=ct.prepareStatement("insert into sys_admincmt values(?,?)");
            pstmt.setString(1,date);
            pstmt.setString(2,comment);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

}