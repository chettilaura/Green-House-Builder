package it.polito.did.gruppo8

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
fun Navigation(navController: NavHostController/*, navigator: Navigator*/ ) {

                    val vm: GameViewModel = viewModel()

                    LaunchedEffect("navigation") {
                        Navigator.sharedFlow.onEach {
                            navController.navigate(it.route)
                        }.launchIn(this)}


                    LaunchedEffect("navigation") {
                        Navigator.back_sharedFlow.onEach {
                            navController.popBackStack()
                        }.launchIn(this)}




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

                                composable(route = ScreenName.Error.route) {
                                    ErrorScreen()
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
                            }







    }
