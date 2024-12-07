import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class LoginViewModel : ViewModel() {
    private val _username = mutableStateOf("")
    val username: State<String> get() = _username

    private val _password = mutableStateOf("")
    val password: State<String> get() = _password

    private val _loginStatus = mutableStateOf("")
    val loginStatus: State<String> get() = _loginStatus

    private val _checked = mutableStateOf(false)
    val checked: State<Boolean> get() = _checked

    private val _rememberMe = mutableStateOf(false)
    val rememberMe: State<Boolean> get() = _rememberMe

    fun updateUsername(newUsername: String) {
        _username.value = newUsername
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun attemptLogin() {
        // Add login logic here (e.g., validate username and password)
        _loginStatus.value = if (_username.value == "user" && _password.value == "password") {
            "Login Successful!"
        } else {
            "Incorrect Username or Password."
        }
    }

    fun updateChecked(isVisible: Boolean) {
        _checked.value = isVisible
    }

    fun updateRememberMe(isChecked: Boolean) {
        _rememberMe.value = isChecked
    }
}
