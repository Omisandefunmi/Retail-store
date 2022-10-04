package africa.semicolon.retailstore.dtos.requests;

import lombok.Getter;

@Getter
public class LogInRequest {
    private String email;
    private String password;
}
