package ru.asic.dialog.chatEngine.ui.model;

import com.bluelinelabs.logansquare.LoganSquare;
import com.bluelinelabs.logansquare.annotation.JsonField;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;

public class TestGetter {

    public static class JsonTest {
        public String id;
        public String name;
    }

    public static void main(String[] args) throws IOException{
        String fileName = "/home/kirill/Projects/chatEngine/src/main/java/ru/asic/dialog/chatEngine/ui/config/script_card.cfg";
        String fileLoc = "/home/kirill/Projects/chatEngine/scr/main/java/ru/asic/dialog/chatEngine/ui/config/local_card_Ru.cfg";
        ArrayList<TestJSON> elements = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        ObjectMapper mapper = new ObjectMapper();
        while (reader.ready()) {
            String jsonString = reader.readLine();
            TestJSON testJSON = mapper.readValue(jsonString, TestJSON.class);
            elements.add(testJSON);
        }

        for (TestJSON element: elements) {
            System.out.println(element.getId() + " " + element.getTypeOfElement());
        }
//        String jsonString = "{\"id\":\"ident\",\"name\":\"Artem\"}";
////        TestJSON testJSON = LoganSquare.parse(jsonString, TestJSON.class);
////        System.out.println(testJSON.getId());
//        ObjectMapper mapper = new ObjectMapper();
//        JsonTest jsonTest = mapper.readValue(jsonString, JsonTest.class);
//        System.out.println(jsonTest.id + " " + jsonTest.name);
    }
}
