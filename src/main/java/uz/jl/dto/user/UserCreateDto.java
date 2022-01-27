package uz.jl.dto.user;

import lombok.*;
import uz.jl.dto.GenericBaseDto;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class UserCreateDto implements GenericBaseDto {
    private String username;
    private String fullName;
    private String password;
    private String role;
    private String language;

    @Builder(builderMethodName = "childBuilder")
    public UserCreateDto(String username, String fullName, String password, String language) {
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.language = language;
    }
}
