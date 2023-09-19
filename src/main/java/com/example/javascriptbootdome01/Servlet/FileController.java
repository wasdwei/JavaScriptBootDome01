package com.example.javascriptbootdome01.Servlet;


import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URLEncoder;
import java.util.UUID;

/*
 * 文件管理控制类
 * */
@Controller
public class FileController {
    @GetMapping("/toUpload")
    public String toUpload() {
        return "upload";
    }

    //文件上传管理
    @PostMapping("/uploadFile")
    public String uploadFile(MultipartFile[] fileUpload, Model model) {
        //默认文件上传成功，并返回状态信息
        model.addAttribute("uploadStatus", "上传成功!");
        for (MultipartFile file : fileUpload) {
            //获取文件名以及后缀名
            String fileName = file.getOriginalFilename();
            //重新生成文件名（根据具体情况生成对应文件名）
            fileName = UUID.randomUUID() + "_" + fileName;
            //指定上传文件本地存储目录，不存在则需要提前创建
            String dirPath = "D:/File/";
            File filePath = new File(dirPath);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            try {
                file.transferTo(new File(dirPath + fileName));
            } catch (Exception e) {
                e.printStackTrace();
                //上传失败,返回失败信息
                model.addAttribute("uploadStatus", "上传失败:" + e.getMessage());
            }
        }
        //携带上传状态信息回调到文件上传页面
        return "upload";
    }
    //向文件下载页面跳转
    @GetMapping("/toDownload")
    public String toDownload() {
        return "download";
    }

    //文件下载管理
    @GetMapping("/download")
    public ResponseEntity<byte[]> fileDownload(HttpServletRequest request, String filename)throws Exception {
        //指定要下载的文件根路径
        String dirPath = "D:/File/";
        //创建该文件对象
        File file = new File(dirPath + File.separator + filename);
        //设置响应头
        HttpHeaders headers = new HttpHeaders();
        //通知浏览器以下载的方式打开
        filename=getFilename(request,filename);
        headers.setContentDispositionFormData("attachment", filename);
        //定义以流的形式下载返回文件数据
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        try {
            return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<byte[]>(e.getMessage().getBytes(), HttpStatus.EXPECTATION_FAILED);
        }
    }
    //根据浏览器的不同进行编码后的文件名
    private String getFilename(HttpServletRequest request, String filename)
            throws Exception {
        String[] IEBrowserKeyWords = {"MSIE", "Trident", "Edge"};
        String userAgent = request.getHeader("User-Agent");
        for (String keyWord : IEBrowserKeyWords) {
            if (userAgent.contains(keyWord)) {
                return URLEncoder.encode(filename, "UTF-8").replace("+"," ");
            }}
        return new String(filename.getBytes("UTF-8"), "ISO-8859-1");
    }

}

