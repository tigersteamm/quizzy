package uz.jl.mappers.questionMapper;

import uz.jl.dto.GenericDto;
import uz.jl.dto.question.QuestionCreateDto;
import uz.jl.dto.question.QuestionUpdateDto;
import uz.jl.entity.quiz.Question;
import uz.jl.enums.Language.Language;
import uz.jl.enums.Level;
import uz.jl.enums.Subject;
import uz.jl.mappers.GenericMapper;

public class QuestionMapper implements GenericMapper<Question, GenericDto, QuestionCreateDto, QuestionUpdateDto> {

    @Override
    public Question fromDto(GenericDto dto) {
        return null;
    }

    @Override
    public Question fromCreateDto(QuestionCreateDto dto) {
        return Question.childBuilder()
                .title(dto.getTitle())
                .level(Level.valueOf(dto.getLevel()))
                .language(Language.valueOf(dto.getLanguage()))
                .subject(Subject.valueOf(dto.getSubject()))
                .variants(dto.getVariants())
                .build();
    }

    @Override
    public Question fromUpdateDto(QuestionUpdateDto dto) {
        return null;
    }

    @Override
    public GenericDto toDto(Question model) {
        return null;
    }

    @Override
    public QuestionCreateDto toCreateDto(Question model) {
        return null;
    }

    @Override
    public QuestionUpdateDto toUpdateDto(Question model) {
        return null;
    }
}
