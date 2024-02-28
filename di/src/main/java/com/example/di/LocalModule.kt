package com.example.di

import com.example.data.entity.NoteEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideRealmConfig() : RealmConfiguration {
        return RealmConfiguration.create(
            schema = setOf(
                NoteEntity::class
            )
        )
    }

    @Provides
    @Singleton
    fun provideRealm(configuration: RealmConfiguration) : Realm {
        return Realm.open(configuration)
    }
}