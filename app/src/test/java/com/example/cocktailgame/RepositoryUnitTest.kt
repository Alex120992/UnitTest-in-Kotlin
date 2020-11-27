package com.example.cocktailgame

import android.content.SharedPreferences
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Test

class RepositoryUnitTest {
    private lateinit var repository: CocktailsRepositoryImpl
    private lateinit var api: CocktailsApi
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    @Before
    fun setup() {
        api = mock()
        sharedPreferences = mock()
        sharedPreferencesEditor = mock()
        whenever(sharedPreferences.edit()).thenReturn(sharedPreferencesEditor)
        repository = CocktailsRepositoryImpl(api, sharedPreferences)
    }

    @Test
    fun saveScore_shouldSaveToSharePreferences() {

        val score = 10
        repository.saveHighScore(10)
        inOrder(sharedPreferencesEditor) {
            // проверка что выполнился метод putInt со значение 10
            verify(sharedPreferencesEditor).putInt(any(), eq(score))
            // проверка метода сохранения изменений, что он вызывался
            verify(sharedPreferencesEditor).apply()
        }
    }

    @Test
    fun getScore_shouldGetScoreFromSharedPrefernces() {
        repository.getHighScore()
        verify(sharedPreferences).getInt(any(), any())

    }
    @Test
    fun saveScore_shouldNotSaveToSharedPreferencesIfLower(){
        val previouslySavedHighSvore = 100
        val newHighScore = 10

        val spyRepository = spy(repository)
        // Делаем возврат значения 100 всегда при вызове метода getHighScore()
        doReturn (previouslySavedHighSvore).whenever(spyRepository).getHighScore()

        spyRepository.saveHighScore(newHighScore)
            // проверка что объект заглушка не вызывался ни разу
        verify(sharedPreferencesEditor,never()).putInt(any(), eq(newHighScore))
    }
}