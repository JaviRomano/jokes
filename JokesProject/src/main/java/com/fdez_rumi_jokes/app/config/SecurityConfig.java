package com.fdez_rumi_jokes.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	/*@Autowired
	private CustomUserDetailsService customUserDetailsService;*/

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/api/auth/**").permitAll()
					.anyRequest().authenticated()
				/*	.requestMatchers("/api/users/create/**").hasRole("ADMIN")
					.requestMatchers("/api/users/update/**").hasRole("ADMIN")
					.requestMatchers("/api/users/delete/**").hasRole("ADMIN")*/	                
	            )
			.oauth2ResourceServer(oauth2 -> oauth2
				.jwt(jwt -> jwt
					.jwtAuthenticationConverter(jwtAuthenticationConverter())
				)
			);
		/*	.userDetailsService(customUserDetailsService)
			.httpBasic(Customizer.withDefaults()); */		
		return http.build();				
	}
	
	private JwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
		
		JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
		authoritiesConverter.setAuthorityPrefix("ROLE_");
        authoritiesConverter.setAuthoritiesClaimName("roles");
        
		converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);
		return converter;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

/*	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.builder().username("aaaaaa").password(passwordEncoder().encode("111111")).roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(user);
	}*/
}