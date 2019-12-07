package ru.asic.dialog.chatEngine.ui.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.asic.dialog.chatEngine.ui.model.ChatElement;
import ru.asic.dialog.chatEngine.ui.model.TypeOfElement;

import java.awt.*;

@RestController
@RequestMapping("request") // http://localhost:8080/request
public class RequestController {

    @PostMapping(path="/init")
    public ChatElement initLanguage() {
        ChatElement resultElement = new ChatElement(TypeOfElement.MESSAGE, "m_init","", new String[] {"m_init"});
        String configFolderPath;
        String scriptInitFilename;

        return resultElement;
    }
    //public ChatElement  // locale


    @PostMapping(path="/{langSetting}/messages/{messageId}")
    public String getMessageById(@PathVariable("langSetting") String langSetting,
                                 @PathVariable("messageId") String messageId) {
        return "there is should be a message with id = " + messageId + ", which is translated to " + langSetting;
    }

}
