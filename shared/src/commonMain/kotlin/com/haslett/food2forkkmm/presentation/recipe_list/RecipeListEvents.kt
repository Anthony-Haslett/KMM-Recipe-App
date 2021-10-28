package com.haslett.food2forkkmm.presentation.recipe_list

// A sealed class is similar to an Enum class, but with extra properties
sealed class RecipeListEvents {
    
    object LoadRecipes: RecipeListEvents()
    
    object NewSearch: RecipeListEvents()
    
    object NextPage: RecipeListEvents()
    
    data class OnUpdateQuery(val query: String): RecipeListEvents()
    
    data class OnSelectCategory(val category: FoodCategory): RecipeListEvents()
    
    object OnRemoveHeadMessageFromQueue: RecipeListEvents()
    
}
