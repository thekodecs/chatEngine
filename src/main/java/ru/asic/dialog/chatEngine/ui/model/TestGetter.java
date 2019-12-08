package ru.asic.dialog.chatEngine.ui.model;

import java.io.*;
import java.util.ArrayList;

public class TestGetter {

    public static class JsonTest {
        public String id;
        public String name;
    }

    public static void main(String[] args) throws IOException{
        String fileName = "../chatEngine/src/main/java/ru/asic/dialog/chatEngine/ui/config/script_init.cfg";
        String fileLoc = "../chatEngine/src/main/java/ru/asic/dialog/chatEngine/ui/config/locale_init_Ru.cfg";
        ArrayList<ChatElement> chatElements = ConfigReader.readChatElementsFromConfig(fileName, fileLoc);
        for (ChatElement chatElement: chatElements) {
            System.out.println(chatElement.toString());
        }
        ChatElement chatElement = ConfigReader.getChatElementByID("card_cardType", chatElements);
        System.out.println(chatElement);
        ChatElement[] elems = ConfigReader.getChatElementsFromLinks("card_cardType", chatElements);
        for (ChatElement el:elems) {
            System.out.println(el);
        }
    }

}
