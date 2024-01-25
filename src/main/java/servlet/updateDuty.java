package servlet;

import DB.DB;
import model.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,   // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class updateDuty extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("uploadImage");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try {
            // 获取上传的图片数据
            Part imagePart = request.getPart("image");
            HttpSession session=request.getSession();
            Teacher teacher = (Teacher) session.getAttribute("teacher");
            InputStream imageInputStream = imagePart.getInputStream();
            DB db=new DB();


            // 将图片数据保存到数据库
            db.updateDuty(imageInputStream,teacher.getCls_id());
            // 返回上传成功的消息
            response.setContentType("text/plain");
            response.getWriter().write("图片上传成功");
        } catch (Exception e) {
            System.out.println(e);
            response.setContentType("text/plain");
            response.getWriter().write("图片上传失败");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
