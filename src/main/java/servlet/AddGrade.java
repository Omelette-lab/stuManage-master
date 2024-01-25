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
import model.Teacher;


/**
 * Servlet implementation class MainServlet
 */

public class AddGrade extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AddGrade");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("utf-8");
        resp.setContentType("text/plain; charset=UTF-8");

        String stu_id = req.getParameter("stu_id");
        String date = req.getParameter("date");
        String chinesestr = req.getParameter("chinese");
        String mathstr = req.getParameter("math");
        String englishstr = req.getParameter("english");

        Float chinese = Float.parseFloat(chinesestr);
        Float math = Float.parseFloat(mathstr);
        Float english = Float.parseFloat(englishstr);

        System.out.println(stu_id);
        System.out.println(date);
        System.out.println(chinese);
        System.out.println(math);
        System.out.println(english);

        DB db = new DB();
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (db.checkGrade(stu_id,date) == null) {
            if (db.insertGrade(stu_id,date,chinese,math,english)) {
                ArrayList NowGradeAL = db.findStuGrade(teacher);
                session.setAttribute("GradeAL", NowGradeAL);
                req.setAttribute("AddGrade", "添加成功");
            } else {
                req.setAttribute("AddGrade", "添加失败，信息填写有误");
            }
        } else {
            req.setAttribute("AddGrade", "失败：已存在");
        }

        System.out.println(req.getAttribute("AddGrade"));
        PrintWriter writer = resp.getWriter();
        writer.print(req.getAttribute("AddGrade"));
        writer.flush();
        writer.close();
    }
}