package com.example.companylogin

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.companylogin.NavigationDrawer.NavigationDrawer


enum class TreeHouseScreen(@StringRes val title: Int){
    Login(title = R.string.login),
    Home(title = R.string.home),
    News(title = R.string.news),
    Employees(title = R.string.employees),
    Offices(title = R.string.offices),
    Settings(title = R.string.settings),
    About(title = R.string.about)
}
