package com.blogspot.ketikanmd.thesportdbapp.team

import com.blogspot.ketikanmd.thesportdbapp.ApiRepository
import com.blogspot.ketikanmd.thesportdbapp.TeamModels
import com.blogspot.ketikanmd.thesportdbapp.TeamResponse
import com.blogspot.ketikanmd.thesportdbapp.TheSportDBApi
import com.blogspot.ketikanmd.thesportdbapp.match.TeamPresenter
import com.blogspot.ketikanmd.thesportdbapp.match.TeamView
import com.blogspot.ketikanmd.thesportdbapp.utils.TestContextProvider
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class TeamPresenterTest {
    private lateinit var presenter: TeamPresenter
    @Mock
    private lateinit var view: TeamView
    @Mock
    private lateinit var gson: Gson
    @Mock
    private lateinit var apirepository: ApiRepository
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter= TeamPresenter(view,apirepository,gson,TestContextProvider())
    }

    @Test
    fun getListTeams() {
        val teams: MutableList<TeamModels> = mutableListOf()
        val response = TeamResponse(teams)
        val league = "English%20Premier%20League"

        `when`(gson.fromJson(apirepository
                .doRequest(TheSportDBApi.getListTeam(league)),
                TeamResponse::class.java
        )).thenReturn(response)

        presenter.getListTeams(league)

        verify(view).showLoading()
        verify(view).showTeam(teams)
        verify(view).hideLoading()


    }
}