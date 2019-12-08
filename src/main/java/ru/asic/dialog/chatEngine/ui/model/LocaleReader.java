package ru.asic.dialog.chatEngine.ui.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class LocaleReader {

    private static String localeFolderPath = "../chatEngine/src/main/java/ru/asic/dialog/chatEngine/ui/config/";

    public static String getTranslationById (String languageCode, String elementId) throws IOException {
        String localeFilePath = generateLocaleFilePath(languageCode, elementId);
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(localeFilePath), StandardCharsets.UTF_8));
        ObjectMapper mapper = new ObjectMapper();
        String result = "id не найден в файле - " + localeFilePath;
        String line = "";
        while (fileReader.ready()) {
            if (!(line = fileReader.readLine()).isEmpty()) {
                System.out.println(line);
                LocateParams currentLine = new ObjectMapper().readValue(line, LocateParams.class);
                if (currentLine.id.equals(elementId)) {
                    line = currentLine.text;
                    break;
                }
                System.out.println(currentLine);
            }
        }
        fileReader.close();
        return line;

    }

    private static String generateLocaleFilePath (String languageCode, String elementId) {
        String product = elementId.split("_")[0];
        String filename = String.format("locale_%s_%s.cfg", product,languageCode);
        return localeFolderPath + filename;
    }

}
