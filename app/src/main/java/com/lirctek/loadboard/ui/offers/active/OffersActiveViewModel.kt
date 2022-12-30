package com.lirctek.loadboard.ui.offers.active

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lirctek.loadboard.data.pagination.DefaultPaginator
import com.lirctek.loadboard.data.reqres.OfferDataList
import com.lirctek.loadboard.repository.Repository
import com.lirctek.loadboard.ui.offers.inactive.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OffersActiveViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    var state by mutableStateOf(ScreenState())
    var isRefreshing : MutableState<Boolean> = mutableStateOf(false)

    private val paginator = DefaultPaginator(
        initialKey = state.page,
        resetValues = { position ->
            if (position == 0) {
                state = state.copy(
                    isLoading = false,
                    offerDataList = emptyList(),
                    error = null,
                    endReached = false,
                    page = 0
                )
            }
        },
        onDataUpdated = {
            state = state.copy(isLoading = it)
        },
        onRequest = { nextPage, status ->
            repository.getOffers(nextPage, 1, status)
        },
        getNextKey = {
            state.page + 1
        },
        onError = {
            isRefreshing.value = false
            state = state.copy(error = it?.localizedMessage)
        },
        onSuccess = { offerData, newKey ->
            isRefreshing.value = false
            val newList = state.offerDataList + offerData
            state = state.copy(
                offerDataList = newList,
                page = newKey,
                endReached = offerData.isEmpty(),
                isLoading = offerData.isNotEmpty()
            )
        }
    )

    init {
        paginator.reset()
        loadNextItems("Active")
    }

    fun loadNextItems(status: String){
        viewModelScope.launch {
            paginator.loadNextItems(status)
        }
    }

    fun refreshItems(status: String){
        viewModelScope.launch {
            isRefreshing.value = true
            paginator.reset()
            paginator.loadNextItems(status)
        }
    }

}

data class ScreenState(
    var isLoading: Boolean = false,
    var offerDataList: List<OfferDataList> = emptyList(),
    var error: String? = null,
    var endReached: Boolean = false,
    var page: Int = 0
)