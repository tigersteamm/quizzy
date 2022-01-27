package uz.jl.dto.user;

import lombok.*;
import uz.jl.dto.GenericBaseDto;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class UserCreateDto implements GenericBaseDto {
    private String username;
    private String password;
    private String role;
    private String language;

    @Builder(builderMethodName = "childBuilder")
    public UserCreateDto(String username, String password, String role, String language) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.language = language;
    }
}
