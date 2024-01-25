package servlet;

import DB.DB;
import model.Teacher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertCmt extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("InsertCmt");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        HttpSession session=request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        String comment = request.getParameter("comment");
        Date date_ = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(date_);

        System.out.println(comment);
        DB db=new DB();
        if(db.insertCmt(comment,date,teacher.getId())){
            request.setAttribute("insertCmt","评价成功");
        }else {
            request.setAttribute("insertCmt","今日已评价");
        }


        System.out.println(request.getAttribute("insertCmt"));
        PrintWriter writer = response.getWriter();
        writer.print(request.getAttribute("insertCmt"));
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
