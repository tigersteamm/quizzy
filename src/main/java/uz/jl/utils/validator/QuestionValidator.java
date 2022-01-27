package uz.jl.utils.validator;

import org.bson.types.ObjectId;
import uz.jl.dto.question.QuestionCreateDto;
import uz.jl.dto.question.QuestionUpdateDto;
import uz.jl.dto.quiz.QuizCreateDto;
import uz.jl.dto.quiz.QuizUpdateDto;
import uz.jl.entity.quiz.Question;
import uz.jl.utils.BaseUtils;

public class QuestionValidator extends BaseValidator<QuestionCreateDto, QuestionUpdateDto, ObjectId> {

    public QuestionValidator(BaseUtils utils) {super(utils);}

    @Override
    protected void validKey(ObjectId key) throws IllegalArgumentException {}

    @Override
    public void validOnCreate(QuestionCreateDto dto) throws IllegalArgumentException {}

    @Override
    protected void validOnUpdate(QuestionUpdateDto dto) throws IllegalArgumentException {}
}
