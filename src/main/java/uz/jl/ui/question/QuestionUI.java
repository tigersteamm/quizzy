package uz.jl.ui.question;


import org.bson.types.ObjectId;
import uz.jl.dto.question.QuestionCreateDto;
import uz.jl.entity.quiz.Variant;
import uz.jl.exceptions.ApiRuntimeException;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;
import uz.jl.services.question.QuestionService;
import uz.jl.utils.Color;
import uz.jl.utils.Input;
import uz.jl.utils.Print;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class QuestionUI {
    private final QuestionService questionService;

    public QuestionUI(QuestionService questionService) {
        this.questionService = questionService;
    }

    public void questionCreate() {
        try {
            QuestionCreateDto dto = QuestionCreateDto.childBuilder()
                    .title(Input.getStr("title:"))
                    .level(Input.getStr("level:"))
                    .language(Input.getStr("language:"))
                    .subject(Input.getStr("subject:"))
                    .variants(createVariants())
                    .build();
            ResponseEntity<Data<ObjectId>> response = questionService.create(dto);
            Print.println(Color.GREEN, response.getData().getBody());
        } catch (ApiRuntimeException e) {
            showResponse(e.getMessage());
        }
    }

    public void questionDelete() {
        String id =Input.getStr("QuestionId : ");
        ObjectId id1 = new ObjectId(id);
        ResponseEntity<Data<Void>> response = questionService.delete(id1);
        Print.println(Color.GREEN, response.getData().getBody());
    }

    private List<Variant> createVariants() {
        List<Variant> variants = new ArrayList<>();
        String variantAnswer = "";
        boolean variantCorrect = false;
        while (true) {
            variantAnswer = Input.getStr("Enter variant: ");
            if (Objects.equals(variantAnswer, "stop")) break;
            variantCorrect = Boolean.parseBoolean(Input.getStr("Enter variant correct true|false:"));
            Variant variant = new Variant(new ObjectId(), new Date(), false, variantAnswer, variantCorrect);
            variants.add(variant);
        }
        return variants;
    }


    private <T> void showResponse(String color, T response) {
        Print.println(color, response);
    }

    private <T> void showResponse(T response) {
        showResponse(Color.RED, response);
    }
}
