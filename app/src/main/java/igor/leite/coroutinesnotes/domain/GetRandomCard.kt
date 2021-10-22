package igor.leite.coroutinesnotes.domain

import igor.leite.coroutinesnotes.data.model.Card
import igor.leite.coroutinesnotes.data.repository.GetCardRepository
import igor.leite.coroutinesnotes.utils.RequestState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRandomCard @Inject constructor(
    private val getCardRepository: GetCardRepository,
) : UseCase.Empty<Card> {
    override suspend fun invoke(): Flow<RequestState<Card>> {
        return getCardRepository.getRandomCard()
    }
}