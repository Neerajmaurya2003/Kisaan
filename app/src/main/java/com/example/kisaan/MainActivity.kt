package com.example.kisaan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kisaan.Model.NavItems
import com.example.kisaan.View.HomePage
import com.example.kisaan.View.LoadingScreen
import com.example.kisaan.View.LoginOrSignupScreen
import com.example.kisaan.ViewModel.AuthViewModel
import com.example.kisaan.ui.theme.KisaanTheme

val PrimaryGreen = Color(0xFF026928)
val DarkGreen = Color(0xFF388E3C)
val LightGreen = Color(0xFFC8E6C9)
val Brown = Color(0xFF795548)
val LightBrown = Color(0xFFD7CCC8)
val BackgroundCream = Color(0xFFF5F5F5)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val authVieModel:AuthViewModel by viewModels()
        setContent {
            KisaanTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
               AppNavigation(Modifier.fillMaxSize(),authVieModel)
                }
            }
        }
    }
}

@Composable
fun AppNavigation(modifier: Modifier = Modifier,authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavItems.Loading.route){
        composable(NavItems.LoginOrSignupScreen.route){
            LoginOrSignupScreen(modifier,navController,authViewModel)
        }
        composable(NavItems.HomePage.route){
            HomePage(modifier,navController,authViewModel)
        }
        composable(NavItems.Loading.route){
            LoadingScreen(modifier,navController,authViewModel)
        }
    }
    
}

