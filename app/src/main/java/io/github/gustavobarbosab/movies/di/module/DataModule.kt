package io.github.gustavobarbosab.movies.di.module

import dagger.Module
import io.github.gustavobarbosab.movies.data.di.ShowCaseDataModule
import io.github.gustavobarbosab.movies.detail.data.di.DetailDataModule

@Module(
  includes = [
      ShowCaseDataModule::class,
      DetailDataModule::class
  ]
)
interface DataModule