package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import DB.DB;
import model.Attendance;
import model.Student;


/**
 * Servlet implementation class MainServlet
 */

public class AddAttendance extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AddAttendance");
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

        if (db.checkATT(id,date)==null) {
            //检查填写的姓名是否与该学生信息一致
            if(db.getName(id).equals(name)){
                if(db.insertATT(id,date,ifatt)){
                    ArrayList NowAttAL=(ArrayList)session.getAttribute("AttAL");
                    Attendance att = new Attendance();
                    att.setStu_id(id);
                    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                    Date dat = null;
                    try {
                        dat = ft.parse(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    att.setDate(dat);
                    att.setIfatt(ifatt);
                    NowAttAL.add(att);
                    session.setAttribute("AttAL", NowAttAL);
                    req.setAttribute("ModifyAttendance","插入成功");

                }else{

                    req.setAttribute("ModifyAttendance","插入数据库失败");
                }
            } else {
                //填入的姓名与该学生信息一致不一致
                req.setAttribute("ModifyAttendance","失败：学生信息填写错误");
            }
        } else {
            //已存在该出勤记录
            req.setAttribute("ModifyAttendance","添加错误：已存在该出勤记录");
        }
        System.out.println(req.getAttribute("ModifyAttendance"));
        PrintWriter writer = resp.getWriter();
        writer.print(req.getAttribute("ModifyAttendance"));
        writer.flush();
        writer.close();
    }
}