package com.lirctek.loadboard.ui.offers.offerDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lirctek.loadboard.data.reqres.*
import com.lirctek.loadboard.network.NetworkResponse
import com.lirctek.loadboard.repository.Repository
import com.lirctek.loadboard.repository.TestException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OfferEditViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    var mAddDescription: MutableLiveData<DescriptionResponse> = MutableLiveData()
    var mGetDescription: MutableLiveData<List<DescriptionListResponse>> = MutableLiveData()

    init {
        getDescriptions()
    }

    fun addCondition(condition: String, proposedPickup: String) {
        //Add Condition and Proposed Pickup to Conditions List
    }

    fun getDescriptions(){
        viewModelScope.launch {
            val response = repository.getDescription()
            when (response) {
                is NetworkResponse.Success -> {
                    response.body.let {
                        mGetDescription.value = it
                    }
                }
                is NetworkResponse.Error -> {

                }
            }
        }
    }

    fun addDescription(descriptionRequest: DescriptionRequest) {
        viewModelScope.launch {
            val response = repository.addEditDescription(descriptionRequest)
            when (response) {
                is NetworkResponse.Success -> {
                    response.body.let {
                        mAddDescription.value = it
                    }
                }
                is NetworkResponse.Error -> {

                }
            }
        }
    }

    fun submitRequest(){}

    fun getOfferDetails(){}

    fun getOfferList(){}

}