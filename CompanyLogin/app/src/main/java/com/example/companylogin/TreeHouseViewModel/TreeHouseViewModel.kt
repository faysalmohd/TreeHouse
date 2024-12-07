package com.example.companylogin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavController

class NavigationDrawerViewModel : ViewModel() {
    // State for the selected item index
    val selectedItemIndex: MutableState<Int> = mutableStateOf(0)

    // State for controlling drawer open/close
    val drawerState = mutableStateOf(DrawerValue.Closed)


    // Function to update the selected item in the drawer
    fun onSelectItem(index: Int) {
        selectedItemIndex.value = index
    }


    // Function to toggle the drawer open/close state
    fun toggleDrawer() {
        drawerState.value = if (drawerState.value == DrawerValue.Closed) {
            DrawerValue.Open
        } else {
            DrawerValue.Closed
        }
    }
}

class TreeHouseViewModel {

}
