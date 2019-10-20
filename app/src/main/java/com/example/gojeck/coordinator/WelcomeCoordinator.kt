package com.example.gojeck.coordinator

class WelcomeCoordinator (private val navigator: Navigator){

    fun startWelcomeCoordinator(){
        navigator.welcomeScreen()
    }
}