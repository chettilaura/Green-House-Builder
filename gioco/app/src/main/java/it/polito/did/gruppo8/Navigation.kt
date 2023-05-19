package it.polito.did.gruppo8

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import it.polito.did.gruppo8.screens.*
import it.polito.did.gruppo8.screens.NewScreens.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun Navigation(navController: NavHostController) {
    val vm: GameViewModel = viewModel()

    LaunchedEffect("navigateTo_linkToNavigator") {
        Navigator.navigateTo_sharedFlow.onEach {
            Log.d("Navigation", "Navigating to ${it.route}")
            navController.navigate(it.route)
        }.launchIn(this)
    }

    LaunchedEffect("back_linkToNavigator") {
        Navigator.back_sharedFlow.onEach {
            Log.d("Navigation", "Navigating to ${it.route}")
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

        composable(route = ScreenName.JoinGame.route) {
            JoinGameScreen(vm)
        }

        composable(route = ScreenName.NewGame.route) {
            NewGameScreen(vm)
        }

        composable(route = ScreenName.GameSetup.route) {
            GameSetupScreen(vm)
        }

        composable(route = ScreenName.Waiting.route) {
            WaitingScreen(vm, vm.gameInfos.value!!.cityName!!, "Attendi")
        }

        composable(route = ScreenName.Dashboard.route) {
            DashboardScreen()
        }

        composable(route = ScreenName.FreeItem.route) {
            FreeItemScreen()
        }

        composable(route = ScreenName.WaitingQuiz.route) {
            WaitingQuizScreen(vm)
        }

        composable(route = ScreenName.Quiz.route) {
            QuizScreen(vm)
        }

        composable(route = ScreenName.CorrectAnswer.route) {
            CorrectAnswerScreen(vm)
        }

        composable(route = ScreenName.WrongAnswer.route) {
            WrongAnswerScreen(vm)
        }

        composable(route = ScreenName.NoAnswer.route) {
            NoAnswerScreen(vm)
        }

        composable(route = ScreenName.HouseOverview.route) {
            HouseOverviewScreen()
        }

        composable(route = ScreenName.Error.route) {
            ErrorScreen()
        }
    }
}
