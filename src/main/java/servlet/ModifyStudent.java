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

import model.Student;
import DB.DB;
import model.Teacher;


/**
 * Servlet implementation class MainServlet
 */

public class ModifyStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ModifyStudent");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("utf-8");
        resp.setContentType("text/plain; charset=UTF-8");

        String id = req.getParameter("stu_id");
        String name = req.getParameter("name");
        String agestr = req.getParameter("age");
        String sex = req.getParameter("sex");
        String ethnicity = req.getParameter("ethnicity");
        String poli_status = req.getParameter("poli_status");
        int age = Integer.parseInt(agestr);

        System.out.println(id);
        System.out.println(name);
        System.out.println(age);
        System.out.println(sex);
        System.out.println(ethnicity);
        System.out.println(poli_status);

        DB db = new DB();
        //session中存放post传给request的值
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        //存在该学号的学生
        if (db.checkStudent(id)!=null) {
            Student stu =  db.checkStudent(id);
            if(db.updateStudent(id,name,age,sex,ethnicity,poli_status,stu.getPosition(),stu.getCls_id(),stu.getDor_id())){
                ArrayList NowStuAL = db.findStuInfoInClass(teacher);
                session.setAttribute("StuAL", NowStuAL);
                req.setAttribute("ModifyStudent", "修改成功");
            }else {
                req.setAttribute("ModifyStudent","修改错误：信息填写有误");
            }
        } else {
            //不存在该学生
            req.setAttribute("ModifyStudent","错误：找不到该学生");
        }
        System.out.println(req.getAttribute("ModifyStudent"));
        PrintWriter writer = resp.getWriter();
        writer.print(req.getAttribute("ModifyStudent"));
        writer.flush();
        writer.close();
    }
}