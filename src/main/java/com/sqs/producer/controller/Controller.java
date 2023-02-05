package com.sqs.producer.controller;

import com.sqs.producer.service.SenderSqs;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Controller implements IController{

    @Autowired
    private SenderSqs senderSqs;

    @Override
    public void incluiMsgSqs(String qtdeMsgs, String payload) {
        senderSqs.sendMsg(qtdeMsgs, payload);
    }
}
