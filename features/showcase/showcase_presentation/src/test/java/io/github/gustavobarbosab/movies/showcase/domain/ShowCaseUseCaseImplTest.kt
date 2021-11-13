package io.github.gustavobarbosab.showcase.showcase.domain

class ShowCaseUseCaseImplTest {

//    @get:Rule
//    val dispatcherRule = CoroutineDispatcherRule()
//
//    val movieRepository = mockk<ShowCaseRepository>()
//    val sessionRepository = mockk<SessionRepository>()
//    val showCaseUseCase = ShowCaseUseCaseImpl(movieRepository, sessionRepository)
//
//    @Test
//    fun `when I can get latest movies`() = dispatcherRule.runBlockingTest {
//        // GIVEN
//        val movie1 = ShowCaseMocker.createMovie(id = 1L)
//        val movie2 = ShowCaseMocker.createMovie(id = 2L)
//        val movie3 = ShowCaseMocker.createMovie(id = 1L, favorite = true)
//
//        coEvery { movieRepository.getLatestMovies() } returns CoroutineResult.Success(
//            listOf(
//                movie1,
//                movie2
//            )
//        )
//        coEvery { sessionRepository.favoriteMovies() } returns CoroutineResult.Success(
//            listOf(movie3)
//        )
//
//        // WHEN
//        val listResult = showCaseUseCase.getLatestMovies()
//        val firstMovie = (listResult as CoroutineResult.Success).data.first()
//
//        // THEN
//        Assert.assertThat(listResult, instanceOf(CoroutineResult.Success::class.java))
//        Assert.assertEquals(2, listResult.data.size)
//        Assert.assertTrue(firstMovie.isFavorite)
//    }
}