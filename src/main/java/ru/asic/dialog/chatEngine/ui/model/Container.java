package ru.asic.dialog.chatEngine.ui.model;

public class Container {
    public ChatElement message;
    public ChatElement[] elements;
    public Container (ChatElement messageElement, ChatElement[] elements) {
        this.message = messageElement;
        this.elements = elements;
    }

}
