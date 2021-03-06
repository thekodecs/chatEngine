package ru.asic.dialog.chatEngine.ui.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ru.asic.dialog.chatEngine.ui.model.payloads.HistoryPayload;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class HistoryJSON {
       private static String historyFilePath = "../chatEngine/src/main/java/ru/asic/dialog/chatEngine/ui/controller/users_history.log"; //"/git/dialog/chatEngine/users_history.log"; //"../chatEngine/src/main/java/ru/asic/dialog/chatEngine/ui/controller/users_history.log";

    public static void appendToHistory (HistoryPayload payload) throws IOException {
        //File history = new File (historyFilePath);
        BufferedWriter historyWriter = new BufferedWriter(new FileWriter(historyFilePath, true));
        System.out.println("trying to write string " + payload.toString() + " in file: " + historyFilePath);
        historyWriter.write(payload.toString() + ",\r\n");
        historyWriter.close();
    }


    public static ArrayList<HistoryPayload> historyToJson ()  throws IOException {
        ArrayList<HistoryPayload> historyList = new ArrayList<>();
        BufferedReader historyReader = new BufferedReader(new InputStreamReader(new FileInputStream(historyFilePath), StandardCharsets.UTF_8));
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        String line;
        while (historyReader.ready()) {
            if (!(line = historyReader.readLine()).isEmpty()) {
                historyList.add(mapper.readValue(line, HistoryPayload.class));
            }
        }

        System.out.println(mapper.toString());
        return historyList;
    }



}
