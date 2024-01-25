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

public class AddSummary extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AddSummary");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("utf-8");
        resp.setContentType("text/plain; charset=UTF-8");

        String date = req.getParameter("date");
        String summarystr = req.getParameter("summary");

        System.out.println(date);
        System.out.println(summarystr);

        DB db = new DB();
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (db.checkSummary(teacher.getId(),date) == null) {
            if(db.insertSummary(teacher.getId(),date,summarystr)){
                ArrayList NowSummaryAL = db.findSummaryInfoInClass(teacher);
                session.setAttribute("SummaryAL", NowSummaryAL);
                req.setAttribute("AddSummary", "添加成功");
            }else {
                req.setAttribute("AddSummary", "添加失败：信息填写有误");
            }
        } else {
            req.setAttribute("AddSummary", "失败：已存在");
        }
        
        System.out.println(req.getAttribute("AddSummary"));
        PrintWriter writer = resp.getWriter();
        writer.print(req.getAttribute("AddSummary"));
        writer.flush();
        writer.close();
    }
}