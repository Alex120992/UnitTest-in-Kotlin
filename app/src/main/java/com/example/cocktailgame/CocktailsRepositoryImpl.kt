package com.example.cocktailgame

import android.content.SharedPreferences

private const val HIGH_SCORE_KEY = "HIGH_SCORE_KEY"

class CocktailsRepositoryImpl(
    private val api: CocktailsApi,
    private val sharedPreferences: SharedPreferences
) : CocktailsRepository {

    override fun saveHighScore(score: Int) {
        val highScore = getHighScore()

        if (highScore < score) {
            val editor = sharedPreferences.edit()
            editor.putInt(HIGH_SCORE_KEY, score)
            editor.apply()
        }
    }

    override fun getHighScore(): Int = sharedPreferences.getInt(HIGH_SCORE_KEY, 0)
    override fun getAlcoholic(objects: Any) {

    }
}