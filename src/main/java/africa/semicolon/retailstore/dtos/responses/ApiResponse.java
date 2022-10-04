package africa.semicolon.retailstore.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class ApiResponse {
    private int statusCode;
    private String message;
    private boolean successful;
}
