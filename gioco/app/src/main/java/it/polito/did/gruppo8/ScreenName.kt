package it.polito.did.gruppo8

sealed class ScreenName {
    object Splash: ScreenName()
    object Initial: ScreenName()
    class SetupMatch(val matchId: String): ScreenName()
    object WaitingStart: ScreenName()
    class Playing(val team: String): ScreenName()
    object Dashboard: ScreenName()
    class Error(val message:String): ScreenName()
}












