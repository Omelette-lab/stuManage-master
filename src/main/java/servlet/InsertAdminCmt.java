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

public class InsertAdminCmt extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("InsertAdminCmt");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        HttpSession session=request.getSession();
        String comment = request.getParameter("comment");
        Date date_ = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(date_);

        System.out.println(comment);
        DB db=new DB();
        if(db.insertAdminCmt(comment,date)){
            request.setAttribute("InsertAdminCmt","评价成功");
        }else {
            request.setAttribute("InsertAdminCmt","今日已评价");
        }


        System.out.println(request.getAttribute("InsertAdminCmt"));
        PrintWriter writer = response.getWriter();
        writer.print(request.getAttribute("InsertAdminCmt"));
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
