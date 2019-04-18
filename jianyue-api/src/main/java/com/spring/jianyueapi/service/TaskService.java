package com.spring.jianyueapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    private MailService mailService;

    @Scheduled(cron ="0 59 9 * * ?" )

    public void proces(){
        mailService.sendMail("16422802@qq.com","王洁磊","9点59分发送");
    }
}
