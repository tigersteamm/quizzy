package uz.jl.dto.quiz;

import lombok.*;
import uz.jl.dto.GenericBaseDto;
import uz.jl.entity.quiz.QuestionMark;

import java.util.List;

/**
 * @author D4uranbek ср. 19:28. 26.01.2022
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class QuizCreateDto implements GenericBaseDto {
    private String subject;
    private String level;
    private String language;
    private List<QuestionMark> questionsMarks;
    private boolean completed;
    /**
     * duration in seconds
     */
    private int duration;
    private int count;

    @Builder(builderMethodName = "childBuilder")
    public QuizCreateDto(String subject, String level, String language, List<QuestionMark> questionsMarks, boolean completed, int duration, int count) {
        this.subject = subject;
        this.level = level;
        this.language = language;
        this.questionsMarks = questionsMarks;
        this.completed = completed;
        this.duration = duration;
        this.count = count;
    }
}
