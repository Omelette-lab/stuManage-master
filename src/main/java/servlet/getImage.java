package servlet;

import DB.DB;
import model.Teacher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class getImage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// 从请求参数中获取 cls_id
        HttpSession session=request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        DB db=new DB();
        // 调用方法从数据库获取 curriculum BLOB 数据
        InputStream curriculumInputStream = db.getCurriculum(teacher.getCls_id());

        if (curriculumInputStream != null) {
            // 设置Content-Type头部，告知浏览器文件类型
            response.setContentType("application/octet-stream");

            // 设置Content-Disposition头部，指定文件名
            response.setHeader("Content-Disposition", "attachment; filename=curriculum.pdf");

            // 将获取到的BLOB数据写入到响应输出流中
            OutputStream outStream = response.getOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = curriculumInputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            curriculumInputStream.close();
            outStream.close();
        } else {
            // 如果无法获取到 curriculum BLOB 数据，则返回404错误
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
