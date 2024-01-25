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
import model.Fund;
import model.Student;
import model.Teacher;


/**
 * Servlet implementation class MainServlet
 */

public class AddFund extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AddFund");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("utf-8");
        resp.setContentType("text/plain; charset=UTF-8");

        String date = req.getParameter("date");
        String amount_str = req.getParameter("amount");
        float amount = Float.parseFloat(amount_str);

        String balance_str = req.getParameter("balance");
        float balance = Float.parseFloat(balance_str);
        String remark = req.getParameter("remark");

        System.out.println(date);
        System.out.println(amount);
        System.out.println(balance);
        System.out.println(remark);

        DB db = new DB();
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (db.checkFund(teacher.getCls_id(),date) == null) {
            if (db.insertFund(teacher.getCls_id(),date,amount,balance,remark)) {
                ArrayList NowFundAL = (ArrayList) session.getAttribute("FundAL");
                Fund fund = new Fund();
                fund.setCls_id(teacher.getCls_id());
                SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                Date dat = null;
                try {
                    dat = ft.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                fund.setDate(dat);
                fund.setAmount(amount);
                fund.setBalance(balance);
                fund.setRemark(remark);
                NowFundAL.add(fund);
                session.setAttribute("FundAL", NowFundAL);
                req.setAttribute("AddFund", "添加成功");
            } else {
                req.setAttribute("AddFund", "添加失败");
            }
        } else {
            req.setAttribute("AddFund", "失败：已存在");
        }

        System.out.println(req.getAttribute("AddFund"));
        PrintWriter writer = resp.getWriter();
        writer.print(req.getAttribute("AddFund"));
        writer.flush();
        writer.close();
    }
}