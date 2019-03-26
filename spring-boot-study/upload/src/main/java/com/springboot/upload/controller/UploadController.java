package com.springboot.upload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 上传文件控制器
 * 直接上传到服务器
 */
@Controller
public class UploadController {
    //制定一个临时路径
    //private  static String UPLOAD_FOLDER="E:/temp/";

    //遇到http://localhost:8080则跳转到upload.html
    @GetMapping("/")
    public String index(){
        return "upload";
    }
    @PostMapping("/upload")
    public String fileUpload(@RequestParam("file") MultipartFile srcFile, RedirectAttributes redirectAttributes){
        if(srcFile.isEmpty()){
            redirectAttributes.addFlashAttribute("message","选择一个文件");
            return "redirect:upload_status";
        }
        try {
            File destFile=new File(ResourceUtils.getURL("classpath").getPath());
            if(destFile.exists()){
                destFile=new File("");
            }
            //输出目标文件的绝对路径
            System.out.println("file path:"+destFile.getAbsolutePath());
            //拼接子路经
            File upload=new File(destFile.getAbsolutePath(),"static/");
            //目标文件不存在，创建一个
            if(!upload.exists()){
                upload.mkdirs();
            }
            System.out.println("完整的上传路径："+upload.getAbsolutePath()+"/"+srcFile.getOriginalFilename());
            byte[] bytes=srcFile.getBytes();
            //拼接上传路径
            //Path path= Paths.get(UPLOAD_FOLDER+srcFile.getOriginalFilename());
            //通过项目路径,拼接上传路径
            Path path=Paths.get(upload.getAbsolutePath()+"/"+srcFile.getOriginalFilename());
            Files.write(path,bytes);
            redirectAttributes.addFlashAttribute("message","文件上传成功");
        }catch (IOException e){
            e.printStackTrace();
        }
        return "redirect:upload_status";
    }
    //匹配upload_status
    @GetMapping("/upload_status")
    public String uploadStatusPage(){
        return "upload_status";
    }
}
