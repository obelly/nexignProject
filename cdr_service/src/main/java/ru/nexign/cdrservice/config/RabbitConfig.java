package ru.nexign.cdrservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue newCallDataRecord(){
        return new Queue("new_call_data_record", true);
    }

    @Bean
    public Queue abonentNumbers(){
        return new Queue("abonent_numbers", true);
    }
}
