package com.example.cocktailgame

import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Test

class CocktailsGameFactoryUnitTests {
    private lateinit var repository: CocktailsRepository
    private lateinit var factory: CocktailsGameFactory
    private val cocktails = listOf<Cocktails>(
        Cocktails("1", "Drink1", "image1"), Cocktails("2", "Drink2", "image2"),
        Cocktails("3", "Drink3", "image3"), Cocktails("4", "Drink4", "image4")
    )

    @Before
    fun setup() {
        repository = mock()
        factory = CocktailsGameFactoryImpl(repository)
    }

    @Test
    fun buildGame_shouldGetCocktailsFromRepo() {
        factory.buildGame(mock())
        verify(repository).getAlcoholic(any())
    }


    @Test
    fun buildGame_shouldCallOnSuccess() {
        val callback =
            mock<CocktailsGameFactory.CallBack>()
        setUpRepositoryWithCocktails(repository)
        factory.buildGame(callback)
        verify(callback).onSuccess(any())
    }

    private fun setUpRepositoryWithCocktails(repository: CocktailsRepository) {
        doAnswer {
            val callBack: RepositoryCallBackCocktails<List<Cocktails>, String> =
                it.getArgument(0)
            callBack.onSuccess(cocktails)
        }.whenever(repository).getAlcoholic(any())
    }

    @Test
    fun buildGame_shouldCallOnError() {

        val callback =
            mock<CocktailsGameFactory.CallBack>()
        setUpRepositoryWithError(repository)
        factory.buildGame(callback)
        // проверка что метод он Error вызывалс
        verify(callback).onError()

    }
    private fun setUpRepositoryWithError(repository: CocktailsRepository) {
         //  замыкание callback.onError
        doAnswer {
            val callBack: RepositoryCallBackCocktails<List<Cocktails>, String> =
                it.getArgument(0)
            callBack.onError("Error")
        }.whenever(repository).getAlcoholic(any())
    }
}
