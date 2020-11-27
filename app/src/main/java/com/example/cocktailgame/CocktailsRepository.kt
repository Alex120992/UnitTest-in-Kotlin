package com.example.cocktailgame

interface CocktailsRepository {
    fun saveHighScore(score:Int)
    fun getHighScore ():Int
    fun getAlcoholic (objects: Any)
}