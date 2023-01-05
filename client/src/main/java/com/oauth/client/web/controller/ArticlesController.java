package com.oauth.client.web.controller;


import com.oauth.client.resource.constants.ResourceConstants;
import com.oauth.client.web.constants.PathConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;


@RestController
@RequiredArgsConstructor
public class ArticlesController {

    @Value("${resource-server.url}")
    private String RESOURCE_SERVER_URL;

    private final WebClient webClient;

    @GetMapping(value = PathConstants.ARTICLES)
    public String[] getArticles(
            @RegisteredOAuth2AuthorizedClient("articles-client-authorization-code") OAuth2AuthorizedClient authorizedClient
    ) {
        return webClient
                .get()
                .uri(RESOURCE_SERVER_URL + ResourceConstants.ARTICLES)
                .attributes(oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToMono(String[].class)
                .block();
    }

}