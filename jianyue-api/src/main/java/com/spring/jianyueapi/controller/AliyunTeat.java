package com.spring.jianyueapi.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

import java.io.File;
import java.net.URL;
import java.util.Date;

public class AliyunTeat {
    public static void main(String[] args) {
        String endpoint="http://oss-cn-hangzhou.aliyuncs.com";
        //阿里云主账号Accesskey拥有所有API的访问权限,风险很高
        String accesskeyid="LTAIL4bn0wGEtnsG";
        String accesskeysecret="KMwPoHxc95LnRAfmaIL6sAVJ4M9PGc";
        String bucketName="niit-soft-wjl";
        String filedir="avatar/";
        String filekey="hello.jpg";
        //创建ossClient实例
        OSSClient ossClient=new OSSClient(endpoint,accesskeyid,accesskeysecret);
        //上传文件
        PutObjectResult putRequest=ossClient.putObject(bucketName,filedir+filekey,new File("D://100.jpg"));
        Date expiration=new Date(new Date().getTime()+3600*1000*24*365*10);
        //生成url
        URL url=ossClient.generatePresignedUrl(bucketName,filedir+filekey,expiration);
        System.out.println(url);
        ossClient.shutdown();
    }
}
