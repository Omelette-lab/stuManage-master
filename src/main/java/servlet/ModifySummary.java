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
import model.Summary;
import model.Teacher;


/**
 * Servlet implementation class MainServlet
 */

public class ModifySummary extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ModifySummary");
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

        if (db.checkSummary(teacher.getId(),date) != null) {
            Summary summary = db.checkSummary(teacher.getId(),date);
            if (summary.getSummary().equals(summarystr)) {
                req.setAttribute("ModifySummary", "失败；修改前后信息相同");
            } else {
                if(db.updateSummary(teacher.getId(),date,summarystr)){
                    ArrayList NowSummaryAL = db.findSummaryInfoInClass(teacher);
                    session.setAttribute("SummaryAL", NowSummaryAL);
                    req.setAttribute("ModifySummary", "修改成功");
                }else {
                    req.setAttribute("ModifySummary", "修改失败，日期不能修改");
                }
            }
        } else {
            req.setAttribute("ModifySummary", "失败：未找到该信息");
        }

        System.out.println(req.getAttribute("ModifySummary"));
        PrintWriter writer = resp.getWriter();
        writer.print(req.getAttribute("ModifySummary"));
        writer.flush();
        writer.close();
    }
}