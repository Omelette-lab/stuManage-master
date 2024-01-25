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
import model.Teacher;
import model.Teacher;
import model.Teacher;
import model.Teacher;


/**
 * Servlet implementation class MainServlet
 */

public class DeleteTeacher extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DeleteTeacher");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("utf-8");
        resp.setContentType("text/plain; charset=UTF-8");

        String tea_id = req.getParameter("tea_id");

        System.out.println(tea_id);

        DB db = new DB();
        HttpSession session = req.getSession();

        if(db.DeleteTeacher(tea_id)){
            ArrayList NowTeacherAL = db.findAllTeacher();
            session.setAttribute("Teacher", NowTeacherAL);
            req.setAttribute("DeleteTeacher", "删除成功");
        }else {
            req.setAttribute("DeleteTeacher", "删除失败：信息异常");
        }

        System.out.println(req.getAttribute("DeleteTeacher"));
        PrintWriter writer = resp.getWriter();
        writer.print(req.getAttribute("DeleteTeacher"));
        writer.flush();
        writer.close();
    }
}