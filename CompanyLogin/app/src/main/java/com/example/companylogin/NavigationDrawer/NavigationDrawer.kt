package com.example.companylogin.NavigationDrawer

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.companylogin.About
import com.example.companylogin.Employees
import com.example.companylogin.Home
import com.example.companylogin.Login
import com.example.companylogin.NavigationItem
import com.example.companylogin.News
import com.example.companylogin.Offices
import com.example.companylogin.R
import com.example.companylogin.Settings
import com.example.companylogin.TreeHouseScreen
import com.example.companylogin.viewmodel.NavigationDrawerViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawer() {
    val navigationDrawerViewModel: NavigationDrawerViewModel = viewModel()
    val drawerState = rememberDrawerState(navigationDrawerViewModel.drawerState.value)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    val navigationDrawerScrollState = rememberScrollState()

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = TreeHouseScreen.valueOf(
        backStackEntry?.destination?.route ?: TreeHouseScreen.Login.name
    )
    val items = listOf(
        NavigationItem(
            title = stringResource(R.string.home),
            iconSelected = R.drawable.baseline_home_24,
            iconUnselected = R.drawable.outline_home_24,
            navigate = TreeHouseScreen.Home,
            modifier = Modifier
        ),
        NavigationItem(
            title = stringResource(R.string.news),
            iconSelected = R.drawable.baseline_mail_24,
            iconUnselected = R.drawable.baseline_mail_outline_24,
            navigate = TreeHouseScreen.News,
            count = 22,
            modifier = Modifier
        ),
        NavigationItem(
            title = stringResource(R.string.employees),
            iconSelected = R.drawable.baseline_account_circle_24,
            iconUnselected = R.drawable.outline_account_circle_24,
            navigate = TreeHouseScreen.Employees,
            count = 5,
            modifier = Modifier
        ),
        NavigationItem(
            title = stringResource(R.string.offices),
            iconSelected = R.drawable.baseline_location_on_24,
            iconUnselected = R.drawable.outline_location_on_24,
            navigate = TreeHouseScreen.Offices,
            modifier = Modifier
        ),
        NavigationItem(
            title = stringResource(R.string.settings),
            iconSelected = R.drawable.baseline_settings_24,
            iconUnselected = R.drawable.outline_settings_24,
            navigate = TreeHouseScreen.Settings,
            modifier = Modifier
        ),
        NavigationItem(
            title = stringResource(R.string.about),
            iconSelected = R.drawable.baseline_info_24,
            iconUnselected = R.drawable.baseline_info_outline_24,
            navigate = TreeHouseScreen.About,
            modifier = Modifier
        ),
        NavigationItem(
            title = stringResource(R.string.log_out),
            iconSelected = R.drawable.baseline_logout_24,
            iconUnselected = R.drawable.baseline_logout_24,
            navigate = TreeHouseScreen.Login,
            modifier = Modifier
        )
    )
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(state = navigationDrawerScrollState)
                        .padding(bottom = 70.dp, top = 50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {

                    Spacer(
                        modifier = Modifier
                            .size(25.dp)
                    )

                    Box(
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape),
                    ) {
                        Image(
                            painter = painterResource(R.drawable.cute_boy_avatar_png),
                            contentDescription = "profile picture",
                            modifier = Modifier
                                .height(200.dp)
                                .width(200.dp)
                                .border(
                                    width = 5.dp,
                                    shape = CircleShape,
                                    brush = Brush.linearGradient(
                                        colors = listOf(
                                            Color.Yellow,
                                            Color.Blue,
                                            Color.Red,
                                            Color.Yellow,
                                            Color.Green
                                        )
                                    )
                                ),
                        )
                    }

                    Spacer(
                        modifier = Modifier
                            .size(25.dp)
                    )

                    Text(
                        text = stringResource(R.string.profile_name),
                        fontWeight = FontWeight.ExtraBold
                    )

                    Text(
                        text = stringResource(R.string.profile_email),
                        fontWeight = FontWeight.ExtraBold
                    )

                    Spacer(
                        modifier = Modifier
                            .size(35.dp)
                    )

                    items.forEachIndexed { index, item ->
                        NavigationDrawerItem(
                            icon = {
                                if (index == selectedItemIndex) {
                                    Icon(
                                        painter = painterResource(item.iconSelected),
                                        contentDescription = item.iconSelected.toString()
                                    )
                                } else {
                                    Icon(
                                        painter = painterResource(item.iconUnselected),
                                        contentDescription = item.iconUnselected.toString()
                                    )
                                }
                            },
                            label = {
                                if (index == selectedItemIndex) {
                                    Text(text = item.title, fontWeight = FontWeight.Bold)
                                } else {
                                    Text(text = item.title)
                                }

                            },
                            selected = index == selectedItemIndex,
                            onClick = {
                                navController.navigate(item.navigate.name)
                                selectedItemIndex = index
                                scope.launch {
                                    navigationDrawerViewModel.onSelectItem(0)
                                    drawerState.close()
                                }
                            },
                            badge = {
                                if (item.count != null) {
                                    if (index == selectedItemIndex) {
                                        Text(
                                            text = item.count.toString(),
                                            fontWeight = FontWeight.Bold
                                        )
                                    } else {
                                        Text(text = item.count.toString())
                                    }

                                }
                            },
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                        )
                    }

                    Spacer(modifier = Modifier.size(30.dp))

                    Text(
                        text = stringResource(R.string.copy_right),
                        fontWeight = FontWeight.Bold
                    )

                    Text(text = stringResource(R.string.version), fontWeight = FontWeight.Bold)

                    Spacer(modifier = Modifier.size(25.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.6f),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.instagram),
                            contentDescription = "social",
                            modifier = Modifier.size(25.dp)
                        )
                        Image(
                            painter = painterResource(R.drawable.snapchat),
                            contentDescription = "social",
                            modifier = Modifier.size(25.dp)
                        )
                        Image(
                            painter = painterResource(R.drawable.facebook),
                            contentDescription = "social",
                            modifier = Modifier.size(25.dp)
                        )
                        Image(
                            painter = painterResource(R.drawable.github),
                            contentDescription = "social",
                            modifier = Modifier.size(25.dp)
                        )
                        Image(
                            painter = painterResource(R.drawable.linkedin),
                            contentDescription = "social",
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
            }
        },
        drawerState = drawerState
    )
    {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(stringResource(currentScreen.title))
                    },
                    navigationIcon = {
                        if (currentScreen.title != R.string.login) {
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.baseline_menu_24),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                )
            },
            content = {
                NavHost(
                    navController = navController,
                    startDestination = TreeHouseScreen.Login.name
                ) {
                    composable(route = TreeHouseScreen.Login.name) {
                        Login(
                            title = stringResource(currentScreen.title),
                            navigationState = navController
                        )
                    }
                    composable(route = TreeHouseScreen.Home.name) {
                        Home()
                    }
                    composable(route = TreeHouseScreen.News.name) {
                        News()
                    }
                    composable(route = TreeHouseScreen.Employees.name) {
                        Employees()
                    }
                    composable(route = TreeHouseScreen.Offices.name) {
                        Offices()
                    }
                    composable(route = TreeHouseScreen.Settings.name) {
                        Settings()
                    }
                    composable(route = TreeHouseScreen.About.name) {
                        About()
                    }
                }
            }
        )
    }
}