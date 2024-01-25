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
import model.Grade;
import model.Teacher;


/**
 * Servlet implementation class MainServlet
 */

public class ModifyGrade extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ModifyGrade");
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

        if (db.checkGrade(stu_id,date) != null) {
            Grade grade = db.checkGrade(stu_id,date);
            if(grade.getChinse() == chinese && grade.getMath() == math && grade.getEnglish() == english){
                req.setAttribute("ModifyGrade", "修改失败：前后信息相同");
            }else {
                if (db.updateGrade(stu_id,date,chinese,math,english)) {
                    ArrayList NowGradeAL = db.findStuGrade(teacher);
                    session.setAttribute("GradeAL", NowGradeAL);
                    req.setAttribute("ModifyGrade", "修改成功");
                } else {
                    req.setAttribute("ModifyGrade", "修改失败，信息有误");
                }
            }
        } else {
            req.setAttribute("ModifyGrade", "失败：不存在该成绩信息");
        }
        System.out.println(req.getAttribute("ModifyGrade"));
        PrintWriter writer = resp.getWriter();
        writer.print(req.getAttribute("ModifyGrade"));
        writer.flush();
        writer.close();
    }
}