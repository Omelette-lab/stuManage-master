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

public class AddStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AddStudent");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("utf-8");
        resp.setContentType("text/plain; charset=UTF-8");

        String id = req.getParameter("stu_id");
        String name = req.getParameter("name");
        String agestr = req.getParameter("age");
        int age = Integer.parseInt(agestr);
        String sex = req.getParameter("sex");
        String ethnicity = req.getParameter("ethnicity");
        String poli_status = req.getParameter("poli_status");
        String position = req.getParameter("position");
        String cls_idstr = req.getParameter("cls_id");
        int cls_id = Integer.parseInt(cls_idstr);
        String dor_id = req.getParameter("dor_id");

        System.out.println(id);
        System.out.println(name);
        System.out.println(age);
        System.out.println(sex);
        System.out.println(ethnicity);
        System.out.println(poli_status);
        System.out.println(position);
        System.out.println(cls_id);
        System.out.println(dor_id);

        DB db = new DB();
        //session中存放post传给request的值
        HttpSession session = req.getSession();

        //存在该学号的学生
        if (db.checkStudent(id)!=null) {
            req.setAttribute("AddStudent","错误：该学生学号已经存在");
        } else {
            //不存在该学生，则添加
            if(db.insertStudent(id,name,age,sex,ethnicity,poli_status,position,cls_id,dor_id)){
                ArrayList NowStudentAL = db.findAllStudent();
                session.setAttribute("Student", NowStudentAL);
                req.setAttribute("AddStudent","添加成功");
            }else{
                req.setAttribute("AddStudent","添加错误：信息填写有误");
            }
        }
        System.out.println(req.getAttribute("AddStudent"));
        PrintWriter writer = resp.getWriter();
        writer.print(req.getAttribute("AddStudent"));
        writer.flush();
        writer.close();
    }
}