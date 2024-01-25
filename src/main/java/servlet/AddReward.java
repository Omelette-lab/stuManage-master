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
import model.Reward;
import model.Student;
import model.Teacher;


/**
 * Servlet implementation class MainServlet
 */

public class AddReward extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AddReward");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("utf-8");
        resp.setContentType("text/plain; charset=UTF-8");

        String stu_id = req.getParameter("stu_id");
        String name = req.getParameter("name");
        String date = req.getParameter("date");
        String type = req.getParameter("type");
        String reason = req.getParameter("reason");

        System.out.println(stu_id);
        System.out.println(name);
        System.out.println(date);
        System.out.println(type);
        System.out.println(reason);

        DB db = new DB();
        HttpSession session = req.getSession();
        //存在该学号的学生
        if (db.checkStudent(stu_id)!=null) {
            Student stu = db.checkStudent(stu_id);
            //检查填写的姓名是否与该学生信息一致
            if (stu.getName().equals(name)) {
                if (db.checkReward(stu_id, date, type, reason) == null) {
                    if (db.insertReward(stu_id, date, type, reason)) {
                        ArrayList NowRewardAL = (ArrayList) session.getAttribute("RewardAL");
                        Reward reward = new Reward();
                        reward.setStu_id(stu_id);
                        reward.setType(type.charAt(0));
                        reward.setReason(reason);
                        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                        Date dat = null;
                        try {
                            dat = ft.parse(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        reward.setDate(dat);
                        NowRewardAL.add(reward);
                        session.setAttribute("RewardAL", NowRewardAL);
                        req.setAttribute("ModifyReward", "修改成功");
                    } else {
                        req.setAttribute("ModifyReward", "修改失败");
                    }
                } else {
                    req.setAttribute("ModifyReward", "失败：已存在");
                }
            } else {
                req.setAttribute("ModifyReward", "失败：学生信息填写错误");
            }
        }else {
            req.setAttribute("ModifyReward", "失败：不存在该学生");
        }
        System.out.println(req.getAttribute("ModifyReward"));
        PrintWriter writer = resp.getWriter();
        writer.print(req.getAttribute("ModifyReward"));
        writer.flush();
        writer.close();
    }
}