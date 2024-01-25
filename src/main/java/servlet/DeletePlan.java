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
import model.Plan;
import model.Plan;
import model.Student;
import model.Teacher;


/**
 * Servlet implementation class MainServlet
 */

public class DeletePlan extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DeletePlan");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("utf-8");
        resp.setContentType("text/plain; charset=UTF-8");

        String time = req.getParameter("time");

        System.out.println(time);

        DB db = new DB();
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (db.checkPlan(teacher.getId(),time) != null) {
            if(db.DeletePlan(teacher.getId(),time)){
                ArrayList NowPlanAL = db.findPlanInfoInClass(teacher);
                session.setAttribute("PlanAL", NowPlanAL);
                req.setAttribute("DeletePlan", "删除成功");
            }else {
                req.setAttribute("DeletePlan", "删除失败：信息异常");
            }
        } else {
            req.setAttribute("DeletePlan", "删除失败：不存在信息");
        }

        System.out.println(req.getAttribute("DeletePlan"));
        PrintWriter writer = resp.getWriter();
        writer.print(req.getAttribute("DeletePlan"));
        writer.flush();
        writer.close();
    }
}