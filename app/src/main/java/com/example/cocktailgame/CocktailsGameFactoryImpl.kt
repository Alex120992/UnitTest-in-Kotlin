package com.example.cocktailgame


class CocktailsGameFactoryImpl(private val repository: CocktailsRepository) : CocktailsGameFactory {
    override fun buildGame(callback: CocktailsGameFactory.CallBack) {
        repository.getAlcoholic(object : RepositoryCallBackCocktails<List<Cocktails>,String>{
            override fun onSuccess(cocktailList: List<Cocktails>) {
                callback.onSuccess(Game(emptyList()))
            }

            override fun onError(e: String) {
                callback.onError()
            }

        })
    }



}