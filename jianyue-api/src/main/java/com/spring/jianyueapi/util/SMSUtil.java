package com.spring.jianyueapi.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class SMSUtil {
    public static String send(String mobile){
        DefaultProfile profile=DefaultProfile.getProfile("cn-hangzhou","LTAIL4bn0wGEtnsG","KMwPoHxc95LnRAfmaIL6sAVJ4M9PGc");
        IAcsClient client=new DefaultAcsClient(profile);
        CommonRequest request=new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", "jianyuesms");
        request.putQueryParameter("TemplateCode", "SMS_162732247");
        String verifyCode = StringUtil.getVerifyCode();
        request.putQueryParameter("TemplateParam","{\"code\":"+verifyCode+"}");
        CommonResponse response= null;
        try {
            response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return verifyCode;
    }

}
