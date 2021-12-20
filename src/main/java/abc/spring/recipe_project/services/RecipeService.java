package abc.spring.recipe_project.services;

import abc.spring.recipe_project.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();

}
