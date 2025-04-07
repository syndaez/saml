package org.example.heimdallsaml;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.saml2.provider.service.registration.InMemoryRelyingPartyRegistrationRepository;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistration;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistrationRepository;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistrations;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public RelyingPartyRegistrationRepository relyingPartyRegistrationRepository() {
        RelyingPartyRegistration registration = RelyingPartyRegistrations
                .fromMetadataLocation("https://portal.sso.eu-central-1.amazonaws.com/saml/metadata/NzY2NjE3MzM0MzIwX2lucy03MjBmN2E0MDdkYTVjY2Y4")
                .registrationId("aws-sso")
                .entityId("http://test-az.heimdalldata.com:8080/saml")
                .assertionConsumerServiceLocation("http://test-az.heimdalldata.com:8080/saml2/acs")
                .build();

        return new InMemoryRelyingPartyRegistrationRepository(registration);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz.anyRequest().authenticated())
                .saml2Login(saml2 -> saml2.loginProcessingUrl("/saml2/acs")
                        .defaultSuccessUrl("/saml2/success", true));
        return http.build();
    }
}
