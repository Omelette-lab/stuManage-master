package servlet;

import DB.DB;
import model.Admin;
import model.Teacher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class ModifyAdminMes extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ModifyAdminMes");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String email = request.getParameter("email");
        System.out.println(email);
        DB db=new DB();
        if(db.updateAEmail(email)){
            request.setAttribute("ModifyAdminMes","修改成功");
        }else {
            request.setAttribute("ModifyAdminMes","修改失败");
        }


        System.out.println(request.getAttribute("ModifyAdminMes"));
        PrintWriter writer = response.getWriter();
        writer.print(request.getAttribute("ModifyAdminMes"));
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
