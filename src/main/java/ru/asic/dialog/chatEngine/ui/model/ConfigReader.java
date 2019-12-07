package ru.asic.dialog.chatEngine.ui.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

public class ConfigReader {
    private String configPath;
    private String localePath;
    private ArrayList<ChatElement> elements;

    public ConfigReader(String configPath, String localePath) {
        this.configPath = configPath;
        this.localePath = localePath;
    }

    private ArrayList<ChatElement> readChatElementsFromConfig(String configPath, String localePath) throws FileNotFoundException {
        ArrayList<ChatElement> chatElements = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(configPath)));
//        while (reader.ready())
        return chatElements;
    }

//    private Map<String, String>
    //    public ChatElement getChatElement(String elementID) {return }
}
