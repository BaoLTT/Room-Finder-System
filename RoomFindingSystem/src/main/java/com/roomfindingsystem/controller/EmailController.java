//package com.roomfindingsystem.controller;
//
//
//import com.roomfindingsystem.service.EmailSenderService;
//import com.roomfindingsystem.vo.EmailMessage;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class EmailController {
//
//    private final EmailSenderService emailSenderService;
//
//    public EmailController(EmailSenderService emailSenderService) {
//        this.emailSenderService = emailSenderService;
//    }
//
//    //    @RequestMapping(value = "/send-email", method = RequestMethod.POST)
//    @PostMapping("/send")
//    public ResponseEntity sendEmail(@ModelAttribute EmailMessage emailMessage) {
//        String mess = emailMessage.getMessage().concat(otpCode());
//        this.emailSenderService.sendEmail(emailMessage.getTo(), emailMessage.getSubject(), mess);
//        return ResponseEntity.ok("Success");
//    }
//    public String otpCode(){
//        int code = (int) Math.floor(((Math.random() * 899999) + 100000));
//        return String.valueOf(code);
//    }
//}