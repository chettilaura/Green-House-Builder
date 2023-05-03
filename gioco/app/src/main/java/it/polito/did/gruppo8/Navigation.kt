package it.polito.did.gruppo8

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun Navigation(navController: NavHostController, navigator: Navigator ) {
                    LaunchedEffect("navigation") {
                        navigator.sharedFlow.onEach {
                            navController.navigate(it.route)
                        }.launchIn(this)}


                            NavHost(
                                navController = navController,
                                startDestination = ScreenName.MainMenu.route
                            ) {

                                composable(route = ScreenName.MainMenu.route) {
                                    Log.d("Laura", "mainmenu")
                                    MainMenuScreen(navController = navController)
                                }

                                composable(route = ScreenName.Error.route) {
                                    Log.d("Laura", "error")
                                    ErrorScreen()
                                }

                                composable(route = ScreenName.GameSetup.route) {
                                    GameSetupScreen()
                                }

                                composable(route = ScreenName.JoinGame.route) {
                                    JoinGameScreen()
                                }

                                composable(route = ScreenName.NewGame.route) {
                                    NewGameScreen()
                                }

                                composable(route = ScreenName.Dashboard.route) {
                                    DashboardScreen()
                                }
                            }







    }
