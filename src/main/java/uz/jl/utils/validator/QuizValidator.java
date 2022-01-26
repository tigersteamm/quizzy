package uz.jl.utils.validator;

import org.bson.types.ObjectId;
import uz.jl.dto.quiz.QuizCreateDto;
import uz.jl.dto.quiz.QuizUpdateDto;
import uz.jl.enums.HttpStatus;
import uz.jl.exceptions.InvalidInputException;
import uz.jl.utils.BaseUtils;

import java.util.Objects;

/**
 * @author D4uranbek ср. 19:23. 26.01.2022
 */
public class QuizValidator extends BaseValidator<QuizCreateDto, QuizUpdateDto, ObjectId> {

    public QuizValidator(BaseUtils utils) {
        super(utils);
    }

    @Override
    protected void validKey(ObjectId key) throws IllegalArgumentException {
        if (Objects.isNull(key)) throw new InvalidInputException("ID_IS_NULL", HttpStatus.HTTP_400);
    }

    @Override
    public void validOnCreate(QuizCreateDto dto) throws IllegalArgumentException {

    }

    @Override
    protected void validOnUpdate(QuizUpdateDto dto) throws IllegalArgumentException {

    }
}
