package it.polito.did.gruppo8

//è una specie di enum che definisce lo stato LiveData in base a cui mainscreen chiama uno screen piuttosto che un altro

//volendo creare nuovo screen prima ne definisco il nome-variabile qui e poi assegno la relativa funzione kotlin in MainScreen

sealed class ScreenName {
    object Splash: ScreenName()
    object Initial: ScreenName()
    class Settings: ScreenName()
    class Tutorial: ScreenName ()
    object Join: ScreenName()
    // TODO: RealJoin è nome nuovo della schermata di join nuova, poi da scambiare con quella vecchia
    class RealJoin: ScreenName()

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
    class Playing(): ScreenName()
    object Dashboard: ScreenName()
    class Error(val message:String): ScreenName()

    companion object{
        /**
         * Static method that retrieves the ScreenName class corresponding to the String stringName.
         *
         * @param stringName a String containing the name of the corresponding ScreenName.
         *
         * @return a ScreenName class corresponding to the passed String. An Error ScreenName if no match was found.
         */
        fun stringToScreenName(stringName: String): ScreenName{
            //NOTA: Il metodo va aggiornato di volta in volta con l'elenco di tutte le possibili ScreenName necessarie, se mancanti -Mattia
            return when (stringName) {
                "WaitingStart" -> WaitingStart
                "Playing" -> Playing()
                else -> Error("Unknown screen $stringName")
            }
        }

        /**
         * Static method that retrieves the String corresponding to the ScreenName screenName.
         *
         * @param screenName a ScreenName class corresponding to the desired String.
         *
         * @return a String containing the name of the corresponding ScreenName. An "Error" String if no match was found.
         */
        fun screenNameToString(screenName: ScreenName): String{
            return when(screenName){
                //NOTA: Il metodo va aggiornato di volta in volta con l'elenco di tutte le possibili ScreenName necessarie, se mancanti -Mattia
                WaitingStart -> "WaitingStart"
                Playing() -> "Playing"
                else -> "Error"
            }
        }
    }
}












