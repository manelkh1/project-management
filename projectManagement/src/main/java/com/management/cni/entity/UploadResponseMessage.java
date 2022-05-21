package com.management.cni.entity;

public class UploadResponseMessage {

// for attachments
    private final String responseMessage;

    public UploadResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
