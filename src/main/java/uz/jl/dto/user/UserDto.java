package uz.jl.dto.user;

import lombok.Builder;
import uz.jl.dto.GenericDto;
import uz.jl.entity.quiz.Quiz;
import uz.jl.enums.Language.Language;
import uz.jl.enums.Role.Role;
import uz.jl.enums.Status.Status;

import java.util.List;

public class UserDto extends GenericDto {
    private String username;
    private String fullName;
    private String password;
    private Role role;
    private Status status;
    private Language language;
    private List<Quiz> quizzes;
    @Builder(builderMethodName = "childBuilder")
    public UserDto(String id, String username, String fullName, String password, Role role, Status status, Language language, List<Quiz> quizzes) {
        super(id);
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.role = role;
        this.status = status;
        this.language = language;
        this.quizzes = quizzes;
    }
}
