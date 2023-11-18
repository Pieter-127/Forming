package com.pieterv.forming.presentation.login.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.pieterv.forming.R
import com.pieterv.forming.presentation.login.LoginEvent
import com.pieterv.forming.presentation.login.LoginScreenState

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun UsernameInput(
    modifier: Modifier,
    state: LoginScreenState,
    onLoginEvent: (LoginEvent) -> Unit
) {
    OutlinedTextField(
        value = state.username,
        onValueChange = {
            onLoginEvent.invoke(LoginEvent.UsernameChanged(it))
        },
        isError = state.usernameError,
        modifier =modifier,
        placeholder = {
            Text(text = stringResource(R.string.username))
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        )
    )
}