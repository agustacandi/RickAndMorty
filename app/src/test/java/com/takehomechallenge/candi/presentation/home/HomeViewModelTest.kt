package com.takehomechallenge.candi.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.takehomechallenge.core.data.RemoteResponse
import com.takehomechallenge.core.domain.character.usecase.CharacterUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.mockito.kotlin.mock
import com.takehomechallenge.core.domain.character.model.Character
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var characterUseCase: CharacterUseCase
    private lateinit var viewModel: HomeViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        characterUseCase = mock {
            onBlocking { getAllCharacters() } doReturn flow {
                emit(RemoteResponse.Success(emptyList())) // Start with an empty list
            }
        }
        viewModel = HomeViewModel(characterUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset the main dispatcher to the original Main dispatcher
    }

    @Test
    fun `getCharacters should return a list of characters`() = runTest {
        // Given
        val characters = listOf(Character(id = 1, name = "John Doe"))
        val flow: Flow<RemoteResponse<List<Character>>> = flow {
            emit(RemoteResponse.Success(characters))
        }
        whenever(characterUseCase.getAllCharacters()).thenReturn(flow)

        // When
        viewModel = HomeViewModel(characterUseCase)

        val observer = mock<Observer<RemoteResponse<List<Character>>>>()
        viewModel.characters.observeForever(observer)

        advanceUntilIdle() // Ensure all coroutines have completed

        // Then
        verify(observer).onChanged(RemoteResponse.Success(characters))
        assertEquals(RemoteResponse.Success(characters), viewModel.characters.value)

        viewModel.characters.removeObserver(observer)
    }

    @Test
    fun `getCharacters should return an error when request fails`() = runTest {
        // Given
        val errorResponse = RemoteResponse.Error("Network Error")
        val flow: Flow<RemoteResponse<List<Character>>> = flow {
            emit(errorResponse)
        }
        whenever(characterUseCase.getAllCharacters()).thenReturn(flow)

        // When
        viewModel = HomeViewModel(characterUseCase)

        val observer = mock<Observer<RemoteResponse<List<Character>>>>()
        viewModel.characters.observeForever(observer)

        advanceUntilIdle() // Ensure all coroutines have completed

        // Then
        verify(observer).onChanged(errorResponse)
        assertEquals(errorResponse, viewModel.characters.value)

        viewModel.characters.removeObserver(observer)
    }
}