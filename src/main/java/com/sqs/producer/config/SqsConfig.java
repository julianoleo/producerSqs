package com.sqs.producer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.net.URI;

@Configuration
public class SqsConfig {

    @Value("${sqs.uri}")
    private String sqsUri;

    @Bean
    public SqsAsyncClient amazonSqsAsyncClient(){
        return SqsAsyncClient.builder()
                .endpointOverride(URI.create(sqsUri))
                .region(Region.SA_EAST_1)
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                new AwsCredentials() {
                                    @Override
                                    public String accessKeyId() { return  "FAKE"; }

                                    @Override
                                    public String secretAccessKey() { return "FAKE"; }
                                }
                        )
                )
                .build();
    }
}
