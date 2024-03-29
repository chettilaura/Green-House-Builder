package it.polito.did.gruppo8

//è una specie di enum che definisce lo stato LiveData in base a cui mainscreen chiama uno screen piuttosto che un altro

//volendo creare nuovo screen prima ne definisco il nome-variabile qui e poi assegno la relativa funzione kotlin in MainScreen


sealed class ScreenName (val route: String) {
    object Splash : ScreenName("splash_screen")
    object MainMenu : ScreenName("main_menu_screen")
    object NewGame : ScreenName("new_game_screen")
    object GameSetup : ScreenName("game_setup_screen")
    object JoinGame : ScreenName("join_game_screen")
    object Waiting : ScreenName("waiting_screen")
    object Dashboard : ScreenName("dashboard_screen")
    object FreeItem : ScreenName("free_item_screen")
    object Quiz : ScreenName("quiz_screen")
    object WaitingQuiz : ScreenName("waiting_quiz_screen")
    object CorrectAnswer : ScreenName("correct_answer_screen")
    object WrongAnswer : ScreenName("wrong_answer_screen")
    object NoAnswer : ScreenName("no_answer_screen")
    object HouseOverview : ScreenName("house_overview_screen")
    object GameOver: ScreenName("game_over_screen")
    object Error : ScreenName("error_screen")

    companion object{
        /**
         * Static method that retrieves the ScreenName class corresponding to the String stringName.
         *
         * @param route a String containing the name of the corresponding ScreenName.
         *
         * @return a ScreenName class corresponding to the passed String. An Error ScreenName if no match was found.
         */
        fun routeToScreenName(route: String): ScreenName{
            //NOTA: Il metodo va aggiornato di volta in volta con l'elenco di tutte le possibili ScreenName necessarie, se mancanti -Mattia
            return when (route) {
                "splash_screen" -> Splash
                "main_menu_screen" -> MainMenu
                "new_game_screen" -> NewGame
                "game_setup_screen" -> GameSetup
                "join_game_screen" -> JoinGame
                "waiting_screen" -> Waiting
                "dashboard_screen" -> Dashboard
                "waiting_quiz_screen" -> WaitingQuiz
                "quiz_screen" -> Quiz
                "correct_answer_screen" -> CorrectAnswer
                "wrong_answer_screen" -> WrongAnswer
                "no_answer_screen" -> NoAnswer
                "house_overview_screen" -> HouseOverview
                "game_over_screen" -> GameOver
                else -> Error
            }
        }
    }
}


/*
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
object WaitingStart: ScreenName()
class Playing(): ScreenName()
object Dashboard: ScreenName()
class Error(val message:String): ScreenName()
*/

//class Settings: ScreenName("settings_screen")
//class Tutorial: ScreenName ("tutorial_screen")

/*
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
}

 */













