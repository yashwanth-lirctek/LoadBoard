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
        onRequest = { nextPage ->
            repository.getOffers(nextPage, 10, "Active")
        },
        getNextKey = {
            state.page + 10
        },
        onError = {
            isRefreshing.value = false
            state = state.copy(error = it?.localizedMessage)
        },
        onSuccess = { offerData, newKey ->
            isRefreshing.value = false
            val newList = state.offerDataList + offerData
            val error = if (state.offerDataList.isEmpty() && offerData.isEmpty()) "No Data" else null
            state = state.copy(
                offerDataList = newList,
                page = newKey,
                endReached = offerData.isEmpty(),
                isLoading = offerData.isNotEmpty(),
                error = error
            )
        }
    )

    init {
        paginator.reset()
        loadNextItems()
    }

    fun loadNextItems(){
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    fun refreshItems(){
        viewModelScope.launch {
            isRefreshing.value = true
            paginator.reset()
            paginator.loadNextItems()
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