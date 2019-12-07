package ru.asic.dialog.chatEngine.ui.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.asic.dialog.chatEngine.ui.model.ChatElement;
import ru.asic.dialog.chatEngine.ui.model.Container;
import ru.asic.dialog.chatEngine.ui.model.TypeOfElement;

import java.awt.*;

@RestController
@RequestMapping("request") // http://localhost:8080/request
public class RequestController {

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
    //public ChatElement  // locale


    @PostMapping(path="/{langSetting}/messages/{messageId}")
    public String getMessageById(@PathVariable("langSetting") String langSetting,
                                 @PathVariable("messageId") String messageId) {
        return "there is should be a message with id = " + messageId + ", which is translated to " + langSetting;
    }

}
