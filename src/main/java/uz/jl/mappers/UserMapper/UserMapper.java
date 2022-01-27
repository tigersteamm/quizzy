package uz.jl.mappers.UserMapper;

import org.bson.types.ObjectId;
import uz.jl.dto.GenericDto;
import uz.jl.dto.user.UserCreateDto;
import uz.jl.dto.user.UserDto;
import uz.jl.dto.user.UserUpdateDto;
import uz.jl.entity.auth.User;
import uz.jl.enums.Language.Language;
import uz.jl.enums.Role.Role;
import uz.jl.enums.Status.Status;
import uz.jl.mappers.GenericMapper;

import java.util.ArrayList;

public class UserMapper implements GenericMapper<User, GenericDto, UserCreateDto, UserUpdateDto> {
    @Override
    public User fromDto(GenericDto dto) {
        return null;
    }

    @Override
    public User fromCreateDto(UserCreateDto dto) {
        return User.childBuilder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .role(Role.getByName(dto.getRole()))
                .status(Status.NO_ACTIVE)
                .language(Language.getByCode(dto.getLanguage()))
                .quizzes(new ArrayList<>())
                .currentQuiz(null)
                .build();
    }

    @Override
    public User fromUpdateDto(UserUpdateDto dto) {
        return User.childBuilder().id(new ObjectId(dto.getId()))
                .username(dto.getUsername())
                .fullName(dto.getFullName())
                .language(Language.getByCode(dto.getLanguage())).
                build();
    }

    @Override
    public UserDto toDto(User model) {
        return UserDto.childBuilder()
                .username(model.getUsername())
                .fullName(model.getFullName())
                .password(model.getPassword())
                .role(model.getRole())
                .language(model.getLanguage())
                .quizzes(model.getQuizzes())
                .status(model.getStatus())
                .build();
    }

    @Override
    public UserCreateDto toCreateDto(User model) {
        return UserCreateDto.childBuilder()
                .username(model.getUsername())
                .fullName(model.getFullName())
                .password(model.getPassword())
                .language(model.getLanguage().toString())
                .build();
    }

    @Override
    public UserUpdateDto toUpdateDto(User model) {

        return UserUpdateDto.childBuilder()
                .id(model.getId().toString())
                .username(model.getUsername())
                .fullName(model.getFullName())
                .language(model.getLanguage().toString())
                .build();
    }
}
