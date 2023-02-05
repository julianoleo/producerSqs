package com.sqs.producer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequestMapping("/msg")
public interface IController {
    @GetMapping("/{qtdeMsg}")
    @ResponseStatus(HttpStatus.CREATED)
    void incluiMsgSqs(
            @PathVariable("qtdeMsg")
            String qtdeMsgs,
            @RequestBody
            String payload
    );
}
