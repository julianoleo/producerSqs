package com.sqs.producer.service;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import java.util.concurrent.CompletableFuture;


@Component
public class SenderSqs {

    @Value("${sqs.fila}")
    private String filaSqs;

    private Logger LOGGER = LoggerFactory.getLogger(SenderSqs.class);

    private final SqsAsyncClient sqsAsyncClient;

    public SenderSqs(SqsAsyncClient sqsAsyncClient) {
        this.sqsAsyncClient = sqsAsyncClient;
    }

    public void sendMsg(String qtdeMsg, String payload) {
        try {
            LOGGER.info("Enviado " + qtdeMsg + " mensagens para fila SQS --> " + filaSqs);

            CompletableFuture wat = sqsAsyncClient.getQueueUrl(
                    GetQueueUrlRequest.builder()
                            .queueName(filaSqs)
                            .build()
            );
            GetQueueUrlResponse getQueueUrlResponse = (GetQueueUrlResponse) wat.get();

            String[] dados = payload.replaceAll("\\s+", " ").split(",");
            JSONObject object = new JSONObject();
            for(int i = 0; i < dados.length; i++) {
                String[] result = dados[i].split(":");
                object.put(result[0].trim().replaceAll("\"", ""), result[1].trim().replaceAll("\"", ""));
            }
            int numMsg = Integer.parseInt(qtdeMsg);

            var msg = SendMessageRequest.builder()
                    .queueUrl(getQueueUrlResponse.queueUrl())
                    .messageBody(object.toString())
                    .build();

            for(int i = 0; i < numMsg; i++) {
                sqsAsyncClient.sendMessage(msg);
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
