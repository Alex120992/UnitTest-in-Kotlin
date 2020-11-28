package com.example.cocktailgame


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

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
        doAnswer {
            val callBack: CocktailsGameFactory.CallBack = it.getArgument(0)
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

    @Test
    fun init_shouldBuildGame() {
        viewModel.initGame()
        verify(factory).buildGame(any())
    }

    @Test
    fun init_shouldShowLoading(){
        viewModel.initGame()

        verify(loadingObserver).onChanged(eq(true))
    }
    @Test
    fun init_shouldHideErroe(){
        viewModel.initGame()

        verify(errorObserver).onChanged(eq(false))
    }
    @Test
    fun init_shouldShowError_whenFactoryReturnsError() {
        setUpFactoryWithError()
        viewModel.initGame()
        verify(errorObserver).onChanged(eq(true))
    }
    @Test
    fun init_shouldHideLoading_whenFactoryReturnsError() {
        setUpFactoryWithError()
        viewModel.initGame()
        verify(loadingObserver).onChanged(eq(false))
    }

    @Test
    fun init_shouldHideError_whenFactoryReturnsSuccess() {
        setUpFactorySuccessGame(game)
        viewModel.initGame()
        verify(errorObserver, times(2)).onChanged(eq(false))
    }

    @Test
    fun init_shouldHideLoading_whenFactoryReturnsSuccess() {
        setUpFactorySuccessGame(game)
        viewModel.initGame()
        verify(loadingObserver).onChanged(eq(false))
    }

    @Test
    fun init_shouldShowScore_whenFactoryReturnsSuccess() {
        val score = mock<Score>()
        whenever(game.score).thenReturn(score)
        setUpFactorySuccessGame(game)
        viewModel.initGame()
        verify(scoreObserver).onChanged(eq(score))
    }

    @Test
    fun init_shouldShowFirstQuestion_whenFactoryReturnsSuccess() {
        val question = mock<Question>()
        whenever(game.nextQuestion()).thenReturn(question)
        setUpFactorySuccessGame(game)
        viewModel.initGame()
        verify(questionObserver).onChanged(eq(question))
    }

    @Test
    fun nextQuestion_shouldShowQuestion(){
        val question1 = mock<Question>()
        val question2 = mock<Question>()
        whenever(game.nextQuestion()).thenReturn(question1).thenReturn(question2)
        setUpFactorySuccessGame(game)
        viewModel.initGame()
        viewModel.nextQuestion()
        verify(questionObserver).onChanged(eq(question2))

    }




}