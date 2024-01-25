package servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.DB;
import model.Teacher;


/**
 * Servlet implementation class MainServlet
 */

public class SearchPlan extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("SearchPlan");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("utf-8");
        String keyword = req.getParameter("keyword");

        System.out.println(keyword);
        System.out.println(keyword.length());

        DB db = new DB();
        if(keyword.length()==0){
            try{
                HttpSession session=req.getSession();
                session.setAttribute("PlanAL", session.getAttribute("AllPlanAL"));
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            try{
                HttpSession session=req.getSession();
                Teacher teacher = (Teacher) session.getAttribute("teacher");
                ArrayList SearchPlanAL = db.SearchInPlan(keyword,teacher.getId());
                session.setAttribute("PlanAL", SearchPlanAL);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}