package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import DB.DB;
import model.Student;
import model.Student;
import model.Student;
import model.Teacher;


/**
 * Servlet implementation class MainServlet
 */

public class DeleteStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DeleteStudent");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("utf-8");
        resp.setContentType("text/plain; charset=UTF-8");

        String stu_id = req.getParameter("stu_id");

        System.out.println(stu_id);

        DB db = new DB();
        HttpSession session = req.getSession();

        if(db.DeleteStudent(stu_id)){
            ArrayList NowStudentAL = db.findAllStudent();
            session.setAttribute("Student", NowStudentAL);
            req.setAttribute("DeleteStudent", "删除成功");
        }else {
            req.setAttribute("DeleteStudent", "删除失败：信息异常");
        }

        System.out.println(req.getAttribute("DeleteStudent"));
        PrintWriter writer = resp.getWriter();
        writer.print(req.getAttribute("DeleteStudent"));
        writer.flush();
        writer.close();
    }
}