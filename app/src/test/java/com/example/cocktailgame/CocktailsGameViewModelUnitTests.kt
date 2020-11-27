package com.example.cocktailgame

import android.arch.core.executor.TaskExecutor
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Rule

class CocktailsGameViewModelUnitTests {
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()
    private lateinit var repository: CocktailsRepository
    private lateinit var factory: CocktailsGameFactory
    private lateinit var viewModel: CocktailsGameViewModel
    private lateinit var game: Game
    private lateinit var loadingObserver: Observer<Boolean>
    private lateinit var errorObserver: Observer<Boolean>
    private lateinit var scoreObserver: Observer<Score>
    private lateinit var questionObserver: Observer<Question>

    @Before
    fun setup() {

        repository = mock()
        factory = mock()
        viewModel = CocktailsGameViewModel(repository, factory)

        game = mock()


        loadingObserver = mock()
        errorObserver = mock()
        scoreObserver = mock()
        questionObserver = mock()
        viewModel.getLoading().observeForever(loadingObserver)
        viewModel.getScore().observeForever(scoreObserver)
        viewModel.getQuestion().observeForever(questionObserver)
        viewModel.getError().observeForever(errorObserver)
    }

    private fun setUpFactorySuccessGame(game: Game) {
        doAnswer { val callBack: CocktailsGameFactory.CallBack = it.getArgument(0)
        callBack.onSuccess(game)
        }.whenever(factory).buildGame(any())
    }
    private fun setUpFactoryWithError() {
        doAnswer {
            val callback: CocktailsGameFactory.CallBack =
                it.getArgument(0)
            callback.onError()
        }.whenever(factory).buildGame(any())
    }
}