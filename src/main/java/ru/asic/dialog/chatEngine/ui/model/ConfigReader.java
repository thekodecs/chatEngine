package ru.asic.dialog.chatEngine.ui.model;

import java.io.*;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigReader {
    private String configPath;
    private String localePath;

    public ConfigReader(String configPath, String localePath) {
        this.configPath = configPath;
        this.localePath = localePath;
    }

    public ArrayList<ChatElement> readChatElementsFromConfig(String configPath, String localePath) throws IOException {
        ArrayList<ChatElement> chatElements = new ArrayList<>();
        ArrayList<ItemParams> items = new ArrayList<>();
        ArrayList<LocateParams> locates = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(configPath)));
        ObjectMapper mapper = new ObjectMapper();
        while (reader.ready()) {
            String jsonString = reader.readLine();
            if (!jsonString.isEmpty()) {
                items.add(mapper.readValue(jsonString, ItemParams.class));
            }
        }
        reader.close();
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(localePath)));
        while (reader.ready()) {
            String jsonString = reader.readLine();
            if (!jsonString.isEmpty()) {
                locates.add(mapper.readValue(jsonString, LocateParams.class));
            }
        }
        reader.close();
        for (ItemParams item: items) {
            chatElements.add(new ChatElement(item.type, item.id, getTextFromLocate(item.id, locates), item.linksID));
        }
        return chatElements;
    }

    private String getTextFromLocate(String itemID, ArrayList<LocateParams> locates) {
        String text = "";
        for (LocateParams locate: locates) {
            if (locate.id.equals(itemID)) {
                text = locate.text;
                break;
            }
        }
        return text;
    }
}
