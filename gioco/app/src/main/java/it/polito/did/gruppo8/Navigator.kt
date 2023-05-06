package it.polito.did.gruppo8


import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object Navigator {

    private val _navigateTo_sharedFlow =
        MutableSharedFlow<ScreenName>(extraBufferCapacity = 1)
    val navigateTo_sharedFlow = _navigateTo_sharedFlow.asSharedFlow()
    fun navigateTo(screenName: ScreenName) {
        _navigateTo_sharedFlow.tryEmit(screenName)
    }


    private val _back_sharedFlow =
        MutableSharedFlow<ScreenName>(extraBufferCapacity = 1)
    val back_sharedFlow = _back_sharedFlow.asSharedFlow()
    fun back(){
        _back_sharedFlow.tryEmit(ScreenName.Splash)
    }
}