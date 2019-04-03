package com.spring.jianyueapi.controller;

import com.aliyun.oss.OSSClient;
import com.spring.jianyueapi.entity.User;
import com.spring.jianyueapi.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api")
public class UploadController {
    @Resource
    private UserService userService;
    @PostMapping("/avatar/upload")
    public String ossUpload(@RequestParam("file")MultipartFile sourceFile,@RequestParam("id") Integer id) {
        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
        //阿里云主账号Accesskey拥有所有API的访问权限,风险很高
        String accesskeyid = "LTAIL4bn0wGEtnsG";
        String accesskeysecret = "KMwPoHxc95LnRAfmaIL6sAVJ4M9PGc";
        String bucketName = "niit-soft-wjl";
        String filedir = "avatar/";
        //获取原文件名
        String filename = sourceFile.getOriginalFilename();
        //获取源文件名后缀
        String suffix=filename.substring(filename.lastIndexOf("."));
        //uuid生成主文件名
        String prefix= UUID.randomUUID().toString();
        //新文件名
        String newFileName=prefix+suffix;
        //file类型的临时文件
        File tempFile=null;
        //创建临时文件,用uuid主文件名+原名后缀
        try {
            tempFile=File.createTempFile(prefix,prefix);
            //MultipartFile转成File
            sourceFile.transferTo(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        OSSClient ossClient=new OSSClient(endpoint,accesskeyid,accesskeysecret);
        ossClient.putObject(bucketName,filedir+newFileName,tempFile);
        Date expiration=new Date(new Date().getTime()+3600*1000*24*365*10);
        //生成url
        URL url=ossClient.generatePresignedUrl(bucketName,filedir+newFileName,expiration);
        ossClient.shutdown();
        //根据userID查询出原始用户数据
        User user=userService.getUserById(id);
        //更新头像属性
        user.setAvatar(url.toString());
        //修改用户信息并持久化
        userService.update(user);
        //将头像链接返回给客户端,以便实时预览
        return  url.toString();
    }
}
