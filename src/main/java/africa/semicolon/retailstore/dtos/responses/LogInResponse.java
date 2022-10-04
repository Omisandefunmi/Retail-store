package africa.semicolon.retailstore.dtos.responses;

import africa.semicolon.retailstore.data.models.Cart;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class LogInResponse {
    private boolean isLogin;
    private String firstName;
    private String lastName;
    private String email;
    private Cart cart;
}
