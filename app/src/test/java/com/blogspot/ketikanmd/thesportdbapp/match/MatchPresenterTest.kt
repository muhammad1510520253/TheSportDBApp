package com.blogspot.ketikanmd.thesportdbapp.match

import com.blogspot.ketikanmd.thesportdbapp.*
import com.blogspot.ketikanmd.thesportdbapp.response.MatchSearchResponse
import com.blogspot.ketikanmd.thesportdbapp.utils.TestContextProvider
import com.google.gson.Gson
import org.junit.Test
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MatchPresenterTest {
    private lateinit var presenter: MatchPresenter
    @Mock
    private lateinit var view: MatchView
    @Mock
    private lateinit var gson: Gson
    @Mock
    private lateinit var apirepository: ApiRepository
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter= MatchPresenter(view,apirepository,gson, TestContextProvider())
    }
    @Test
    fun getPastMatch() {
        val match: MutableList<MatchModels> = mutableListOf()
        val response = MatchResponse(match)
        val league = "3128"

        `when`(gson.fromJson(apirepository
                .doRequest(TheSportDBApi.getPastMatch(league)),
                MatchResponse::class.java
        )).thenReturn(response)
        presenter.getPastMatch(league)
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showMatchEvent(match)
        Mockito.verify(view).hideLoading()
    }

    @Test
    fun getNextMatch() {
        val match: MutableList<MatchModels> = mutableListOf()
        val response = MatchResponse(match)
        val league = "3128"

        `when`(gson.fromJson(apirepository
                .doRequest(TheSportDBApi.getNextMatch(league)),
                MatchResponse::class.java
        )).thenReturn(response)
        presenter.getNextMatch(league)
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showMatchEvent(match)
        Mockito.verify(view).hideLoading()
    }

    @Test
    fun getsearchMatch() {
        val match: MutableList<MatchModels> = mutableListOf()
        val response = MatchSearchResponse(match)
        val teamName = "Arsenal"

        `when`(gson.fromJson(apirepository
                .doRequest(TheSportDBApi.getSearchMatch(teamName)),
                MatchSearchResponse::class.java
        )).thenReturn(response)
        presenter.getsearchMatch(teamName)
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showMatchEvent(match)
        Mockito.verify(view).hideLoading()
    }
}