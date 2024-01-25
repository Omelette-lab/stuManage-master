package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.script.ScriptContext;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.alibaba.fastjson.JSON;
import model.Admin;
import model.Teacher;
import DB.DB;
import model.User;
/**
 * Servlet implementation class MainServlet
 */

//@WebServlet("/Retrieve")
public class Retrieve extends HttpServlet {

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Retrieve");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        String email = request.getParameter("email");
        String newPwd = request.getParameter("newPwd");
        String confirmPwd = request.getParameter("confirmPwd");
        String info=null;
        Map<String,String> map=new HashMap();

        System.out.println(id);
        System.out.println(email);
        System.out.println(newPwd);
        System.out.println(confirmPwd);

        DB db = new DB();
//        //session中存放post传给request的值
        HttpSession session = request.getSession();
        /**如果id和email在数据库管理员或者老师中存在且搭配(写一个checkID)，则比较两次输入的密码
         **如果两次输入一致：弹出会话修改成功
         **如果两次输入不一致：弹出会话两次输入不一致
         */
        /**如果id和email在数据库中不存在或不搭配，则弹出:不存在该账户
         */
        if (db.checkUser(id, email)!=null) {
            User user=db.checkUser(id, email);
            if(!id.equals("Admin")){
                Teacher teacher=db.checkTeacher(id,user.getPassword());
            }else {
                Admin admin=db.checkAdmin(id,user.getPassword());
            }
            if (confirmPwd.equals(newPwd)) {
                //在数据库中修改密码
                db.updatePwd(id,newPwd);
                //密码修改成功

//                    request.setAttribute("Success", "密码修改成功");
                info="密码修改成功!";
//                request.getRequestDispatcher("login.jsp").forward(request, response);


            } else {
                //两次输入的密码不一致
//                request.setAttribute("Success","两次输入的密码不一致");
                info="两次输入的密码不一致!";
//                request.getRequestDispatcher("login.jsp").forward(request,response);

            }
        } else {
            //不存在该账户
            info="账号或邮箱输入错误";
//            request.setAttribute("Success","账号或邮箱输入错误");
//            request.getRequestDispatcher("login.jsp").forward(request,response);

        }
        System.out.println(info);
        map.put("info",info);

        PrintWriter writer = response.getWriter();
//        writer.print(map);
        writer.print(JSON.toJSON(map));
        writer.flush();
        writer.close();
    }
    

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

}