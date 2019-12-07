package ru.asic.dialog.chatEngine.ui.model;

public class ChatElement {
    private TypeOfElement typeOfElement;
    private String id;
    private String text;
    private String[] callers;

    public ChatElement(TypeOfElement typeOfElement, String id, String text, String[] callers) {
        this.typeOfElement = typeOfElement;
        this.id = id;
        this.text = text;
        this.callers = callers;
    }

    public TypeOfElement getTypeOfElement() {
        return typeOfElement;
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
