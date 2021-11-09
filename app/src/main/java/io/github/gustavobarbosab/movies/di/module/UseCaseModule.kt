package io.github.gustavobarbosab.movies.di.module

import dagger.Module
import io.github.gustavobarbosab.movies.data.di.ShowCaseDataModule

@Module(
  includes = [
      ShowCaseDataModule::class
  ]
)
interface UseCaseModule