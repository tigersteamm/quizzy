package uz.jl.entity.quiz;

import lombok.*;
import org.bson.types.ObjectId;
import uz.jl.entity.Auditable;
import uz.jl.enums.Language.Language;

import java.util.Date;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Quiz extends Auditable {
    private String subject;
    private String level;
    private Language language;
    private List<QuestionMark> questionsMarks;
    private boolean completed;
    /**
     * duration in seconds
     */
    private int duration;

    @Builder(builderMethodName = "childBuilder")
    public Quiz(ObjectId id, Date createdAt, boolean deleted, String subject, String level, Language language, List<QuestionMark> questionsMarks, boolean completed, int duration) {
        super(id, createdAt, deleted);
        this.subject = subject;
        this.level = level;
        this.language = language;
        this.questionsMarks = questionsMarks;
        this.completed = completed;
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Quiz quiz = (Quiz) o;
        return completed == quiz.completed && duration == quiz.duration && subject.equals(quiz.subject) && level.equals(quiz.level) && language == quiz.language && questionsMarks.equals(quiz.questionsMarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subject, level, language, questionsMarks, completed, duration);
    }
}
