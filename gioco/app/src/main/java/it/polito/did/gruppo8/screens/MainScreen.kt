package it.polito.did.gruppo8.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import it.polito.did.gruppo8.GameViewModel
import it.polito.did.gruppo8.ScreenName

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val vm: GameViewModel = viewModel()
    val players = vm.players.observeAsState()

    //in base allo stato fa partire uno screen diverso -> vm.screenName è un liveData definito in gameManager
    //il primo stato (nomeScreen) assegnato è lo splash che infatti parte per primo di default-> assegnaz fatta in gameManager
    //i nomi degli stati sono definiti nella classe ScreenName (che non fa però il collegamento allo screen vero e proprio, quello lo fa main screen (loader))
    //volendo creare un nuovo screen qui definisco passaggio da stato-screen a chiamata alla screen vera e propria (chiamo lo screen quando stato impostato sul corrispondente )

    //TODO: Questo elenco di screen fatto tutto insieme a priori penso possa essere più fuorviante che di aiuto. Piuttosto che buttar giù già tutto insieme, forse è il caso di procedere per gradi, implementando una cosa per volta -Mattia

    when (val screenName = vm.currentScreenName.observeAsState().value) {
        is ScreenName.Splash -> SplashScreen(modifier)
        is ScreenName.Initial -> OpenScreen(
            vm::onCreateNewGame,
            vm::onPreJoinGame,
            modifier)
        is ScreenName.SetupMatch -> SetupMatchScreen(
            screenName.matchId,
            players,
            vm::onStartGame,
            modifier)
        is ScreenName.WaitingStart -> WaitScreen(modifier)
        // TODO: Implementare la nostra JoinScreen (usiamo quella di Malnati per testare cose) -Mattia
        is ScreenName.Join -> MalnatiJoinScreen(vm::onJoinGame, modifier)

        // TODO: controllare se così è corretto per la schermata nuova -Edo
        is ScreenName.RealJoin -> JoinGameScreen(vm::onJoinGame, modifier)

        is ScreenName.Settings -> SettingsScreen(modifier)
        //is ScreenName.Tutorial -> TutorialScreen (modifier)
        is ScreenName.CitySetup -> CitySetupScreen(modifier)
        is ScreenName.JoinInfo -> JoinInfoScreen(modifier)
        is ScreenName.GameSetup -> GameSetupScreen(modifier)
        is ScreenName.FreeItem -> FreeItemScreen(modifier)
        is ScreenName.ItemRecap -> ItemRecapScreen(modifier)
        is ScreenName.Quiz -> QuizScreen(modifier)
        is ScreenName.Dashboard -> DashboardScreen(modifier)
        is ScreenName.Playing -> PlayerScreen(modifier)
        is ScreenName.Shop -> ShopScreen(modifier)
        is ScreenName.BuyItem -> BuyItemScreen(modifier)
        is ScreenName.GenericLoading -> GenericLoadingScreen(modifier)
        is ScreenName.QuizFeedback -> QuizFeedbackScreen(modifier)
        is ScreenName.HouseOverview -> HouseOverviewScreen(modifier)
        is ScreenName.ItemList -> ItemListScreen(modifier)
        is ScreenName.GameOver -> GameOverScreen(modifier)
        is ScreenName.DistrictRank -> DistrictRankingScreen(modifier)
        is ScreenName.PlayersRank -> PlayersRankingScreen(modifier)
        is ScreenName.Error -> ErrorScreen(screenName.message, modifier)
        null -> Box(modifier)
        else -> {}
    }
}






// CIMITERO SETUP MALNATI

//        is ScreenName.Initial -> InitialScreen(
//            vm::onCreateNewGame,
//            vm::onJoinGame,
//            modifier)

//        is ScreenName.Join -> JoinScreen(modifier)