package uz.jl.mappers.quiz;

import uz.jl.dto.GenericDto;
import uz.jl.dto.quiz.QuizCreateDto;
import uz.jl.dto.quiz.QuizUpdateDto;
import uz.jl.entity.quiz.Quiz;
import uz.jl.mappers.GenericBaseMapper;
import uz.jl.mappers.GenericMapper;

/**
 * @author D4uranbek ср. 20:48. 26.01.2022
 */
public class QuizMapper implements GenericMapper<Quiz, GenericDto, QuizCreateDto, QuizUpdateDto> {

    @Override
    public Quiz fromDto(GenericDto dto) {
        return null;
    }

    @Override
    public Quiz fromCreateDto(QuizCreateDto dto) {
        return null;
    }

    @Override
    public Quiz fromUpdateDto(QuizUpdateDto dto) {
        return null;
    }

    @Override
    public GenericDto toDto(Quiz model) {
        return null;
    }

    @Override
    public QuizCreateDto toCreateDto(Quiz model) {
        return null;
    }

    @Override
    public QuizUpdateDto toUpdateDto(Quiz model) {
        return null;
    }
}
