package ru.asic.dialog.chatEngine.ui.model;

import java.io.*;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.print.DocFlavor;

public class ConfigReader {
    private String configPath;
    private String localePath;
    private String folderPath = "";

    public ConfigReader(String configPath, String localePath) {
        this.configPath = configPath;
        this.localePath = localePath;
    }

    private String generateLocaleFilePath (String languageCode, String elementId) {
        String product = elementId.split("_")[0];
        String filename = String.format("locale_%s_%s.cfg", product,languageCode);
        return folderPath + filename;
    }

    public static ArrayList<ChatElement> readChatElementsFromConfig(String configPath, String localePath) throws IOException {
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
            chatElements.add(new ChatElement(item.type, item.id, getTextFromLocale(item.id, locates), item.linksID));
        }
        return chatElements;
    }

    public static ChatElement[] getChatElementsFromLinks (String chatElementID, ArrayList<ChatElement> chatElements) {
        ChatElement[] elements = new ChatElement[getLinkContainsCount(chatElementID, chatElements)];
        int count = 0;
        for (ChatElement chatElement: chatElements) {
            for (String linkID: chatElement.getLinksID()) {
                if (linkID.equals(chatElementID)) {
                    elements[count] = chatElement;
                    count++;
                    break;
                }
            }
        }
        return elements;
    }

    public static ChatElement getChatElementByID (String id, ArrayList<ChatElement> chatElements) {
        ChatElement chatElement = null;
        for (ChatElement element: chatElements) {
            if (element.getId().equals(id)) {
                chatElement = element;
                break;
            }
        }
        return chatElement;
    }

//    public static Container getContainer(String chatElementID) {
//        ArrayList<ChatElement> chatElements;
//        try {
//            chatElements = readChatElementsFromConfig(configPath, localePath);
//        }
//        catch (IOException e) {chatElements = null; return null;}
//        return new Container(getChatElementByID(chatElementID, chatElements), getChatElementsFromLinks(chatElementID, chatElements));
//    }

    private static int getLinkContainsCount (String id, ArrayList<ChatElement> chatElements) {
        int count = 0;
        for (ChatElement chatElement: chatElements) {
            for (String linkID: chatElement.getLinksID()) {
                if (linkID.equals(id)) {count++;}
            }
        }
        return count;
    }

    private static String getTextFromLocale(String itemID, ArrayList<LocateParams> locales) {
        String text = "";
        for (LocateParams locate: locales) {
            if (locate.id.equals(itemID)) {
                text = locate.text;
                break;
            }
        }
        return text;
    }
}
