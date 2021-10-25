package com.haslett.food2forkkmm.presentation.recipe_list

// A sealed class is similar to an Enum class, but with extra properties
sealed class RecipeListEvents() {
    object LoadRecipes: RecipeListEvents()
    
    object NextPage: RecipeListEvents()
    
    object NewSearch: RecipeListEvents()
    
    data class OnUpdateQuery(val query: String): RecipeListEvents()
}
