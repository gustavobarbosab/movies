package io.github.gustavobarbosab.showcase.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.github.gustavobarbosab.core.domain.Result
import io.github.gustavobarbosab.showcase.domain.ShowCaseUseCase
import io.github.gustavobarbosab.showcase.domain.model.MovieShowCase
import io.github.gustavobarbosab.testutilities.coroutines.CoroutineDispatcherRule
import io.github.gustavobarbosab.testutilities.verifyNever
import io.github.gustavobarbosab.testutilities.verifyOnce
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

class ShowCaseViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val dispatcherRule = CoroutineDispatcherRule()

    private val useCase = mockk<ShowCaseUseCase>()
    private val stateObserver: Observer<ShowCaseViewState.Action> = mockk(relaxed = true)
    private val listObserver: Observer<List<MovieShowCase>> = mockk(relaxed = true)
    private val viewModel = ShowCaseViewModel(useCase)

    @Test
    fun `when I can get latest movies and had success`() = dispatcherRule.runBlockingTest {
        // GIVEN
        val expectedResult = Result.Success(emptyList<MovieShowCase>())
        coEvery { useCase.getLatestMovies() } returns expectedResult
        viewModel.state.action.observeForever(stateObserver)
        viewModel.state.latestMovies.observeForever(listObserver)

        // WHEN
        viewModel.getLatestMovies()

        // THEN
        verifyOnce {
            stateObserver.onChanged(ShowCaseViewState.Action.ShowLatestLoading)
            listObserver.onChanged(expectedResult.data)
        }
    }

    @Test
    fun `when I can get latest movies and had error`() = dispatcherRule.runBlockingTest {
        // GIVEN
        val expectedResult = Result.Error(Exception())
        coEvery { useCase.getLatestMovies() } returns expectedResult
        viewModel.state.action.observeForever(stateObserver)
        viewModel.state.latestMovies.observeForever(listObserver)

        // WHEN
        viewModel.getLatestMovies()

        // THEN
        verifyOnce {
            stateObserver.onChanged(ShowCaseViewState.Action.ShowLatestLoading)
            stateObserver.onChanged(ShowCaseViewState.Action.ErrorLoadLatest)
        }

        verifyNever {
            listObserver.onChanged(any())
        }
    }

    @Test
    fun `when I can get top rated movies and had success`() = dispatcherRule.runBlockingTest {
        // GIVEN
        val expectedResult = Result.Success(emptyList<MovieShowCase>())
        coEvery { useCase.getTopRatedMovies() } returns expectedResult
        viewModel.state.action.observeForever(stateObserver)
        viewModel.state.topRatedMovies.observeForever(listObserver)

        // WHEN
        viewModel.getTopRatedMovies()

        // THEN
        verifyOnce {
            stateObserver.onChanged(ShowCaseViewState.Action.ShowTopRatedLoading)
            listObserver.onChanged(expectedResult.data)
        }
    }

    @Test
    fun `when I can get top rated movies and had error`() = dispatcherRule.runBlockingTest {
        // GIVEN
        val expectedResult = Result.Error(Exception())
        coEvery { useCase.getTopRatedMovies() } returns expectedResult
        viewModel.state.action.observeForever(stateObserver)
        viewModel.state.topRatedMovies.observeForever(listObserver)

        // WHEN
        viewModel.getTopRatedMovies()

        // THEN
        verifyOnce {
            stateObserver.onChanged(ShowCaseViewState.Action.ShowTopRatedLoading)
            stateObserver.onChanged(ShowCaseViewState.Action.ErrorLoadTopRated)
        }

        verifyNever {
            listObserver.onChanged(any())
        }
    }
}