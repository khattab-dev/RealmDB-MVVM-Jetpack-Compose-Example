package com.example.di

import com.example.data.repo.NoteRepoImpl
import com.example.domain.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindTaskRepo(taskRepository: NoteRepoImpl) : NoteRepository
}