package ru.asic.dialog.chatEngine.ui.model;

import ru.asic.dialog.chatEngine.ui.model.payloads.HistoryPayload;

import java.io.*;

public class HistoryJSON {

    public static void appendToHistory (HistoryPayload payload) throws IOException {
        String historyFilePath = "../chatEngine/src/main/java/ru/asic/dialog/chatEngine/ui/controller/users_history.log";
        BufferedWriter historyWriter = new BufferedWriter(new FileWriter(historyFilePath, true));
        historyWriter.write(payload.toString() + ",\r\n");
        historyWriter.close();
    }






}
