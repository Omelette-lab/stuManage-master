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

public class ModifyMes extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ModifyMes");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        HttpSession session=request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        String email = request.getParameter("email");
        String info=null;

        System.out.println(email);
        DB db=new DB();
        if(db.updateEmail(email,teacher)){
            request.setAttribute("ModifyMes","修改成功");
        }else {
            request.setAttribute("ModifyMes","修改失败");
        }


        System.out.println(request.getAttribute("ModifyMes"));
        PrintWriter writer = response.getWriter();
        writer.print(request.getAttribute("ModifyMes"));
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
