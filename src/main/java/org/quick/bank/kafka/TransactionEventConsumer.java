package org.quick.bank.kafka;

import lombok.extern.slf4j.Slf4j;
import org.quick.bank.events.TransactionEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransactionEventConsumer {

    private final SimpMessagingTemplate messagingTemplate;

    public TransactionEventConsumer(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @KafkaListener(topics = "transactions-topic", groupId = "bank-app-group")
    public void consumeTransactionEvent(TransactionEvent event) {
        log.info("Received transaction event from Kafka: {}", event);

        messagingTemplate.convertAndSend("/topic/transactions", event);
    }
}