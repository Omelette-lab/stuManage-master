package servlet;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Admin;
import model.Teacher;
import DB.DB;

/**
 * Servlet implementation class MainServlet
 */

public class Login extends HttpServlet {

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("gb2312");
        response.setContentType("gb2312");
        String id=request.getParameter("id");
        String pwd=request.getParameter("pwd");
        String identity=request.getParameter("identity");

        System.out.println(id);
        System.out.println(pwd);
        System.out.println(identity);

        DB db=new DB();
        HttpSession session=request.getSession();

        if(identity.equals("teacher")){
            //如果选中的是老师身份，则向数据库核对
            Teacher teacher = db.checkTeacher(id,pwd);
            if(teacher!=null){//数据库中存在该账号
                session.setAttribute("teacher", teacher);  //把老师存入session中，方便将信息传入下一页面
                ArrayList StuAL=db.findStuInfoInClass(teacher);
                ArrayList attAL=db.findAttInfoInClass(teacher);
                ArrayList ActivityAL=db.findActivityInfoInClass(teacher);
                ArrayList PlanAL=db.findPlanInfoInClass(teacher);
                ArrayList SummaryAL=db.findSummaryInfoInClass(teacher);
                ArrayList MeetingAL=db.findMeetingInfoInClass(teacher);
                ArrayList TalkingAL=db.findTalkingInfoInClass(teacher);
                ArrayList CommentAL=db.findCommentInClass(teacher);
                ArrayList VisitAL=db.findVisitInClass(teacher);
                ArrayList GradeAL=db.findStuGrade(teacher);
                ArrayList RewardAL=db.findReward(teacher);
                ArrayList FundAL=db.findFund(teacher);
                InputStream curriculumInputStream = db.getCurriculum(teacher.getCls_id());
                session.setAttribute("curriculum", curriculumInputStream);
                InputStream dutyStream = db.getDuty(teacher.getCls_id());
                session.setAttribute("duty", dutyStream);
                session.setAttribute("AllStuAL", StuAL);
                session.setAttribute("StuAL", StuAL);
                session.setAttribute("AllAttAL", attAL);
                session.setAttribute("AttAL", attAL);
                session.setAttribute("AllActivityAL", ActivityAL);
                session.setAttribute("ActivityAL", ActivityAL);
                session.setAttribute("AllPlanAL", PlanAL);
                session.setAttribute("PlanAL", PlanAL);
                session.setAttribute("AllSummaryAL", SummaryAL);
                session.setAttribute("SummaryAL", SummaryAL);
                session.setAttribute("AllMeetingAL", MeetingAL);
                session.setAttribute("MeetingAL", MeetingAL);
                session.setAttribute("AllTalkingAL", TalkingAL);
                session.setAttribute("TalkingAL", TalkingAL);
                session.setAttribute("AllCommentAL", CommentAL);
                session.setAttribute("CommentAL", CommentAL);
                session.setAttribute("AllVisitAL", VisitAL);
                session.setAttribute("VisitAL", VisitAL);
                session.setAttribute("AllGradeAL", GradeAL);
                session.setAttribute("GradeAL", GradeAL);
                session.setAttribute("AllRewardAL", RewardAL);
                session.setAttribute("RewardAL", RewardAL);
                session.setAttribute("AllFundAL", FundAL);
                session.setAttribute("FundAL", FundAL);
                response.sendRedirect("main_teacher.jsp");
            }
            else{
                request.setAttribute("login_Error","登录失败：教师工号或密码错误");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }
        }
        else if(identity.equals("admin")){
            Admin admin=db.checkAdmin(id,pwd);
            if(admin!=null){
                session.setAttribute("admin", admin);
                ArrayList AllStudent = db.findAllStudent();
                session.setAttribute("Student",AllStudent);
                ArrayList AllTeacher = db.findAllTeacher();
                session.setAttribute("Teacher",AllTeacher);
                response.sendRedirect("main_admin.jsp");
            }
            else{
                request.setAttribute("login_Error","登录失败：管理员用户名或密码错误");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }
        }
        else {
            request.setAttribute("login_Error","请填写完整信息");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

}