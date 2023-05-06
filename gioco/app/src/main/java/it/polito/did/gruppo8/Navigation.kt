package it.polito.did.gruppo8

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import it.polito.did.gruppo8.screens.DashboardScreen
import it.polito.did.gruppo8.screens.ErrorScreen
import it.polito.did.gruppo8.screens.JoinGameScreen
import it.polito.did.gruppo8.screens.MainMenuScreen
import it.polito.did.gruppo8.screens.NewScreens.GameSetupScreen
import it.polito.did.gruppo8.screens.NewScreens.NewGameScreen
import it.polito.did.gruppo8.screens.SplashScreen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun Navigation(navController: NavHostController) {

    val vm: GameViewModel = viewModel()

    LaunchedEffect("navigateTo_linkToNavigator") {

        Navigator.navigateTo_sharedFlow.onEach {
            navController.navigate(it.route)
        }.launchIn(this)
    }

    LaunchedEffect("back_linkToNagivator") {
        Navigator.back_sharedFlow.onEach {
            navController.popBackStack()
        }.launchIn(this)
    }


    NavHost(
        navController = navController,
        startDestination = ScreenName.Splash.route
    ) {
        composable(route = ScreenName.Splash.route) {
            SplashScreen()
        }

        composable(route = ScreenName.MainMenu.route) {
            MainMenuScreen(vm)
        }

        composable(route = ScreenName.GameSetup.route) {
            GameSetupScreen(vm)
        }

        composable(route = ScreenName.JoinGame.route) {
            JoinGameScreen(vm)
        }

        composable(route = ScreenName.NewGame.route) {
            NewGameScreen(vm)
        }

        composable(route = ScreenName.Dashboard.route) {
            DashboardScreen()
        }

        composable(route = ScreenName.Error.route) {
            ErrorScreen()
        }
    }
}
