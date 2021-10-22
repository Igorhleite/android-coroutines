package igor.leite.coroutinesnotes.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import igor.leite.coroutinesnotes.data.model.Card
import igor.leite.coroutinesnotes.domain.GetRandomCard
import igor.leite.coroutinesnotes.utils.RequestState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val getRandomCard: GetRandomCard,
) : ViewModel() {

    private val _randomCardState = MutableLiveData<RequestState<Card>>()
    val randomCardState get() = _randomCardState

    fun getRandomCard() {
        viewModelScope.launch {
            getRandomCard.invoke()
                .collect { cardEvent ->
                    _randomCardState.value = cardEvent
                }
        }
    }
}