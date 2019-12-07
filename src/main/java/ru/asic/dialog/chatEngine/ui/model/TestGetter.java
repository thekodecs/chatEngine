package ru.asic.dialog.chatEngine.ui.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class TestGetter {

    public static class JsonTest {
        public String id;
        public String name;
    }

    public static void main(String[] args) throws IOException{
        String fileName = "/home/kirill/Projects/chatEngine/src/main/java/ru/asic/dialog/chatEngine/ui/config/script_card.cfg";
        String fileLoc = "/home/kirill/Projects/chatEngine/src/main/java/ru/asic/dialog/chatEngine/ui/config/locale_card_Ru.cfg";
        ArrayList<ChatElement> chatElements = new ArrayList<>();
        chatElements = ConfigReader.readChatElementsFromConfig(fileName, fileLoc);
        for (ChatElement chatElement: chatElements) {
            System.out.println(chatElement.toString());
        }
//        ArrayList<TestJSON> elements = new ArrayList<>();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
//        ObjectMapper mapper = new ObjectMapper();
//        while (reader.ready()) {
//            String jsonString = reader.readLine();
//            TestJSON testJSON = mapper.readValue(jsonString, TestJSON.class);
//            elements.add(testJSON);
//        }
//
//        for (TestJSON element: elements) {
//            System.out.println(element.id + " " + element.type + " " + Arrays.toString(element.linksID));
//        }
    }
}
