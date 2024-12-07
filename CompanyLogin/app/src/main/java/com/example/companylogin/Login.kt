package com.example.companylogin

import LoginViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.companylogin.ui.theme.CompanyLoginTheme

@Composable
fun Login(title: String, navigationState: NavHostController) {
    AppBar(title = title)
    LoginPage(
        title = stringResource(R.string.tree_house),
        loginText = stringResource(R.string.username),
        passwordText = stringResource(R.string.password),
        navigationState = navigationState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(title)
        }
    )
}

@Composable
fun LoginPage(title: String, loginText: String, passwordText: String, navigationState: NavHostController, loginViewModel: LoginViewModel = viewModel()) {
    val scrollState = rememberScrollState()
    val username by loginViewModel.username
    val password by loginViewModel.password
    val loginStatus by loginViewModel.loginStatus
    val checked by loginViewModel.checked
    val rememberMe by loginViewModel.rememberMe

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.company_logo),
            contentDescription = null,
        )
        Text(
            text = title,
            modifier = Modifier,
            fontSize = 35.sp,
            fontFamily = FontFamily(Font(R.font.agbalumo))
        )

        Spacer(Modifier.size(20.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { loginViewModel.updateUsername(it) },
            label = { Text(text = loginText) },
            modifier = Modifier
                .fillMaxWidth(0.9f),
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.baseline_person_24),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        )

        Spacer(Modifier.size(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { loginViewModel.updatePassword(it) },
            label = { Text(text = passwordText) },
            visualTransformation = if (checked) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth(0.9f),
            leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.baseline_key_24),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        loginViewModel.updateChecked(!checked)
                    }
                ) {
                    Icon(
                    painter = if (checked) painterResource(R.drawable.baseline_visibility_off_24)
                    else painterResource(R.drawable.baseline_remove_red_eye_24),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                    )
                }
            },
        )

        Spacer(Modifier.size(5.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f),
            horizontalArrangement = Arrangement.Start,
        ) {
            Text(
                text = loginStatus,
                color = MaterialTheme.colorScheme.error.takeIf { loginStatus.contains("Incorrect") }
                    ?: MaterialTheme.colorScheme.primary,
                fontSize = 15.sp,
            )
        }

        Spacer(Modifier.size(2.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = rememberMe,
                onCheckedChange = { loginViewModel.updateRememberMe(it) }
            )
            Text(
                text = stringResource(R.string.remember_me)
            )
        }

        Spacer(Modifier.size(10.dp))

        Button(
            onClick = {
                loginViewModel.attemptLogin()
                if (username == "user" && password == "password"){
                    navigationState.navigate(TreeHouseScreen.Home.name)
                }
            },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.09f)
        ) {
            Text(
                text = stringResource(R.string.login),
                modifier = Modifier,
                fontSize = 20.sp
            )
        }
    }
}


