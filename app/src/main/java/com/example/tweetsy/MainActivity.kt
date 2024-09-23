package com.example.tweetsy

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tweetsy.api.TweetsyApi
import com.example.tweetsy.screens.CategoryScreen
import com.example.tweetsy.screens.DetailsScree
import com.example.tweetsy.ui.theme.TweetsyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

/**
 * IMPORATANT
 * Inside a Navigation component your view model is connected with your screen
 * as long as your screen is there your view model is there
 * it is not exactly connect with screen it is connected with NavBAckStackEntry class
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var tweetsyApi: TweetsyApi

    @OptIn(DelicateCoroutinesApi::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            TweetsyTheme {
                Scaffold(topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "Tweetsy")
                        },
                        colors = TopAppBarColors(
                            containerColor = Color.Black,
                            titleContentColor = Color.White,
                            scrolledContainerColor = Color.Unspecified,
                            actionIconContentColor = Color.Unspecified,
                            navigationIconContentColor = Color.Unspecified
                        )
                    )
                }) {
                    Box(modifier = Modifier.padding(it)) {
                        App()
                    }
                }
            }
        }
    }
}

/**
 * whenever we navigate one screen to another that time the arguments are stored at another place
 * there is known as  ##{SavedStateHandle}
 * the navBackStackEntry class store the arguments in SavedStateHandle
 */
@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "category") {
        composable(route = "category") {
            CategoryScreen() {
                navController.navigate("detail/${it}")
            }
        }

        composable(route = "detail/{category}",
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                }
            )
        ) {
            DetailsScree()
        }
    }
}