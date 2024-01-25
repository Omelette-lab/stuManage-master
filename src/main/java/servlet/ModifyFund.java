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
import model.Fund;
import model.Student;
import model.Teacher;


/**
 * Servlet implementation class MainServlet
 */

public class ModifyFund extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ModifyFund");
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

        if (db.checkFund(teacher.getCls_id(),date) != null) {
            Fund fund = db.checkFund(teacher.getCls_id(),date);
            if (fund.getAmount() == amount && fund.getBalance() == balance && fund.getRemark().equals(remark)) {
                req.setAttribute("ModifyFund", "失败；修改前后信息相同");
            } else {
                if(db.updateFund(teacher.getCls_id(),date,amount,balance,remark)){
                    ArrayList NowFundAL=(ArrayList)session.getAttribute("FundAL");
                    Iterator iter=NowFundAL.iterator();
                    while(iter.hasNext()) {
                        Fund f = (Fund) iter.next();
                        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                        if(f.getCls_id() == teacher.getCls_id() && ft.format(f.getDate()).equals(date)){
                            Fund new_fund = f;
                            new_fund.setAmount(amount);
                            new_fund.setBalance(balance);
                            new_fund.setRemark(remark);
                            int index = NowFundAL.indexOf(f);
                            NowFundAL.set(index,new_fund);
                            break;
                        }
                    }
                    session.setAttribute("FundAL", NowFundAL);
                    req.setAttribute("ModifyFund", "修改成功");
                }else {
                    req.setAttribute("ModifyFund", "修改失败，日期不能修改");
                }
            }
        } else {
            req.setAttribute("ModifyFund", "失败：未找到该信息");
        }

        System.out.println(req.getAttribute("ModifyFund"));
        PrintWriter writer = resp.getWriter();
        writer.print(req.getAttribute("ModifyFund"));
        writer.flush();
        writer.close();
    }
}