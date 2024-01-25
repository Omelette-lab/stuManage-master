package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.DB;


/**
 * Servlet implementation class MainServlet
 */

public class AddTeacher extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AddTeacher");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("utf-8");
        resp.setContentType("text/plain; charset=UTF-8");

        String id = req.getParameter("tea_id");
        String name = req.getParameter("name");
        String pwd = req.getParameter("pwd");
        String mail = req.getParameter("mail");
        String cls_idstr =req.getParameter("cls_id");
        int cls_id = Integer.parseInt(cls_idstr);

        System.out.println(id);
        System.out.println(name);
        System.out.println(pwd);
        System.out.println(mail);
        System.out.println(cls_id);

        DB db = new DB();
        //session中存放post传给request的值
        HttpSession session = req.getSession();

        //存在该学号的学生
        if (db.checkTeacher(id)!=null) {
            req.setAttribute("AddTeacher","错误：该老师已经存在");
        } else {
            //不存在该学生，则添加
            if(db.insertTeacher(id,name,pwd,mail,cls_id)){
                ArrayList NowTeacherAL = db.findAllTeacher();
                session.setAttribute("Teacher", NowTeacherAL);
                req.setAttribute("AddTeacher","添加成功");
            }else{
                req.setAttribute("AddTeacher","添加错误：信息填写有误");
            }
        }
        System.out.println(req.getAttribute("AddTeacher"));
        PrintWriter writer = resp.getWriter();
        writer.print(req.getAttribute("AddTeacher"));
        writer.flush();
        writer.close();
    }
}