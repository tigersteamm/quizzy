package uz.jl.dto.question;

import lombok.*;
import uz.jl.dto.GenericBaseDto;
import uz.jl.entity.quiz.Variant;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class QuestionCreateDto implements GenericBaseDto {
    private String title;
    private String level;
    private String language;
    private String subject;
    private List<Variant> variants;


    @Builder(builderMethodName = "childBuilder")
    public QuestionCreateDto(String title, String level, String language, String subject, List<Variant> variants) {
        this.title = title;
        this.level = level;
        this.language = language;
        this.subject = subject;
        this.variants = variants;
    }
}
