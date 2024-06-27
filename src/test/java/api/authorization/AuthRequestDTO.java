package api.authorization;

import lombok.Data;

@Data
public class AuthRequestDTO {
    String userName, password;
}
