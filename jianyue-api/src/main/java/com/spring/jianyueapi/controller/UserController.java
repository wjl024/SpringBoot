package com.spring.jianyueapi.controller;

import com.aliyun.oss.OSSClient;
import com.spring.jianyueapi.entity.User;
import com.spring.jianyueapi.entity.dto.UserDTO;
import com.spring.jianyueapi.service.RedisService;
import com.spring.jianyueapi.service.UserService;
import com.spring.jianyueapi.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private RedisService redisService;

    @PostMapping(value = "/sign_in")
    public ResponseResult signIn(@RequestBody UserDTO userDTO){
        System.out.println(userDTO);
        User user=userService.getUserByMobile(userDTO.getMobile());
        if (user==null){
            return ResponseResult.error(StatusConst.USER_MOBILE_NOT_FOUND, MsgConst.USER_MOBILE_NOT_FOUND);
        }else{
            userDTO.setPassword(StringUtil.getBase64Encoder(userDTO.getPassword()));
            int status=userService.signIn(userDTO);
            if(status==StatusConst.SUCCESS){
                return ResponseResult.success(user);
            }else if(status==StatusConst.PASSWORD_ERROR){
                return  ResponseResult.error(status,MsgConst.PASSWORD_ERROR);
            }else {
                return ResponseResult.error(status,MsgConst.USER_STATUS_ERROR);
            }
        }
    }

    @PostMapping("/avatar")
    public String ossUpload(@RequestParam("file") MultipartFile sourceFile, @RequestParam("id") Integer id) {
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

    @GetMapping(value = "/{id}")
    public ResponseResult getUserById(@PathVariable("id") Integer userId){
        User user=userService.getUserById(userId);
        return ResponseResult.success(user);
    }

    @PostMapping(value = "/verify")
    public ResponseResult getVerifyCode(@RequestParam("mobile") String mobile){
        User user=userService.getUserByMobile(mobile);
        if (user!=null){
            return ResponseResult.error(StatusConst.MOBILE_EXIST,MsgConst.MOBILE_EXIST);
        }else{
            //SMSUtil.send(mobile);
            String verifyCode= SMSUtil.send(mobile);
            System.out.println(verifyCode);
            redisService.set(mobile,verifyCode);
            return ResponseResult.success();
        }
    }

    @PostMapping(value = "/check")
    public ResponseResult checkVerifyCode(@RequestParam("mobile") String mobile,@RequestParam("verifyCode") String verifyCode){
        String code=redisService.get(mobile).toString();
        System.out.println(code+"---");
        System.out.println(verifyCode);
        if(code.equals(verifyCode)){
            return ResponseResult.success();
        }else {
            return ResponseResult.error(StatusConst.VERIFYCODE_ERROR,MsgConst.VERIFYCODE_ERROR);
        }
    }

    @PostMapping(value = "/sign_up")
    public ResponseResult signUp(@RequestBody UserDTO userDTO){
        userService.signUp(userDTO);
        return ResponseResult.success();
    }
}
