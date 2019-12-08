package ru.asic.dialog.chatEngine.ui.controller;

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
        String configFile = "../chatEngine/src/main/java/ru/asic/dialog/chatEngine/ui/config/script_card.cfg";
        String localeFile = "../chatEngine/src/main/java/ru/asic/dialog/chatEngine/ui/config/locale_card_Ru.cfg";
        ArrayList<ChatElement> chatElements = null;
        try {
            chatElements = ConfigReader.readChatElementsFromConfig(configFile, localeFile);
        }
        catch (IOException e) {}

        return new Container(ConfigReader.getChatElementByID(chatElementID, chatElements), ConfigReader.getChatElementsFromLinks(chatElementID, chatElements));
    }

    @PostMapping(path="/init")
    public Container initLanguage() {
        ChatElement resultElement = new ChatElement("message", "init","Выберите язык / Choose your language", new String[] {"init"});
        ChatElement[] elements = new ChatElement[2];
        elements[0] = new ChatElement("button", "RU", "Русский", new String[] {"init"});
        elements[1] = new ChatElement("button", "EN", "English", new String[] {"init"});
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


}
