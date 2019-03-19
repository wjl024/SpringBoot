package com.spring.mybatis.springbootdemo.util;

import java.util.Random;

public class RandomUtil {
    public static String getRandomCode(){
        StringBuffer stringBuffer=new StringBuffer();
        Random random=new Random();
        for(int i=0;i<6;i++){
            //[0,num)
            int num=random.nextInt(10);
            //将随机数加入可变长字符窜
            stringBuffer.append(num);
        }
        return stringBuffer.toString();
    }
}
