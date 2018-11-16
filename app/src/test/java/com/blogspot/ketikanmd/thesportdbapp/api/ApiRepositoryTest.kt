package com.blogspot.ketikanmd.thesportdbapp.api

import com.blogspot.ketikanmd.thesportdbapp.ApiRepository
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ApiRepositoryTest {
    @Test
    fun doRequest() {
        val apiRepository = mock(ApiRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/eventsprevleague.php?id=3128"
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }
}