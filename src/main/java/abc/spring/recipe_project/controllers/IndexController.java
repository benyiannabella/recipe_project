package abc.spring.recipe_project.controllers;

import abc.spring.recipe_project.domain.Category;
import abc.spring.recipe_project.domain.UnitOfMeasure;
import abc.spring.recipe_project.repositories.CategoryRepository;
import abc.spring.recipe_project.repositories.RecipeRepository;
import abc.spring.recipe_project.repositories.UnitOfMeasureRepository;
import abc.spring.recipe_project.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/","/index", "/index.html"})
    public String getIndexPage(Model model){
        log.debug("In the index controller");
        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }


}
