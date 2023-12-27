package md.labs.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginScreenViewModel : ViewModel() {
    var username by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var email by mutableStateOf("")
        private set

    fun updateUsername(input: String) {
        username = input
    }

    fun updatePassword(input: String) {
        password = input
    }

    fun updateEmail(input: String) {
        email = input
    }
}