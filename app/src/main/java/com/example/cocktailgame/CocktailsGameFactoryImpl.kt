package com.example.cocktailgame


class CocktailsGameFactoryImpl(private val repository: CocktailsRepository) : CocktailsGameFactory {
    override fun buildGame(callback: CocktailsGameFactory.ReposiryCallBackCocktails<Any?, Any?>) {
        repository.getAlcoholic(object :
            CocktailsGameFactory.ReposiryCallBackCocktails<List<Cocktails>, String> {

            override fun onSuccess(cocktailList: List<Cocktails>) {
                TODO("Not yet implemented")
            }

            override fun onError(e: String) {
                TODO("Not yet implemented")
            }

        })
    }


}