package com.example.facebook.screens.signIn

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.facebook.R

@Composable
fun SignInScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()
            .padding(top=120.dp)) {
            Icon(
                painterResource(id = R.drawable.facebook),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(80.dp),
                contentDescription = stringResource(id = R.string.facebook)
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(stringResource(id = R.string.signInWithFacebook))
        }
    }
}