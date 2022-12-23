package it.polito.did.gruppo8

//è una specie di enum che definisce lo stato LiveData in base a cui mainscreen chiama uno screen piuttosto che un altro

//volendo creare nuovo screen prima ne definisco il nome-variabile qui e poi assegno la relativa funzione kotlin in MainScreen

sealed class ScreenName {
    object Splash: ScreenName()
    object Initial: ScreenName()
    class Settings: ScreenName()
    class Tutorial: ScreenName ()
    class Join: ScreenName()
    class CitySetup: ScreenName()
    class JoinInfo: ScreenName()
    class GameSetup: ScreenName()
    class FreeItem: ScreenName()
    class ItemRecap: ScreenName()
    class Quiz: ScreenName()
    class Shop: ScreenName()
    class BuyItem: ScreenName()
    class GenericLoading: ScreenName()
    class QuizFeedback: ScreenName()
    class HouseOverview: ScreenName()
    class ItemList: ScreenName()
    object GameOver: ScreenName()
    class DistrictRank: ScreenName()
    class PlayersRank: ScreenName()
    class SetupMatch(val matchId: String): ScreenName()
    object WaitingStart: ScreenName()
    class Playing(val team: String): ScreenName()
    object Dashboard: ScreenName()
    class Error(val message:String): ScreenName()
}












