package com.example.cocktailgame

interface CocktailsGameFactory  {
    fun buildGame(callback: CocktailsGameFactory.ReposiryCallBackCocktails<Any?, Any?>)

    interface ReposiryCallBackCocktails<T, U> {
        fun onSuccess (cocktailList:T)
        fun onError(e: U)
    }
}