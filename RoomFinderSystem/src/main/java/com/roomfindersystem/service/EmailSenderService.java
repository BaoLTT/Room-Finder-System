package com.roomfindersystem.service;

public interface EmailSenderService {
    void sendEmail(String to, String subject, String message);
}
