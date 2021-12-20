package abc.spring.recipe_project.services;

import abc.spring.recipe_project.domain.Recipe;
import abc.spring.recipe_project.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
       Set<Recipe> recipes = new HashSet<>();
       recipeRepository.findAll().iterator().forEachRemaining(recipes :: add);
       return recipes;
    }
}
