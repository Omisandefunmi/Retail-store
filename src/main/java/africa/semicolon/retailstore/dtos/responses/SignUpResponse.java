package africa.semicolon.retailstore.dtos.responses;

import africa.semicolon.retailstore.data.models.Cart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpResponse {
    private String firstName;
    private String LastName;
    private String email;
    private String address;
    private Cart cart;

}
