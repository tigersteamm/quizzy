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

import java.util.*;

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
        String id = Input.getStr("QuestionId : ");
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


    private String randomNumber() {
        return String.valueOf(new Random().nextInt(99999999));
    }

    public void createRandomQuestion() {
        try {
            QuestionCreateDto dto = QuestionCreateDto.childBuilder()
                    .title(randomNumber())
                    .level("HARD")
                    .language("RU")
                    .subject("ENGLISH")
                    .variants(createRandomVariants())
                    .build();
            ResponseEntity<Data<ObjectId>> response = questionService.create(dto);
            Print.println(Color.GREEN, response.getData().getBody());
        } catch (ApiRuntimeException e) {
            showResponse(e.getMessage());
        }
    }

    private List<Variant> createRandomVariants() {
        List<Variant> variants = new ArrayList<>();
        Variant variant1 = new Variant(new ObjectId(), new Date(), false, "1", true);
        Variant variant2 = new Variant(new ObjectId(), new Date(), false, "2", false);
        Variant variant3 = new Variant(new ObjectId(), new Date(), false, "3", false);
        Variant variant4 = new Variant(new ObjectId(), new Date(), false, "4", false);
        variants.add(variant1);
        variants.add(variant2);
        variants.add(variant3);
        variants.add(variant4);
        return variants;
    }

    public void random100() {
        for (int i = 0; i < 100; i++) {
            createRandomQuestion();
        }
    }


    private <T> void showResponse(String color, T response) {
        Print.println(color, response);
    }

    private <T> void showResponse(T response) {
        showResponse(Color.RED, response);
    }
}
