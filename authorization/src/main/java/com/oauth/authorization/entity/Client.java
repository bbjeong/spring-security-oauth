package com.oauth.authorization.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import javax.persistence.*;

@Getter
@ToString
@Entity
@NoArgsConstructor
public class Client extends RegisteredClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;


}
