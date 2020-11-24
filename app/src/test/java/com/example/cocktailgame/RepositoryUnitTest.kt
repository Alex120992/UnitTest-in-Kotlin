package com.example.cocktailgame

import android.content.SharedPreferences
import com.nhaarman.mockitokotlin2.*
import org.junit.Test

class RepositoryUnitTest {
    @Test
    fun saveScore_shouldSaveToSharePreferences (){
        val api: CocktailsApi = mock()

        val sharePreferenceEditor: SharedPreferences.Editor = mock()
        val sharedPreferences:SharedPreferences = mock()
        whenever(sharedPreferences.edit()).thenReturn(sharePreferenceEditor)

        val repository = CocktailsRepositoryImpl (api,sharedPreferences)

        val score = 100
        repository.saveHighScore(10)
        inOrder (sharePreferenceEditor){
            verify(sharePreferenceEditor).putInt(any(), eq(score))
            verify(sharePreferenceEditor).apply()
        }
    }
}