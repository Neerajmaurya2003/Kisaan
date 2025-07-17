package com.example.kisaan.View

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.kisaan.Model.NavItems
import com.example.kisaan.PrimaryGreen
import com.example.kisaan.ViewModel.AuthState
import com.example.kisaan.ViewModel.AuthViewModel

@Composable
fun LoadingScreen(modifier: Modifier = Modifier, navController: NavHostController,authViewModel: AuthViewModel) {

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> navController.navigate(NavItems.HomePage.route) {
                popUpTo(0) {
                    inclusive = true
                }
            }
            is AuthState.Unauthenticated -> navController.navigate(NavItems.LoginOrSignupScreen.route) {
                popUpTo(0) {
                    inclusive = true
                }
            }

            is AuthState.Error -> Toast.makeText(
                context, (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT
            ).show()

            else -> Unit

        }
    }


    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment =Alignment.Center
    ){
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = PrimaryGreen,
            strokeWidth = 5.dp
        )
    }
}