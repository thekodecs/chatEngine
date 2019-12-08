package ru.asic.dialog.chatEngine.ui.controller;

import org.springframework.objenesis.SpringObjenesis;
import org.springframework.web.bind.annotation.*;
import ru.asic.dialog.chatEngine.ui.model.*;
import ru.asic.dialog.chatEngine.ui.model.payloads.HistoryPayload;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("request") // http://localhost:8080/request
public class RequestController {

    @PostMapping()
    public Container onUserClick (@RequestBody HistoryPayload clickedMessagePayload) {
        try {
            clickedMessagePayload.setMessageText(LocaleReader.getTranslationById(clickedMessagePayload.getLanguage(), clickedMessagePayload.getMessageId()));
            clickedMessagePayload.setUserAnswerText(LocaleReader.getTranslationById(clickedMessagePayload.getLanguage(), clickedMessagePayload.getChatElementId()));
            HistoryJSON.appendToHistory(clickedMessagePayload);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String chatElementID = clickedMessagePayload.getChatElementId();
        String configPath = "../chatEngine/src/main/java/ru/asic/dialog/chatEngine/ui/config/";
        String configFile = configPath + getScriptPath(chatElementID, "begin");
        String localeFile = configPath + getLocalePath(clickedMessagePayload.getLanguage(), chatElementID);
        ArrayList<ChatElement> chatElements = new ArrayList<>();
        ChatElement[] arrayChatElements;
        try {
            System.out.println("Try 1");
            System.out.println(configFile + " " + localeFile);
            chatElements = ConfigReader.readChatElementsFromConfig(configFile, localeFile);
            arrayChatElements = ConfigReader.getChatElementsFromLinks(chatElementID, chatElements);
            if (arrayChatElements[0].getLinksID().length == 0) {
                configFile = configPath + getScriptPath(chatElementID, "");
                System.out.println("Try 2");
                System.out.println(configFile + " " + localeFile);
                chatElements = ConfigReader.readChatElementsFromConfig(configFile, localeFile);
            }
        }
        catch (IOException e) {}
        ChatElement chatElement = ConfigReader.getChatElementByID(chatElementID, chatElements);
        if (chatElement == null) {chatElement = new ChatElement();}
        return new Container(chatElement, ConfigReader.getChatElementsFromLinks(chatElement.getId(), chatElements));
    }

//    public static void main (String[] args) {
//        System.out.println(getScriptPath("init_card", "begin") + " " + getScriptPath("init_card", ""));
//        System.out.println(getLocalePath("Ru", "init_card"));
//    }

    @PostMapping(path="/init")
    public Container initLanguage() {
        ChatElement resultElement = new ChatElement("message", "init_init","Выберите язык / Choose your language", new String[] {"init_init"});
        ChatElement[] elements = new ChatElement[2];
        elements[0] = new ChatElement("button", "init_RU", "Русский", new String[] {"init_init"});
        elements[1] = new ChatElement("button", "init_EN", "English", new String[] {"init_init"});
        String configFolderPath;
        String scriptInitFilename;

        return new Container(resultElement, elements);
    }

    @PostMapping(path="/test")
    public Container initTest() {
        ChatElement resultElement = new ChatElement("message", "init","Выберите язык (Choose your language)", new String[] {"init"});
        ChatElement[] elements = new ChatElement[2];
        elements[0] = new ChatElement("button", "RU", "Русский", new String[] {"init"});
        elements[1] = new ChatElement("button", "EN", "English", new String[] {"init"});
        String configFolderPath;
        String scriptInitFilename;

        return new Container(resultElement, elements);
    }

    @PostMapping(path="/{langSetting}/messages/{messageId}")
    public String getMessageById(@PathVariable("langSetting") String langSetting,
                                 @PathVariable("messageId") String messageId) {
        return "there is should be a message with id = " + messageId + ", which is translated to " + langSetting;
    }

    private static String getScriptPath(String elementId, String position) {
        String product;
        if (position.equals("begin")) {
            product = elementId.split("_")[0];

        } else {
            product = elementId.split("_")[1];
        }
        String fileName = String.format("script_%s.cfg", product);
        return fileName;
    }

    private static String getLocalePath (String languageCode, String elementId) {
        String product = elementId.split("_")[0];
        String filename = String.format("locale_%s_%s.cfg", product,languageCode);
        return filename;
    }

}
