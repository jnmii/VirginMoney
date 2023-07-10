package com.example.virginmoney.data.di




import com.example.virginmoney.data.remote.ApiDetails
import com.example.virginmoney.data.remote.PeopleCall
import com.example.virginmoney.data.remote.RoomCall
import com.example.virginmoney.data.repository.RepoImpl
import com.example.virginmoney.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module //state current class is a module
@InstallIn(SingletonComponent::class) // informs the scope of class of items inside
class AppModule {

    @Provides
    fun provideOkHttpInstance(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    fun provideRetrofitInstance(
        client: OkHttpClient
    ) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiDetails.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun providePeopleAPI(
        retrofit: Retrofit
    ): PeopleCall {
        return retrofit.create(PeopleCall::class.java)

    }

    @Provides
    fun provideRoomAPI(
        retrofit: Retrofit
    ): RoomCall {
        return retrofit.create(RoomCall::class.java)

    }

    @Provides
    fun provideRepository(
        roomCall: RoomCall,
        peopleCall: PeopleCall
    ) : Repository {
        return RepoImpl(roomCall, peopleCall)
    }

}