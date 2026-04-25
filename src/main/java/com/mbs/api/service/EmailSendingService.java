package com.mbs.api.service;

import com.mbs.api.entity.SmsEntity;
import com.mbs.api.repository.SmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author 'Bilol Tuxtamurodov' on 15.03.2026
 * @project Lesson_email_sending
 * @contact @BilolTuxtamurodov
 */

@Service
public class EmailSendingService {
    @Value("${spring.mail.username}")
    private String fromAccount;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SmsRepository smsRepository;


    public String sendingSimpleMessage(String email, Integer code) {
        SmsEntity sms = new SmsEntity();
        sms.setCode(code);
        sms.setEmail(email);
        sms.setUsed(false);
        smsRepository.save(sms);

        String text = "Kun uz uchun tasdiqlash code : " + code;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromAccount);
        message.setTo(email);
        message.setSubject("Ro'yhatdan o'tish");
        message.setText(text);
        javaMailSender.send(message);
        return "Xabar yuborildi";
    }
}
