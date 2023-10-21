package com.roomfindingsystem.reponsitory.service;

public interface EmailSenderService {
    void sendEmail(String to, String subject, String message);
}
