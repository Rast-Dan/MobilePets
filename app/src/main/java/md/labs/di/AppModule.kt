package md.labs.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import md.labs.db.PetDao
import md.labs.db.PetRepository
import md.labs.db.PetRepositoryImpl
import md.labs.db.PetsDb
import md.labs.utils.Constants.Companion.PET_TABLE

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun providePetDb(//доступ к базе данных
        @ApplicationContext
        context : Context
    ) = Room.databaseBuilder(
        context,
        PetsDb::class.java,
        PET_TABLE
    ).build()
    @Provides
    fun providePetDao(//доступ к Data Access Object
    petDb: PetsDb
    ) = petDb.petDao()
    @Provides
    fun providePetRepository(//доступ к репозиторию с данными
        petDao: PetDao
    ): PetRepository = PetRepositoryImpl(
        petDao = petDao
    )
}