package com.example.cocktailgame

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

class CocktailsGameFactoryUnitTests {
    private lateinit var repository: CocktailsRepository
    private lateinit var factory: CocktailsGameFactory

    @Before
    fun setup() {
        repository = mock()
        factory = CocktailsGameFactoryImpl(repository)
    }
    @Test
    fun buildGame_shouldGetCocktailsFromRepo() {
        factory.buildGame(mock())
        verify(repository).getAlcoholic (any())
    }
}
