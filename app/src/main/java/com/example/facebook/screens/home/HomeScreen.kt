package com.example.facebook.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.facebook.R
import com.example.facebook.ui.theme.ButtonGray
import com.example.facebook.ui.theme.FbBlue

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        TopAppBar()
        }

}

@Composable
fun TopAppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "facebook",
            color = FbBlue,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.weight(1f))
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .clip(CircleShape)
                .background(ButtonGray)
        ) {
            Icon(
                Icons.Rounded.Search,
                "Search",

                )
        }
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            onClick = { /*TODO*/ }, modifier = Modifier
                .clip(CircleShape)
                .background(ButtonGray)
        ) {
            Icon(
                painterResource(id = R.drawable.speech_bubble),
                "Chats",
                modifier = Modifier.size(24.dp)

            )
        }
    }
}