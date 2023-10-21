package com.mihirjoshi.javaassignment.serivces.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter @Setter @NoArgsConstructor
@Component
public class AuthRequest {
    private String login_id;
    private String password;

    public AuthRequest(String login_id, String password) {
        this.login_id = login_id;
        this.password = password;
    }
}
