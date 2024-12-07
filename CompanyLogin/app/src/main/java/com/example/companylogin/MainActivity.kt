package com.example.companylogin

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.companylogin.NavigationDrawer.NavigationDrawer
import com.example.companylogin.ui.theme.CompanyLoginTheme
import com.google.android.gms.maps.MapsInitializer

data class NavigationItem(
    val title: String,
    val iconSelected: Int,
    val iconUnselected: Int,
    val count: Int? = null,
    val navigate: TreeHouseScreen,
    val modifier: Modifier = Modifier
)

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MapsInitializer.initialize(this, MapsInitializer.Renderer.LATEST){

        }
        enableEdgeToEdge()
        setContent {
            CompanyLoginTheme {
                NavigationDrawer()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AppPreview() {
    CompanyLoginTheme {
        NavigationDrawer()
    }
}