package com.example.companylogin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Settings() {
    var isDarkModeEnabled by remember { mutableStateOf(false) }
    var isLocationAccessEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 110.dp)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        SettingHeader("Account")

        SettingItem(
            title = "Username",
            icon = R.drawable.baseline_chevron_right_24,
            func = {}
        )
        Divider()
        SettingItem(
            title = "Password",
            icon = R.drawable.baseline_chevron_right_24,
            func = {}
        )

        SettingHeader("General")

        SettingItem(
            title = "Accessibility",
            icon = R.drawable.baseline_chevron_right_24,
            func = {}
        )
        Divider()
        SettingItem(
            title = "Modes and Routines",
            icon = R.drawable.baseline_chevron_right_24,
            func = {}
        )
        Divider()
        SettingItem(
            title = "Notification",
            icon = R.drawable.baseline_chevron_right_24,
            func = {}
        )
        Divider()
        SettingItem(
            title = "Themes",
            icon = R.drawable.baseline_chevron_right_24,
            func = {}
        )

        SettingHeader("App and Privacy")

        SettingItem(
            title = "Advanced feature",
            icon = R.drawable.baseline_chevron_right_24,
            func = {}
        )
        Divider()
        SettingItem(
            title = "Security and Privacy",
            icon = R.drawable.baseline_chevron_right_24,
            func = {}
        )
        Divider()
        SettingItem(
            title = "Backup",
            icon = R.drawable.baseline_chevron_right_24,
            func = {}
        )
    }
}

@Composable
fun SettingItem(
    title: String,
    icon: Int,
    func: () -> Unit,
){
    var isNotificationsEnabled by remember { mutableStateOf(true) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { func() }
            .padding(horizontal = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(modifier = Modifier.weight(1f), text = title, fontSize = 13.sp)
        Icon(
            painter = painterResource(icon),
            contentDescription = null
        )
    }
}

@Composable
fun SettingHeader(
    title: String
){
    Text(
        text = title,
        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(top = 10.dp)
    )
}

@Preview
@Composable
fun SettingsScreenPreview() {
    Settings()
}
