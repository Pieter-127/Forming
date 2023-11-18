package com.pieterv.forming.presentation.login.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.pieterv.forming.R
import com.pieterv.forming.presentation.login.LoginEvent
import com.pieterv.forming.presentation.login.LoginScreenState

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PasswordInput(
    modifier: Modifier,
    state: LoginScreenState,
    onLoginEvent: (LoginEvent) -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = state.password,
        onValueChange = {
            onLoginEvent(LoginEvent.PasswordChanged(it))
        },
        isError = state.passwordError,
        modifier = modifier,
        placeholder = {
            Text(text = stringResource(R.string.password))
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisible)
                Icons.Filled.Visibility else
                Icons.Filled.VisibilityOff

            val description =
                if (passwordVisible) stringResource(R.string.hide_password) else stringResource(
                    R.string.show_password
                )
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, description)
            }
        }
    )
}
