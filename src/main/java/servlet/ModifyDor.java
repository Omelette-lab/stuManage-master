package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Student;
import DB.DB;


/**
 * Servlet implementation class MainServlet
 */

public class ModifyDor extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ModifyDor");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("utf-8");
        resp.setContentType("text/plain; charset=UTF-8");

        String id = req.getParameter("stu_id");
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        String NewDor_id = req.getParameter("dor_id");

        System.out.println(id);
        System.out.println(name);
        System.out.println(sex);
        System.out.println(NewDor_id);

        DB db = new DB();
        //session中存放post传给request的值
        HttpSession session = req.getSession();

        //存在该学号的学生
        if (db.checkStudent(id)!=null) {
            Student stu=db.checkStudent(id);
            //检查填写的姓名、性别是否与该学生信息一致
            if(stu.getName().equals(name) && stu.getSex().equals(sex)){
                //检查填入的预修改的职位与当前职位是否一致
                if(stu.getDor_id().equals(NewDor_id)){
                    req.setAttribute("ModifyDor","失败：修改前后宿舍号相同");
                }
                else{
                    db.updateDor(id,NewDor_id);
                    ArrayList NowStuAL=(ArrayList)session.getAttribute("StuAL");
                    Iterator iter=NowStuAL.iterator();
                    while(iter.hasNext()) {
                        Student s = (Student) iter.next();
                        if(s.getId().equals(id)){
                            Student newstu = s;
                            newstu.setDor_id(NewDor_id);
                            int index = NowStuAL.indexOf(s);
                            NowStuAL.set(index,newstu);
                            break;
                        }
                    }
                    session.setAttribute("StuAL", NowStuAL);
                    req.setAttribute("ModifyDor","修改成功");
                }
            } else {
                //填入的姓名、性别与该学生信息不一致
                req.setAttribute("ModifyDor","失败：学生信息填写错误");
            }
        } else {
            //不存在该学生
            req.setAttribute("ModifyDor","错误：找不到该学生");
        }
        System.out.println(req.getAttribute("ModifyDor"));
        PrintWriter writer = resp.getWriter();
        writer.print(req.getAttribute("ModifyDor"));
        writer.flush();
        writer.close();
    }
}