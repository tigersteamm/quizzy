package uz.jl.dto.user;

import lombok.*;
import uz.jl.dto.GenericDto;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto extends GenericDto {
    private String username;
    private String fullName;
    private String language;

    @Builder(builderMethodName = "childBuilder")
    public UserUpdateDto(String id, String username, String fullName, String language) {
        super(id);
        this.username = username;
        this.fullName = fullName;
        this.language = language;
    }
}
