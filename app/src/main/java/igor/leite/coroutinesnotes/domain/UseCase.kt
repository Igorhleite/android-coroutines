package igor.leite.coroutinesnotes.domain

import igor.leite.coroutinesnotes.utils.RequestState
import kotlinx.coroutines.flow.Flow

interface UseCase {
    interface Empty<Type> {
        suspend operator fun invoke(): Flow<RequestState<Type>>
    }

    interface Params<Type, in Params> {
        suspend operator fun invoke(params: Params): Flow<RequestState<Type>>
    }
}