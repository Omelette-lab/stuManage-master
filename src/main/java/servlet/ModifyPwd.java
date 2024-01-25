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

public class ModifyPwd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("ModifyPwd");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        var session=request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        String oldpwd = request.getParameter("oldpwd");
        String newpwd_1 = request.getParameter("newpwd_1");
        String newpwd_2 = request.getParameter("newpwd_2");

        System.out.println(oldpwd);
        System.out.println(newpwd_1);

        DB db = new DB();
        if(db.getTeaPwd(teacher.getId()).equals(oldpwd)){
            if(newpwd_2.equals(newpwd_1)){
                if (db.updateTeaPwd(newpwd_1,teacher)){
                    request.setAttribute("ModifyPwd","修改成功");
                }else {
                    request.setAttribute("ModifyPwd","修改失败");
                }
            }else {
                request.setAttribute("ModifyPwd","新修改密码两次填写不一致");
            }
        }else {
            request.setAttribute("ModifyPwd","旧密码填写错误");
        }
        System.out.println(request.getAttribute("ModifyPwd"));
        PrintWriter writer = response.getWriter();
        writer.print(request.getAttribute("ModifyPwd"));
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
