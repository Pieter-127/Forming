package com.pieterv.forming.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pieterv.forming.R
import com.pieterv.forming.presentation.component.AppTitle
import com.pieterv.forming.presentation.login.component.PasswordInput
import com.pieterv.forming.presentation.login.component.RememberLoginComponent
import com.pieterv.forming.presentation.login.component.UsernameInput
import com.pieterv.forming.ui.theme.FormingTheme
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel) {
    LoginScreenContent(viewModel.state) {
        viewModel.onEvent(it)
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        viewModel.screenEvent.receiveAsFlow().collect { event ->
            when (event) {
                LoginViewModel.ScreenEvent.Success -> {
                    //now nav away with something like `navController.navigate(Screen.MainScreen.route)`
                }

                LoginViewModel.ScreenEvent.ForgotPassword -> {
                    //now nav away with something like `navController.navigate(Screen.ForgotPassword.route)`
                }

                LoginViewModel.ScreenEvent.Registration -> {
                    //now nav away with something like `navController.navigate(Screen.Registration.route)`
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreenContent(state = LoginScreenState()) {

    }
}

@Composable
fun LoginScreenContent(state: LoginScreenState, onLoginEvent: (LoginEvent) -> Unit) {
    FormingTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AppTitle(Modifier.padding(vertical = 16.dp))
                    UsernameInput(
                        modifier = Modifier.fillMaxWidth(),
                        state = state,
                        onLoginEvent = onLoginEvent
                    )
                    if (state.usernameError) {
                        Text(
                            text = stringResource(R.string.username_empty_error),
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                    PasswordInput(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        state = state,
                        onLoginEvent = onLoginEvent
                    )
                    if (state.passwordError) {
                        Text(
                            text = stringResource(R.string.password_empty_error),
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                    RememberLoginComponent(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        state,
                        onLoginEvent
                    )
                    Button(modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.small.copy(
                            all = CornerSize(6.dp)
                        ),
                        contentPadding = PaddingValues(16.dp),
                        onClick = { onLoginEvent(LoginEvent.Submit) }) {
                        Text(
                            text = stringResource(R.string.login),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                    TextButton(onClick = { onLoginEvent(LoginEvent.ForgotPassword) }) {
                        Text(text = stringResource(R.string.forgot_password))
                    }
                    Spacer(
                        Modifier.weight(0.6f)
                    )
                    Row(Modifier.clickable {
                        onLoginEvent(LoginEvent.StartRegistration)
                    }) {
                        Text(text = stringResource(R.string.create_account_text))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = stringResource(R.string.sign_up),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}