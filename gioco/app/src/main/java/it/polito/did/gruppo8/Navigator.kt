package it.polito.did.gruppo8


import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object Navigator {

    //val _navController = rememberNavController()

    private val _sharedFlow =
        MutableSharedFlow<ScreenName>(extraBufferCapacity = 1)
    val sharedFlow = _sharedFlow.asSharedFlow()


    private val _back_sharedFlow =
        MutableSharedFlow <ScreenName> (extraBufferCapacity = 1)
    val back_sharedFlow = _back_sharedFlow.asSharedFlow()




    fun navigateTo(screenName: ScreenName) {
        _sharedFlow.tryEmit(screenName)
    }

    fun back(screenName: ScreenName){
        _back_sharedFlow.tryEmit(screenName)
    }


}