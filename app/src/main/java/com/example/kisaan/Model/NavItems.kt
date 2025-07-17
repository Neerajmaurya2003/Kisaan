package com.example.kisaan.Model

sealed class NavItems (val route:String,val label:String){
    object HomePage:NavItems("HomePage","HomePage")
    object LoginOrSignupScreen:NavItems("LoginOrSignupScreen","LoginOrSignupScreen")
    object Loading:NavItems("Loading","Loading")
}