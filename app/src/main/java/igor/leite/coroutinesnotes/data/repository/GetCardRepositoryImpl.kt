package igor.leite.coroutinesnotes.data.repository

import igor.leite.coroutinesnotes.data.ApiClient
import igor.leite.coroutinesnotes.data.model.Card
import igor.leite.coroutinesnotes.utils.RequestState
import igor.leite.coroutinesnotes.utils.extensions.fromDomainToCard
import igor.leite.coroutinesnotes.utils.extensions.safeAwait
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCardRepositoryImpl @Inject constructor(
    private val apiClient: ApiClient,
) : GetCardRepository {
    override suspend fun getRandomCard(): Flow<RequestState<Card>> {
        return apiClient.getRandomCardAsync().safeAwait().map { responseState ->
            when (responseState) {
                is RequestState.ResponseSuccess -> {
                    responseState.value.let { cardDto ->
                        RequestState.ResponseSuccess(cardDto.fromDomainToCard())
                    }
                }
                is RequestState.ResponseFailure -> responseState
                is RequestState.ResponseException -> responseState
                is RequestState.Loading -> responseState
            }
        }
    }
}