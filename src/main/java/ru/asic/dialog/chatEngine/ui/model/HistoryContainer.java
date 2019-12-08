package ru.asic.dialog.chatEngine.ui.model;

import ru.asic.dialog.chatEngine.ui.model.payloads.HistoryPayload;

import java.util.ArrayList;

public class HistoryContainer {
    ArrayList<HistoryPayload> history;

    public HistoryContainer(ArrayList<HistoryPayload> payload) {
        this.history = payload;
    }
}
