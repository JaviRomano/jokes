package com.fdez_rumi_jokes.app.config;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
public class JwtConfig {
	
	@Value("${jwt.secret-key}")
    private String secretKey;
    
    @Bean
    public JwtDecoder jwtDecoder() {
        SecretKey key = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        return NimbusJwtDecoder.withSecretKey(key).build();
    }
    
    @Bean
    public JwtEncoder jwtEncoder() {
        SecretKey key = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        ImmutableSecret<SecurityContext> jwkSource = new ImmutableSecret<>(key);
        return new NimbusJwtEncoder(jwkSource);
    }     
}