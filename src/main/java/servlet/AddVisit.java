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

public class AddVisit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AddVisit");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("utf-8");
        resp.setContentType("text/plain; charset=UTF-8");

        String id = req.getParameter("stu_id");
        String name = req.getParameter("name");
        String date = req.getParameter("date");
        String content =req.getParameter("content");

        System.out.println(id);
        System.out.println(name);
        System.out.println(date);
        System.out.println(content);

        DB db = new DB();
        //session中存放post传给request的值
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        //存在该学号的学生
        if (db.checkStudent(id)!=null) {
            Student stu=db.checkStudent(id);
            //检查填写的姓名、性别是否与该学生信息一致
            if(stu.getName().equals(name)) {
                //不存在该家访信息
                if(db.checkVisit(id,date)==null){
                    if (db.insertVisit(id,date,content)) {
                        ArrayList NowVisitAL = db.findVisitInClass(teacher);
                        session.setAttribute("VisitAL", NowVisitAL);
                        req.setAttribute("AddVisit", "增加成功");
                    } else {
                        req.setAttribute("AddVisit", "增加失败：信息填写有误");
                    }
                }else {
                    req.setAttribute("AddVisit", "添加失败：已存在该家访信息");
                }
            }else {
                req.setAttribute("AddVisit", "错误：学生信息填写错误");
            }
        } else {
            //不存在该学生
            req.setAttribute("AddVisit","错误：找不到该学生");
        }

        System.out.println(req.getAttribute("AddVisit"));
        PrintWriter writer = resp.getWriter();
        writer.print(req.getAttribute("AddVisit"));
        writer.flush();
        writer.close();
    }
}
