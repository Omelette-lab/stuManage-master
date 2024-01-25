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

import model.Comment;
import model.Student;
import DB.DB;
import model.Teacher;


/**
 * Servlet implementation class MainServlet
 */

public class ModifyComment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ModifyComment");
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
                if(db.checkComment(id,date)!=null){
                    Comment comment = db.checkComment(id,date);
                    if(comment.getCls_adv().equals(cls_adv) && comment.getSelf_cmt().equals(self_cmt)){
                        req.setAttribute("ModifyComment", "修改失败：修改前后内容一致");
                    }else {
                        if (db.updateComment(id, date, self_cmt, cls_adv)) {
                            ArrayList NowCommentAL = db.findCommentInClass(teacher);
                            session.setAttribute("CommentAL", NowCommentAL);
                            req.setAttribute("ModifyComment", "修改成功");
                        } else {
                            req.setAttribute("ModifyComment", "修改失败");
                        }
                    }
                }else {
                    req.setAttribute("ModifyComment", "修改失败：不存在该评语");
                }
            }else {
                req.setAttribute("ModifyComment", "错误：学生信息填写错误");
            }
        } else {
            //不存在该学生
            req.setAttribute("ModifyComment","错误：找不到该学生");
        }
        System.out.println(req.getAttribute("ModifyComment"));
        PrintWriter writer = resp.getWriter();
        writer.print(req.getAttribute("ModifyComment"));
        writer.flush();
        writer.close();
    }
}