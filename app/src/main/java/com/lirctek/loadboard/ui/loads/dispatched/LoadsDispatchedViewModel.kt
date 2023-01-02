package com.lirctek.loadboard.ui.loads.dispatched

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lirctek.loadboard.data.pagination.DefaultPaginator
import com.lirctek.loadboard.data.reqres.LoadsList
import com.lirctek.loadboard.data.reqres.OfferDataList
import com.lirctek.loadboard.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoadsDispatchedViewModel @Inject constructor(
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
                    loadsList = emptyList(),
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
            repository.getTripLoadList(nextPage, 10)
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
            val newList = state.loadsList + offerData
            val error = if (state.loadsList.isEmpty() && offerData.isEmpty()) "No Data" else null
            state = state.copy(
                loadsList = newList,
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
    var loadsList: List<LoadsList> = emptyList(),
    var error: String? = null,
    var endReached: Boolean = false,
    var page: Int = 0
)