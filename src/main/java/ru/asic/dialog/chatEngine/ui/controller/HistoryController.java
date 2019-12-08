package ru.asic.dialog.chatEngine.ui.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import ru.asic.dialog.chatEngine.ui.model.HistoryJSON;
import ru.asic.dialog.chatEngine.ui.model.LocateParams;
import ru.asic.dialog.chatEngine.ui.model.payloads.HistoryPayload;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("history")
public class HistoryController {
    @PostMapping()
    public ArrayList<HistoryPayload> sendHistoryContainer(@RequestBody LocateParams langPayload){
        ArrayList<HistoryPayload> payloads = null;
        try {
            payloads = HistoryJSON.historyToJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return payloads;
    }

}
