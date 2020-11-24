
package com.example.cocktailgame

import com.nhaarman.mockitokotlin2.*
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock


class GameUnitTest {


    @Test
    fun whenIncrementScore_shouldIncrementCurrentScore() {
        val  score = Score(0)
        score.increment()
        Assert.assertEquals("Current score should have been 1", 1, score.current)
    }

    @Test
    fun whenIncrementScore_aboveHighScore_shouldAlsoIcrementHighScore() {
        val  score = Score(0)
        score.increment()
        Assert.assertEquals(1, score.highest)
    }

    @Test
    fun wgenIncrementingScore_belowHighScore_shouldNotHighScore() {
        val  score = Score(10)
        score.increment()
        Assert.assertEquals(10, score.highest)
    }

    @Test
    fun whenAnswering_shouldDelegateToQuestion() {
        val question = mock<Question>()
        val game = Game(listOf(question))

        game.answer(question, "OPTION")
// проверяем что нужный аргумент в методе
        verify(question, times(1)).answer(eq("OPTION"))
    }

    //    Тут мы ставим заглушку на замоканный объект и проверяем правильность значения
//    возвращаемого                         значения
//    @Test
//    fun whenAnsweringCorrectly_shouldIncrementCurrentScore() {
//        val question = mock<Question>()
//
//        whenever(question.answer(anyString())).thenReturn(true)
//
//        val game = Game(listOf(question))
//
//        game.answer(question, "OPTION")
//        Assert.assertEquals(1, game.currentScore)
//    }
//
//    @Test
//    fun whenAnsweringIncorrectly_shouldINotIncrementCurrentScore() {
//        val question = mock<Question>()
//        // когда бы то ни был метод question с любым параметром String
////        он всегда возвращает false - т.е. мы ставим заглушку экзмепляра Question на метод
////        answer
//        whenever(question.answer(anyString())).thenReturn(false)
//
//        val game = Game(listOf(question))
//
//        game.answer(question, "OPTION")
//        Assert.assertEquals(0, game.currentScore)
//    }
    @Test
    fun whenAnsweringCorrectly_shouldIncrementCurrentScore() {
        val question = mock<Question>() // можно так мокать
        whenever(question.answer(anyString())).thenReturn(true)
        val score:Score = mock() // можно так мокать
        val game = Game(listOf(question), score)

        game.answer(question, "OPTION")
        // проверка того, что нужный метод был вызван
        verify(score).increment()
    }

    @Test
    fun whenAnsweringIncorrectly_shouldINotIncrementCurrentScore() {
        val question = mock<Question>()

        whenever(question.answer(anyString())).thenReturn(false)
        val score = mock<Score>()
        val game = Game(listOf(question), score)

        game.answer(question, "OPTION")
        // проверка что метод не вызывался
        verify(score, never()).increment()
    }

}