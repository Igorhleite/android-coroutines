package igor.leite.coroutinesnotes.data.repository

import igor.leite.coroutinesnotes.data.model.Card
import igor.leite.coroutinesnotes.utils.RequestState
import kotlinx.coroutines.flow.Flow

interface GetCardRepository {
    suspend fun getRandomCard(): Flow<RequestState<Card>>
}