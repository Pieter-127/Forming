package com.pieterv.forming.presentation.login.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.pieterv.forming.R
import com.pieterv.forming.presentation.login.LoginEvent
import com.pieterv.forming.presentation.login.LoginScreenState

@Composable
fun RememberLoginComponent(
    modifier: Modifier,
    state: LoginScreenState,
    onLoginEvent: (LoginEvent) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = state.keepLoggedIn,
            onCheckedChange = {
                onLoginEvent(LoginEvent.RememberMeChanged(it))
            }
        )

        Text(text = stringResource(R.string.keep_me_logged_in))
    }
}