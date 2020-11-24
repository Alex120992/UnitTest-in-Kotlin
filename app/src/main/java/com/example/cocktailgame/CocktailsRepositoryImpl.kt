package com.example.cocktailgame

import android.content.SharedPreferences

class CocktailsRepositoryImpl(
    private val api: CocktailsApi,
    private val sharedPreferences: SharedPreferences
) : CocktailsRepository {
    override fun saveHighScore(score: Int) {

    }
}