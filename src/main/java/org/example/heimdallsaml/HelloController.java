package org.example.heimdallsaml;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.saml2.provider.service.authentication.Saml2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/saml2/success")
    public String home(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal) {
        return principal.getName();
//        model.addAttribute("emailAddress", principal.getFirstAttribute("email"));
//        model.addAttribute("userAttributes", principal.getAttributes());
//        return "t";
    }

}
