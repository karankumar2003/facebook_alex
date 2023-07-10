package com.example.facebook.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class HomeScreenState{
    object Loading : HomeScreenState()
    data class isLoaded(
        val avatarUrl : String
    ): HomeScreenState()
    object SignInRequired : HomeScreenState()
}

class HomeScreenViewModel : ViewModel(){
    private val _screenState = MutableStateFlow<HomeScreenState>(
        HomeScreenState.Loading
    )
    val screenState = _screenState.asStateFlow()

    init {
        // TODO Check for Sign In
        viewModelScope.launch {
            _screenState.emit(HomeScreenState.SignInRequired)
        }
    }
}