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
import model.Attendance;
import model.Plan;
import model.Student;
import model.Teacher;


/**
 * Servlet implementation class MainServlet
 */

public class ModifyPlan extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ModifyPlan");
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

        if (db.checkPlan(teacher.getId(),time) != null) {
            Plan plan = db.checkPlan(teacher.getId(),time);
            if (plan.getPlan().equals(plan)) {
                req.setAttribute("ModifyPlan", "失败；修改前后信息相同");
            } else {
                if(db.updatePlan(teacher.getId(),time,planstr)){
                    ArrayList NowPlanAL=(ArrayList)session.getAttribute("PlanAL");
                    Iterator iter=NowPlanAL.iterator();
                    while(iter.hasNext()) {
                        Plan p = (Plan) iter.next();
                        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        System.out.println(ft.format(p.getTime()));
                        if(p.getTea_id().equals(teacher.getId()) && ft.format(p.getTime()).equals(time)){
                            Plan new_plan = p;
                            new_plan.setPlan(planstr);
                            int index = NowPlanAL.indexOf(p);
                            NowPlanAL.set(index,new_plan);
                            break;
                        }
                    }
                    session.setAttribute("PlanAL", NowPlanAL);
                    req.setAttribute("ModifyPlan", "修改成功");
                }else {
                    req.setAttribute("ModifyPlan", "修改失败，时间不能修改");
                }
            }
        } else {
            req.setAttribute("ModifyPlan", "失败：未找到该信息");
        }

        System.out.println(req.getAttribute("ModifyPlan"));
        PrintWriter writer = resp.getWriter();
        writer.print(req.getAttribute("ModifyPlan"));
        writer.flush();
        writer.close();
    }
}