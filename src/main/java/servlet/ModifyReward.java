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


/**
 * Servlet implementation class MainServlet
 */

public class ModifyReward extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ModifyReward");
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
            Student stu=db.checkStudent(stu_id);
            //检查填写的姓名是否与该学生信息一致
            if(stu.getName().equals(name)){
                //检查填入信息是否与之前一致
                if(db.checkReward(stu_id,date,type,reason)!=null){
                    req.setAttribute("ModifyReward","失败：修改前后信息相同");
                } else{
                    if(db.updateReward(stu_id,date,type,reason)){
                        ArrayList NowRewardAL=(ArrayList)session.getAttribute("RewardAL");
                        Iterator iter=NowRewardAL.iterator();
                        while(iter.hasNext()) {
                            Reward reward = (Reward) iter.next();
                            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                            if(reward.getStu_id().equals(stu_id) && ft.format(reward.getDate()).equals(date)){
                                Reward new_reward = reward;
                                new_reward.setReason(reason);
                                new_reward.setType(type.charAt(0));
                                int index = NowRewardAL.indexOf(reward);
                                NowRewardAL.set(index,new_reward);
                                break;
                            }
                        }
                        session.setAttribute("RewardAL", NowRewardAL);
                        req.setAttribute("ModifyReward","修改成功");
                    }else {
                        req.setAttribute("ModifyReward","时间不允许修改");
                    }
                }
            } else {
                //填入的姓名与该学生信息一致不一致
                req.setAttribute("ModifyReward","失败：学生信息填写错误");
            }
        } else {
            //不存在该学生
            req.setAttribute("ModifyReward","错误：找不到该学生");
        }

        System.out.println(req.getAttribute("ModifyReward"));
        PrintWriter writer = resp.getWriter();
        writer.print(req.getAttribute("ModifyReward"));
        writer.flush();
        writer.close();
    }
}