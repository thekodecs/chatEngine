package ru.asic.dialog.chatEngine.ui.model.payloads;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

public class HistoryPayload {

    private String language;
    private String type = "MESSAGE";
    private String messageId;
    private String messageReceivedAt = new Date().getTime() + "";
    private String messageText = "";
    private String buttonId;
    private String userAnswerText;

    public String getButtonId() {
        return buttonId;
    }

    public void setButtonId(String buttonId) {
        this.buttonId = buttonId;
    }

    public String getUserAnswerText() {
        return userAnswerText;
    }

    public void setUserAnswerText(String userAnswerText) {
        this.userAnswerText = userAnswerText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMessageReceivedAt(String messageReceivedAt) {
        this.messageReceivedAt = messageReceivedAt;
    }

    public String getMessageReceivedAt() {
        return messageReceivedAt;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageText() {
        return messageText;
    }

    @Override
    public String toString()  {
        String json = "Ошибка преобразования в JSON";
        try {
            json = new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}