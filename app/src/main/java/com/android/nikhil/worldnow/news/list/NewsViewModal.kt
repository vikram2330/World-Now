package com.android.nikhil.worldnow.news.list

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.android.nikhil.worldnow.base.BaseViewModel
import com.android.nikhil.worldnow.model.Result
import com.android.nikhil.worldnow.repository.NewsRepository
import javax.inject.Inject

class NewsViewModal @Inject constructor() : BaseViewModel() {

  private var error = MutableLiveData<Boolean>()
  @Inject lateinit var newsRepository: NewsRepository
  private var newsLiveData = MutableLiveData<List<Result>>()

  fun getNews() {
    newsLiveData.postValue(newsRepository.getNews())
  }

  fun getNewsData() = newsLiveData

  fun getErrorLiveData() = error

  fun observeRepository() {
    newsLiveData = Transformations.switchMap(newsRepository.newsListLiveData) { resultsList ->
      val data = MutableLiveData<List<Result>>()
      data.value = resultsList
      data
    } as MutableLiveData<List<Result>>
  }
}