package igor.leite.coroutinesnotes.data

import igor.leite.coroutinesnotes.data.model.dto.CardModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {
    @GET("v7/randomcard.php")
    suspend fun getRandomCardAsync(): Response<CardModel>
}