package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import DB.DB;
import model.Attendance;
import model.Student;


/**
 * Servlet implementation class MainServlet
 */

public class ModifyAttendance extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ModifyAttendance");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("utf-8");
        resp.setContentType("text/plain; charset=UTF-8");

        String id = req.getParameter("stu_id");
        String name = req.getParameter("name");
        String date = req.getParameter("date");
        String ifatt = req.getParameter("ifatt");

        System.out.println(id);
        System.out.println(name);
        System.out.println(date);
        System.out.println(ifatt);

        DB db = new DB();
        HttpSession session = req.getSession();

        if (db.checkATT(id,date)!=null) {
            Attendance att =db.checkATT(id,date);
            //检查填写的姓名是否与该学生信息一致
            if(db.getName(id).equals(name)){
                //检查填入的出勤情况与当前是否一致
                if(att.getIfatt().equals(ifatt)){
                    req.setAttribute("ModifyAttendance","失败：修改前后信息相同");
                }
                else{
                    db.updateAtt(id,date,ifatt);
                    ArrayList NowAttAL=(ArrayList)session.getAttribute("AttAL");
                    Iterator iter=NowAttAL.iterator();
                    while(iter.hasNext()) {
                        Attendance attendance = (Attendance) iter.next();
                        if(attendance.getStu_id().equals(id)){
                            Attendance newatt = attendance;
                            newatt.setIfatt(ifatt);
                            int index = NowAttAL.indexOf(attendance);
                            NowAttAL.set(index,newatt);
                            break;
                        }
                    }
                    session.setAttribute("AttAL", NowAttAL);
                    req.setAttribute("ModifyAttendance","修改成功");
                }
            } else {
                //填入的姓名与该学生信息一致不一致
                req.setAttribute("ModifyAttendance","失败：学生信息填写错误");
            }
        } else {
            //不存在该出勤记录
            req.setAttribute("ModifyAttendance","错误：不存在次出勤记录");
        }
        System.out.println(req.getAttribute("ModifyAttendance"));
        PrintWriter writer = resp.getWriter();
        writer.print(req.getAttribute("ModifyAttendance"));
        writer.flush();
        writer.close();
    }
}