package org.quick.bank.kafka;

import lombok.extern.slf4j.Slf4j;
import org.quick.bank.events.TransactionEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Slf4j
@Service
public class OrderProducer {

    private static final String TRANSACTION_TOPIC = "transactions-topic";
    private final KafkaTemplate<String, TransactionEvent> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, TransactionEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(TransactionEvent event) {
        CompletableFuture<SendResult<String, TransactionEvent>> future =
                kafkaTemplate.send(TRANSACTION_TOPIC, String.valueOf(event.getTransactionId()), event);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Transaction event sent to Kafka successfully: {}, offset: {}",
                        event, result.getRecordMetadata().offset());
            } else {
                log.error("Failed to send transaction event to Kafka: {}", event, ex);
            }
        });
    }
}
