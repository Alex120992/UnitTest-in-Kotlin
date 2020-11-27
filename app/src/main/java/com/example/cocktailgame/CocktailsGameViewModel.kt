package com.example.cocktailgame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CocktailsGameViewModel
    (private val repository: CocktailsRepository, private val factory: CocktailsGameFactory) :
    ViewModel() {
    private val loadingLiveData = MutableLiveData<Boolean>()
    private val errorLiveData = MutableLiveData<Boolean>()
    private val questionLiveData = MutableLiveData<Question>()
    private val scoreLiveData = MutableLiveData<Score>()

    fun getLoading ():LiveData<Boolean> = loadingLiveData
    fun getError ():LiveData<Boolean> = errorLiveData
    fun getQuestion ():LiveData<Question> = questionLiveData
    fun getScore ():LiveData<Score> = scoreLiveData



}