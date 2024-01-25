package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import DB.DB;
import model.Student;
import model.Stutalk;
import model.Teacher;


/**
 * Servlet implementation class MainServlet
 */

public class AddTalking extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AddTalking");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("utf-8");
        resp.setContentType("text/plain; charset=UTF-8");

        String stu_id = req.getParameter("stu_id");
        String name = req.getParameter("name");
        String date = req.getParameter("date");
        String content = req.getParameter("content");

        System.out.println(stu_id);
        System.out.println(name);
        System.out.println(date);
        System.out.println(content);

        DB db = new DB();
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        //存在该学号的学生
        if (db.checkStudent(stu_id)!=null) {
            Student stu=db.checkStudent(stu_id);
            //检查填写的姓名是否与该学生信息一致
            if(stu.getName().equals(name)){

                if(db.checkTalking(stu_id,date)==null){
                    if(db.insertTalking(stu_id,date,content)){
                        ArrayList NowTalkingAL = db.findTalkingInfoInClass(teacher);
                        session.setAttribute("TalkingAL", NowTalkingAL);
                        req.setAttribute("AddTalking", "添加成功");
                    }else {
                        req.setAttribute("AddTalking","添加失败：信息填写有误");
                    }
                } else{
                    req.setAttribute("AddTalking","失败：已存在该谈话记录");
                }
            } else {
                //填入的姓名与该学生信息不一致
                req.setAttribute("AddTalking","错误：学生信息填写错误");
            }
        } else {
            //不存在该学生
            req.setAttribute("AddTalking","错误：找不到该学生");
        }

        System.out.println(req.getAttribute("AddTalking"));
        PrintWriter writer = resp.getWriter();
        writer.print(req.getAttribute("AddTalking"));
        writer.flush();
        writer.close();
    }
}