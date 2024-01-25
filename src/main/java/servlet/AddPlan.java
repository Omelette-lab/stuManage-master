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

public class AddPlan extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AddPlan");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("utf-8");
        resp.setContentType("text/plain; charset=UTF-8");
        
        String time = req.getParameter("time");
        String planstr = req.getParameter("plan");

        System.out.println(time);
        System.out.println(planstr);

        DB db = new DB();
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (db.checkPlan(teacher.getId(),time) == null) {
            if(db.insertPlan(teacher.getId(),time,planstr)){
                ArrayList NowPlanAL = (ArrayList) session.getAttribute("PlanAL");
                Plan plan = new Plan();
                plan.setTea_id(teacher.getId());
                Timestamp timestamp = Timestamp.valueOf(time);
                plan.setTime(timestamp);
                plan.setPlan(planstr);
                NowPlanAL.add(plan);
                session.setAttribute("PlanAL", NowPlanAL);
                req.setAttribute("AddPlan", "添加成功");
            }else {
                req.setAttribute("AddPlan", "添加失败，已存在");
            }
        } else {
            req.setAttribute("AddPlan", "添加失败：已存在");
        }

        System.out.println(req.getAttribute("AddPlan"));
        PrintWriter writer = resp.getWriter();
        writer.print(req.getAttribute("AddPlan"));
        writer.flush();
        writer.close();
    }
}