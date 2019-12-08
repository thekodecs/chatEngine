package ru.asic.dialog.chatEngine.ui.model;

import java.util.Arrays;

public class ChatElement {
    private String type;
    private String id;
    private String text;
    private String[] linksID;

    public ChatElement(String type, String id, String text, String[] callers) {
        this.type = type;
        this.id = id;
        this.text = text;
        this.linksID = callers;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String[] getLinksID() {
        return linksID;
    }

    public String toString() {
        return id + " " + type + " " + text + " " + Arrays.toString(linksID);
    }
}
