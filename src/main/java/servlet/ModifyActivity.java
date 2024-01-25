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
import model.Activity;
import model.Teacher;


/**
 * Servlet implementation class MainServlet
 */

public class ModifyActivity extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ModifyActivity");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("utf-8");
        resp.setContentType("text/plain; charset=UTF-8");

        String name = req.getParameter("name");
        String date = req.getParameter("date");
        String place = req.getParameter("place");
        String content = req.getParameter("content");

        System.out.println(name);
        System.out.println(date);
        System.out.println(place);
        System.out.println(content);

        DB db = new DB();
        HttpSession session = req.getSession();
        Teacher tea = (Teacher) session.getAttribute("teacher");
        if(db.checkActivity(tea.getCls_id(),name,date,place,content)==null){
            if(db.updateActivity(tea.getCls_id(),name,date,place,content)){
                ArrayList NowActivityAL=(ArrayList)session.getAttribute("ActivityAL");
                Iterator iter=NowActivityAL.iterator();
                while(iter.hasNext()) {
                    Activity activity = (Activity) iter.next();
                    if(activity.getName().equals(name)){
                        Activity newac = activity;
                        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                        Date dat = null;
                        try {
                            dat = ft.parse(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        newac.setDate(dat);
                        newac.setPlace(place);
                        newac.setContent(content);
                        int index = NowActivityAL.indexOf(activity);
                        NowActivityAL.set(index,newac);
                        break;
                    }
                }
                session.setAttribute("ActivityAL", NowActivityAL);
                req.setAttribute("ModifyActivity","修改成功");
            }else {
                req.setAttribute("ModifyActivity","修改失败");
            }
        }else {
            req.setAttribute("ModifyActivity","失败：已存在完全相同的活动");
        }
        System.out.println(req.getAttribute("ModifyActivity"));
        PrintWriter writer = resp.getWriter();
        writer.print(req.getAttribute("ModifyActivity"));
        writer.flush();
        writer.close();
    }
}