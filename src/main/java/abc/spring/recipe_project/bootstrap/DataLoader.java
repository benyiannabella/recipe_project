package abc.spring.recipe_project.bootstrap;

import abc.spring.recipe_project.domain.*;
import abc.spring.recipe_project.repositories.CategoryRepository;
import abc.spring.recipe_project.repositories.RecipeRepository;
import abc.spring.recipe_project.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeRepository recipeRepository;

    public DataLoader(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository,
                      RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipeList());
    }

    private List<Recipe> getRecipeList(){
        List<Recipe> recipes = new ArrayList<>();
        Recipe guacamole = new Recipe();

        UnitOfMeasure each = new UnitOfMeasure();
        each.setDescription("Each");
        unitOfMeasureRepository.save(each);
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        UnitOfMeasure teaSpoon = new UnitOfMeasure();
        teaSpoon.setDescription("Teaspoon");
        unitOfMeasureRepository.save(teaSpoon);
        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if(!teaSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        UnitOfMeasure tableSpoon = new UnitOfMeasure();
        tableSpoon.setDescription("Tablespoon");
        unitOfMeasureRepository.save(tableSpoon);
        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if(!tableSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }
        UnitOfMeasure dash = new UnitOfMeasure();
        dash.setDescription("Dash");
        unitOfMeasureRepository.save(dash);
        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
        if(!dashUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();

        Category category1 = new Category();
        category1.setDescription("American");
        categoryRepository.save(category1);
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if(!americanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }
        Category category2 = new Category();
        category2.setDescription("Mexican");
        categoryRepository.save(category2);
        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("American");
        if(!mexicanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        guacamole.getCategories().add(category1);
        guacamole.getCategories().add(category2);
        guacamole.setDescription("Perfect Guacamole");
        guacamole.setPrepTime(10);
        guacamole.setCookTime(0);
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setDirections("1. Cut the avocado:\n" +
                "Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. " +
                "(See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
        "2. Mash the avocado flesh:\n" +
                "Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "3. Add remaining ingredients to taste:\n" +
                "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4. Serve immediately:\n" +
                "If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.)\n" +
                "\n" +
                "Garnish with slices of red radish or jigama strips. Serve with your choice of store-bought tortilla chips or make your own homemade tortilla chips.\n" +
                "\n" +
                "Refrigerate leftover guacamole up to 3 days.\n" +
                "\n" +
                "Note: Chilling tomatoes hurts their flavor. So, if you want to add chopped tomato to your guacamole, add it just before serving");

        Notes guacamoleNotes = new Notes();
        guacamoleNotes.setRecipeNotes("Be careful handling chilis! If using, it's best to wear food-safe gloves. If no gloves are available, wash your hands thoroughly after handling, and do not touch your eyes or the area near your eyes for several hours afterwards.");

        guacamole.setNotes(guacamoleNotes);

        guacamole.getIngredients().add(new Ingredient("ripe avocado", new BigDecimal(2), eachUom));
        guacamole.getIngredients().add(new Ingredient("salt", new BigDecimal(5), teaSpoonUom));
        guacamole.getIngredients().add(new Ingredient("fresh lime juice", new BigDecimal(2), tableSpoonUom));
        guacamole.getIngredients().add(new Ingredient("minced red onion", new BigDecimal(2), tableSpoonUom));
        guacamole.getIngredients().add(new Ingredient("serrano chiles", new BigDecimal(2), eachUom));
        guacamole.getIngredients().add(new Ingredient("black pepper", new BigDecimal(2), dashUom));
        guacamole.getIngredients().add(new Ingredient("cilantro", new BigDecimal(2), tableSpoonUom));
        guacamole.getIngredients().add(new Ingredient("ripe tomato", new BigDecimal(".5"), eachUom));

        recipes.add(guacamole);

        log.debug("logging");
        return recipes;
    }


}
