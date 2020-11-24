package com.example.cocktailgame

import org.junit.Assert
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import java.lang.IllegalArgumentException
import java.lang.RuntimeException

class QuestionUnitTest {
    private lateinit var question: Question
    @Before
    fun setup(){
        question = Question("CORRECT","INCORRECT")
    }
    @Test
    fun whenCreatingQuestion_shouldNotHaveAnseweredOption() {

        question.answer("INCORRECT")
        Assert.assertEquals("INCORRECT", question.answeredOption)
    }

    @Test
    fun whenAnswering_withCorrectOption_shouldReturnTrue() {

        val result = question.answer("CORRECT")
        Assert.assertTrue(result)
    }

    @Test
    fun whenAnswering_withIncorrectOption_shouldReturnFalse() {


        val reesult = question.answer("INCORRECT")

        Assert.assertFalse(reesult)
    }
    @Test(expected = IllegalArgumentException::class )
    fun whenAnswering_withIncorretOption_shouldThrowExeption(){

        question.answer("Invalid")
    }

}