package ru.asic.dialog.chatEngine.ui.controller;

import org.springframework.web.bind.annotation.*;
import ru.asic.dialog.chatEngine.ui.model.*;
import ru.asic.dialog.chatEngine.ui.model.payloads.HistoryPayload;

import java.io.IOException;

@RestController
@RequestMapping("request") // http://localhost:8080/request
public class RequestController {

    @PostMapping()
    public void onUserClick (@RequestBody HistoryPayload clickedMessagePayload) {
        try {
            clickedMessagePayload.setMessageText(LocaleReader.getTranslationById(clickedMessagePayload.getLanguage(), clickedMessagePayload.getMessageId()));
            clickedMessagePayload.setUserAnswerText(LocaleReader.getTranslationById(clickedMessagePayload.getLanguage(), clickedMessagePayload.getButtonId()));
            HistoryJSON.appendToHistory(clickedMessagePayload);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

//    @PostMapping(path="/history")
//    public Container sendHistoryContainer(@RequestBody LocateParams langPayload){
//        // TODO собрать в json все строки или часть строк и отправить согласно ворда. (return)
//        return new Container();
//    }

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
    //public ChatElement  // locale


    @PostMapping(path="/{langSetting}/messages/{messageId}")
    public String getMessageById(@PathVariable("langSetting") String langSetting,
                                 @PathVariable("messageId") String messageId) {
        return "there is should be a message with id = " + messageId + ", which is translated to " + langSetting;
    }

}
