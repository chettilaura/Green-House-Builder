package it.polito.did.gruppo8

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class Navigator {

    private val _sharedFlow =
        MutableSharedFlow<ScreenName>(extraBufferCapacity = 1)
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun navigateTo(ScreenName: ScreenName) {
        _sharedFlow.tryEmit(ScreenName)
    }
}

    /*
    enum class NavTarget(val route: String) {

        Home("home"),
        Detail("detail")
    }

     */

