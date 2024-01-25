package servlet;

import DB.DB;
import model.Admin;
import model.Teacher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ModifyAdminPwd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ModifyAdminPwd");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String oldpwd = request.getParameter("oldpwd");
        String newpwd_1 = request.getParameter("newpwd_1");
        String newpwd_2 = request.getParameter("newpwd_2");

        System.out.println(oldpwd);
        System.out.println(newpwd_1);

        DB db = new DB();
        if(db.getAdminPwd().equals(oldpwd)){
            if(newpwd_2.equals(newpwd_1)){
                if (db.updateAdminPwd(newpwd_1)){
                    request.setAttribute("ModifyAdminPwd","修改成功");
                }else {
                    request.setAttribute("ModifyAdminPwd","修改失败");
                }
            }else {
                request.setAttribute("ModifyAdminPwd","新修改密码两次填写不一致");
            }
        }else {
            request.setAttribute("ModifyAdminPwd","旧密码填写错误");
        }
        System.out.println(request.getAttribute("ModifyAdminPwd"));
        PrintWriter writer = response.getWriter();
        writer.print(request.getAttribute("ModifyAdminPwd"));
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
