package com.fdez_rumi_jokes.app.service;

import java.time.Instant;
import java.util.List;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

	private final JwtEncoder jwtEncoder;

	public JwtService(JwtEncoder jwtEncoder) {
		this.jwtEncoder = jwtEncoder;
	}

	public String generateToken(String username, List<String> roles) {
		Instant now = Instant.now();
		JwtClaimsSet claims = JwtClaimsSet.builder()
				.issuer("self")
				.issuedAt(now)
				.expiresAt(now.plusMillis(43200000)) // 12h
				.subject(username)
				.claim("roles", roles)
				.build();
		return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}
}