package com.example.cocktailgame

interface CocktailsGameFactory  {
    fun buildGame(callback: CallBack)


    interface CallBack {
        fun onSuccess (game: Game)
        fun onError()
    }
}