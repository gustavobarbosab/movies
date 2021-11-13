package io.github.gustavobarbosab.movies.showcase.presentation

class ShowCaseViewModelTest {
//
//    @get:Rule
//    val rule = InstantTaskExecutorRule()
//
//    @get:Rule
//    val dispatcherRule = CoroutineDispatcherRule()
//
//    private val useCase = mockk<ShowCaseUseCase>()
//    private val stateObserver: Observer<ShowCaseViewState.Action> = mockk(relaxed = true)
//    private val listObserver: Observer<List<MovieShowCase>> = mockk(relaxed = true)
//    private val viewModel = ShowCaseViewModel(useCase)
//
//    @Test
//    fun `when I can get latest movies and had success`() = dispatcherRule.runBlockingTest {
//        // GIVEN
//        val expectedResult = io.gustavobarbosab.coroutinesresult.model.CoroutineResult.Success(emptyList<MovieShowCase>())
//        coEvery { useCase.getLatestMovies() } returns expectedResult
//        viewModel.state.action.observeForever(stateObserver)
//        viewModel.state.latestMovies.observeForever(listObserver)
//
//        // WHEN
//        viewModel.getLatestMovies()
//
//        // THEN
//        verifyOnce {
//            stateObserver.onChanged(ShowCaseViewState.Action.ShowLatestLoading)
//            listObserver.onChanged(expectedResult.data)
//        }
//    }
//
//    @Test
//    fun `when I can get latest movies and had error`() = dispatcherRule.runBlockingTest {
//        // GIVEN
//        val expectedResult = io.gustavobarbosab.coroutinesresult.model.CoroutineResult.Error(Exception())
//        coEvery { useCase.getLatestMovies() } returns expectedResult
//        viewModel.state.action.observeForever(stateObserver)
//        viewModel.state.latestMovies.observeForever(listObserver)
//
//        // WHEN
//        viewModel.getLatestMovies()
//
//        // THEN
//        verifyOnce {
//            stateObserver.onChanged(ShowCaseViewState.Action.ShowLatestLoading)
//            stateObserver.onChanged(ShowCaseViewState.Action.ErrorLoadLatest)
//        }
//
//        verifyNever {
//            listObserver.onChanged(any())
//        }
//    }
//
//    @Test
//    fun `when I can get top rated movies and had success`() = dispatcherRule.runBlockingTest {
//        // GIVEN
//        val expectedResult = io.gustavobarbosab.coroutinesresult.model.CoroutineResult.Success(emptyList<MovieShowCase>())
//        coEvery { useCase.getTopRatedMovies() } returns expectedResult
//        viewModel.state.action.observeForever(stateObserver)
//        viewModel.state.topRatedMovies.observeForever(listObserver)
//
//        // WHEN
//        viewModel.getTopRatedMovies()
//
//        // THEN
//        verifyOnce {
//            stateObserver.onChanged(ShowCaseViewState.Action.ShowTopRatedLoading)
//            listObserver.onChanged(expectedResult.data)
//        }
//    }
//
//    @Test
//    fun `when I can get top rated movies and had error`() = dispatcherRule.runBlockingTest {
//        // GIVEN
//        val expectedResult = io.gustavobarbosab.coroutinesresult.model.CoroutineResult.Error(Exception())
//        coEvery { useCase.getTopRatedMovies() } returns expectedResult
//        viewModel.state.action.observeForever(stateObserver)
//        viewModel.state.topRatedMovies.observeForever(listObserver)
//
//        // WHEN
//        viewModel.getTopRatedMovies()
//
//        // THEN
//        verifyOnce {
//            stateObserver.onChanged(ShowCaseViewState.Action.ShowTopRatedLoading)
//            stateObserver.onChanged(ShowCaseViewState.Action.ErrorLoadTopRated)
//        }
//
//        verifyNever {
//            listObserver.onChanged(any())
//        }
//    }
}