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

public class AddComment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AddComment");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("utf-8");
        resp.setContentType("text/plain; charset=UTF-8");

        String id = req.getParameter("stu_id");
        String name = req.getParameter("name");
        String date = req.getParameter("date");
        String self_cmt = req.getParameter("self_cmt");
        String cls_adv = req.getParameter("cls_adv");

        System.out.println(id);
        System.out.println(name);
        System.out.println(date);
        System.out.println(self_cmt);
        System.out.println(cls_adv);

        DB db = new DB();
        //session中存放post传给request的值
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        //存在该学号的学生
        if (db.checkStudent(id)!=null) {
            Student stu=db.checkStudent(id);
            //检查填写的姓名、性别是否与该学生信息一致
            if(stu.getName().equals(name)) {
                if(db.checkComment(id,date)==null){
                    if (db.insertComment(id,date,self_cmt,cls_adv)) {
                        ArrayList NowCommentAL = db.findCommentInClass(teacher);
                        session.setAttribute("CommentAL", NowCommentAL);
                        req.setAttribute("AddComment", "增加成功");
                    } else {
                        req.setAttribute("AddComment", "增加失败：信息填写有误");
                    }
                }else {
                    req.setAttribute("AddComment", "添加失败：已存在该评语");
                }
            }else {
                req.setAttribute("AddComment", "错误：学生信息填写错误");
            }
        } else {
            //不存在该学生
            req.setAttribute("AddComment","错误：找不到该学生");
        }

        System.out.println(req.getAttribute("AddComment"));
        PrintWriter writer = resp.getWriter();
        writer.print(req.getAttribute("AddComment"));
        writer.flush();
        writer.close();
    }
}
