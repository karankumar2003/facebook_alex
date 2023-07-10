package com.example.facebook.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.facebook.R
import com.example.facebook.ui.theme.ButtonGray

@Composable
fun HomeScreen(
    navigateToSignInScreen: ()-> Unit
) {
    val viewModel = viewModel<HomeScreenViewModel>()
    val screenState by viewModel.screenState.collectAsState()

    when(screenState){
        is HomeScreenState.Loading -> LoadingScreen()
        is HomeScreenState.SignInRequired -> LaunchedEffect(key1 = true){
            navigateToSignInScreen()
        }
        is HomeScreenState.isLoaded -> HomeScreenContent()
    }


}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        CircularProgressIndicator()
    }
}

@Composable
private fun HomeScreenContent() {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        LazyColumn {
            item {
                TopAppBar()
            }
            item {
                TabBar()
            }
            item {
                StatusUpdateBar(
                    onTextChange = {},
                    onSendClicked = {}
                )
            }
        }
    }
}

@Composable
fun TopAppBar() {
    Surface {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                stringResource(id = R.string.facebook),
                color = MaterialTheme.colorScheme.primary,
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
}

sealed class TabIcon {
    data class ImageVectorIcon(val icon: ImageVector) : TabIcon()
    data class PainterIcon(val icon: Painter) : TabIcon()
}

@Composable
fun TabBar() {
    Surface {

        var index by remember {
            mutableIntStateOf(0)
        }
        val tabList = listOf(
            TabIcon.ImageVectorIcon(Icons.Rounded.Home),
            TabIcon.PainterIcon(painterResource(id = R.drawable.tv_icon)),
            TabIcon.PainterIcon(painterResource(id = R.drawable.store)),
            TabIcon.PainterIcon(painterResource(id = R.drawable.newspaper)),
            TabIcon.ImageVectorIcon(Icons.Rounded.Notifications),
            TabIcon.ImageVectorIcon(Icons.Rounded.Menu)
        )

        TabRow(
            selectedTabIndex = index,
            contentColor = MaterialTheme.colorScheme.primary
        ) {
            tabList.forEachIndexed { i, tabIcon ->
                Tab(
                    selected = index == i,
                    onClick = { index = i },
                    modifier = Modifier.height(48.dp)
                ) {
                    when (tabIcon) {
                        is TabIcon.ImageVectorIcon -> {
                            Icon(
                                imageVector = tabIcon.icon,
                                contentDescription = "TabIcon",
                                tint = if (index == i) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    MaterialTheme.colorScheme.onSurface.copy(
                                        alpha = 0.44f
                                    )
                                }
                            )
                        }

                        is TabIcon.PainterIcon -> {
                            Icon(
                                painter = tabIcon.icon,
                                contentDescription = "TabIcon",
                                tint = if (index == i) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    MaterialTheme.colorScheme.onSurface.copy(
                                        alpha = 0.44f
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatusUpdateBar(
    onTextChange: (String) -> Unit,
    onSendClicked: (String) -> Unit
) {
    Surface {
        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://media.licdn.com/dms/image/D5603AQH63CO9GAL7sg/profile-displayphoto-shrink_200_200/0/1685352524697?e=1694649600&v=beta&t=Y32WzpxXctiNgnNOz094OPYTt-e2ebDmfYf1n2rxthQ")
                        .crossfade(true)
                        .placeholder(R.drawable.dp_placeholder)
                        .build(),
                    contentDescription = stringResource(id = R.string.profile_image),
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .border(
                            1.dp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.25f),
                            CircleShape
                        )

                )
                var text by remember {
                    mutableStateOf("")
                }

                TextField(
                    value = text,
                    onValueChange = {
                        text = it
                        onTextChange(it)
                    },
                    placeholder = {
                        Text(
                            "What's on your mind?",
                            color = MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.66f
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Send
                    ),
                    keyboardActions = KeyboardActions(
                        onSend = {
                            onSendClicked(text)
                            text = ""
                        }
                    )


                )

            }

            Divider()

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
            ) {
                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.weight(1f),
                    shape = RectangleShape
                ) {
                    Icon(
                        painterResource(id = R.drawable.video_camera),
                        contentDescription = stringResource(id = R.string.live)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = stringResource(id = R.string.live))

                }

                Divider(
                    color = Color.Black.copy(
                        alpha= 0.25f
                    ),
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                )

                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.weight(1f),
                    shape = RectangleShape
                ) {
                    Icon(
                        painterResource(id = R.drawable.photo),
                        contentDescription = stringResource(id = R.string.live)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = stringResource(id = R.string.live))

                }

                Divider(
                    color = Color.Black.copy(
                        alpha= 0.25f
                    ),
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                )

                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.weight(1f),
                    shape = RectangleShape
                ) {
                    Icon(
                        painterResource(id = R.drawable.speech_bubble),
                        contentDescription = stringResource(id = R.string.discuss),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = stringResource(id = R.string.discuss))

                }
            }


        }
    }
}
