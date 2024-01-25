<%@ page import="java.io.InputStream" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="java.io.IOException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>值日安排</title>

</head>
<body>
<h4 align="center">值日安排</h4>
<br>
<form id="upload-form" enctype="multipart/form-data">
    <input type="file" accept="image/*" id="file-input" name="image">
    <br>
    <img id="img-preview" src="#" alt="预览图片" width="600" height="330">
    <br>

    <button class="btn btn-primary btn-large" type="button" id="upload-btn" >上传</button>
    <input align="center"  style="border: none;" type="text" id="info"/>
</form>
<script src="../../js/jquery.min.js"></script>
<script>
    $(document).ready(function() {
        // 监听文件选择事件
        $('#file-input').on('change', function(event) {
            var file = event.target.files[0];
            var reader = new FileReader();

            reader.onload = function(event) {
                var imgPreview = $('#img-preview');
                imgPreview.attr('src', event.target.result);
            };

            reader.readAsDataURL(file);
        });

        // 监听按钮点击事件
        $('#upload-btn').on('click', function() {
            // 构建 FormData 对象
            var formData = new FormData($('#upload-form')[0]);

            // 发送 AJAX 请求
            $.ajax({
                url: 'updateDuty',
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                success: function(response) {
                    console.log("成功！！！");
                    // 处理成功响应
                    console.log(response);
                    $("#info").val(response);
                },
                error: function(xhr, status, error) {
                    console.log("失败！！！");
                    // 处理错误响应
                    console.error(error);
                }
            });
        });
    });
    <%!
        private String convertToBase64(InputStream inputStream) throws IOException {
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                byte[] bytes = outputStream.toByteArray();
                return Base64.getEncoder().encodeToString(bytes);
            }
        }

    %>
    <%InputStream inputStream = (InputStream) session.getAttribute("duty");
    String encodedImage = convertToBase64(inputStream);%>

    // 获取session中的duty值
    var base64Image = '<%= encodedImage %>';

    // 设置img标签的src属性为base64编码的图片数据
    document.getElementById('img-preview').src = 'data:image/jpeg;base64,' + base64Image;

    // 如果图片格式不是JPEG，需要根据实际情况修改data URI中的MIME类型


</script>
</body>
</html>


