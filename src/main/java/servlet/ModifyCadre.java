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

import com.alibaba.fastjson.JSON;
import model.Student;
import DB.DB;

/**
 * Servlet implementation class MainServlet
 */

public class ModifyCadre extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ModifyCadre");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain; charset=UTF-8");

        String id = req.getParameter("stu_id");
        String name = req.getParameter("stu_name");
        String newposition = req.getParameter("position");
        System.out.println(id);
        System.out.println(name);
        System.out.println(newposition);

        DB db = new DB();
        HttpSession session = req.getSession();

        //存在该学号的学生
        if (db.checkStudent(id)!=null) {
            Student stu=db.checkStudent(id);
            //检查填写的学号姓名是否一致
            if(stu.getName().equals(name)){
                //检查填入的预修改的职位与当前职位是否一致
                if(stu.getPosition().equals(newposition)){
                    req.setAttribute("ModifyCadre","失败：修改前后职位相同");
                }
                else{
                    db.updatePosition(id,newposition);
                    ArrayList NowStuAL=(ArrayList)session.getAttribute("StuAL");
                    Iterator iter=NowStuAL.iterator();
                    while(iter.hasNext()) {
                        Student s = (Student) iter.next();
                        if(s.getId().equals(id)){
                            Student newstu = s;
                            newstu.setPosition(newposition);
                            int index = NowStuAL.indexOf(s);
                            NowStuAL.set(index,newstu);
                            break;
                        }
                    }
                    session.setAttribute("StuAL", NowStuAL);
                    req.setAttribute("ModifyCadre","修改成功");
                }
            } else {
                //填入的学号与姓名不一致
                req.setAttribute("ModifyCadre","失败：学号或姓名填写错误");
            }

        } else {
            //不存在该学生
            req.setAttribute("ModifyCadre","错误：找不到该学生");
        }
        System.out.println(req.getAttribute("ModifyCadre"));
        PrintWriter writer = resp.getWriter();
        writer.print(req.getAttribute("ModifyCadre"));
        writer.flush();
        writer.close();

    }
}