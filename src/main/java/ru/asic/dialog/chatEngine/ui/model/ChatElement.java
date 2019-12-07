package ru.asic.dialog.chatEngine.ui.model;

public class ChatElement {
    private String type;
    private String id;
    private String text;
    private String[] callers;

    public ChatElement(String type, String id, String text, String[] callers) {
        this.type = type;
        this.id = id;
        this.text = text;
        this.callers = callers;
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

    public String[] getCallers() {
        return callers;
    }
}
