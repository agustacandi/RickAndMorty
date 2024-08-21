package com.takehomechallenge.candi.presentation.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.takehomechallenge.core.data.RemoteResponse
import com.takehomechallenge.core.domain.character.usecase.CharacterUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import com.takehomechallenge.core.domain.character.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var characterUseCase: CharacterUseCase

    @Mock
    private lateinit var observer: Observer<RemoteResponse<List<Character>>>

    private lateinit var searchViewModel: SearchViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        searchViewModel = SearchViewModel(characterUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
    }

    @Test
    fun `getCharacterByName should return a list of characters`() = runTest {
        // Given
        val characters = listOf(Character(id = 1, name = "Rick Sanchez"))
        val expectedResponse = RemoteResponse.Success(characters)
        `when`(characterUseCase.getCharacterByName("Rick")).thenReturn(flow {
            emit(expectedResponse)
        })

        // When
        searchViewModel.searchResult.observeForever(observer)
        searchViewModel.getCharacterByName("Rick")

        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        verify(observer).onChanged(expectedResponse)
        assertEquals(expectedResponse, searchViewModel.searchResult.value)
    }

    @Test
    fun `getCharacterByName should return an error`() = runTest {
        // Given
        val expectedResponse = RemoteResponse.Error("Network Error")
        `when`(characterUseCase.getCharacterByName("Rick")).thenReturn(flow {
            emit(expectedResponse)
        })

        // When
        searchViewModel.searchResult.observeForever(observer)
        searchViewModel.getCharacterByName("Rick")

        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        verify(observer).onChanged(expectedResponse)
        assertEquals(expectedResponse, searchViewModel.searchResult.value)
    }
}