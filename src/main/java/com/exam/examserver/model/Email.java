package com.exam.examserver.model;

import org.springframework.stereotype.Component;

@Component
public class Email {
    private String to;
    private String subject;
    private String message;
    private Integer receivedOTP;

    public Email() {
    }

    public Email(String to, String subject, String message, Integer receivedOTP) {
        this.to = to;
        this.subject = subject;
        this.message = message;
        this.receivedOTP = receivedOTP;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getReceivedOTP() {
        return receivedOTP;
    }

    public void setReceivedOTP(Integer receivedOTP) {
        this.receivedOTP = receivedOTP;
    }

    @Override
    public String toString() {
        return "Email{" +
                "to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", receivedOTP=" + receivedOTP +
                '}';
    }
}
