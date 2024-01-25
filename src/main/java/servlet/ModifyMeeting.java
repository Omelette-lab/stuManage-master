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
import model.Meetings;
import model.Teacher;


/**
 * Servlet implementation class MainServlet
 */

public class ModifyMeeting extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ModifyMeeting");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("utf-8");
        resp.setContentType("text/plain; charset=UTF-8");

        String date = req.getParameter("date");
        String theme = req.getParameter("theme");
        String content = req.getParameter("content");

        System.out.println(date);
        System.out.println(theme);
        System.out.println(content);

        DB db = new DB();
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (db.checkMeeting(teacher.getCls_id(),date) != null) {
            Meetings meeting = db.checkMeeting(teacher.getCls_id(),date);
            if (meeting.getTheme().equals(theme) && meeting.getContent().equals(content)) {
                req.setAttribute("ModifyMeeting", "失败；修改前后信息相同");
            } else {
                if(db.updateMeeting(teacher.getCls_id(),date,theme,content)){
                    ArrayList NowMeetingAL = db.findMeetingInfoInClass(teacher);
                    session.setAttribute("MeetingAL", NowMeetingAL);
                    req.setAttribute("ModifyMeeting", "修改成功");
                }else {
                    req.setAttribute("ModifyMeeting", "修改失败，日期不能修改");
                }
            }
        } else {
            req.setAttribute("ModifyMeeting", "失败：未找到该信息");
        }

        System.out.println(req.getAttribute("ModifyMeeting"));
        PrintWriter writer = resp.getWriter();
        writer.print(req.getAttribute("ModifyMeeting"));
        writer.flush();
        writer.close();
    }
}