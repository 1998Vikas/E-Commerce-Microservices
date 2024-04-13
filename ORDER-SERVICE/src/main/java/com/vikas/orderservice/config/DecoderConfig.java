package com.vikas.orderservice.config;

import com.vikas.orderservice.exception.decoder.CustomDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DecoderConfig {
    @Bean
    public CustomDecoder createConfig(){
        return new CustomDecoder();
    }
}
