package ru.nexign.brtservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue newCallDataRecord() {
        return new Queue("call_data_records_plus", true);
    }

    @Bean
    public Queue abonentNumberAnswer() {
        return new Queue("abonent_numbers_answer", true);
    }
}