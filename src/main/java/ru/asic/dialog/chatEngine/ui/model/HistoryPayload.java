package ru.asic.dialog.chatEngine.ui.model;

import java.time.LocalDateTime;
import java.util.Date;

public class HistoryPayload {
    private String language;
    private String buttonID;
    private Long messageReceivedAt = new Date().getTime();

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getButtonID() {
        return buttonID;
    }

    public Long getMessageReceivedAt() {
        return messageReceivedAt;
    }

    public void setButtonID(String buttonID) {
        this.buttonID = buttonID;
    }
}
