package com.example.companylogin

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
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.companylogin.viewmodel.NavigationDrawerViewModel
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(){
    val homeScrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(homeScrollState)
            .padding(vertical = 120.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Profile(
            name = "Faysal Mohammed",
            day = "Fri",
            date = "06th",
            month = "Dec",
            year = 2024,
            time = "12:14",
            image = R.drawable.cute_boy_avatar_png
        )

        Text(
            text = stringResource(R.string.meetings),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.size(15.dp))

        Meeting(day = "Mon", date = "09", month = "Dec", year = 2024, startTime = "12:30", endTime = "14:30", title = "OOP meeting with Laura" )
        Meeting(day = "Tue", date = "10", month = "Dec", year = 2024, startTime = "14:30", endTime = "15:30", title = "Android app dev meeting" )
        Meeting(day = "Wed", date = "11", month = "Dec", year = 2024, startTime = "10:30", endTime = "12:30", title = "Meeting with tutor regarding thesis" )
        Meeting(day = "Thu", date = "12", month = "Dec", year = 2024, startTime = "12:30", endTime = "2:30", title = "Meet Jhon Appleseed for business deal" )

        Spacer(modifier = Modifier.size(25.dp))

        Text(
            text = stringResource(R.string.todos),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.size(10.dp))

        Todo(title = "Shopping with Jhon Appleseed")
        Todo(title = "Reply Jhon Appleseed's email")
        Todo(title = "Laundry")
        Todo(title = "Complete graphics programming")
        Todo(title = "Pickup parcel from posti")
    }
}

@Composable
fun Profile(
    name: String,
    day: String,
    date: String,
    month: String,
    year: Int,
    time: String,
    image: Int
) {
    Column(
        modifier = Modifier.fillMaxWidth(0.8f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape),
        ){
            Image(
                painter = painterResource(image),
                contentDescription = "profile picture",
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
                    .border(
                        width = 5.dp, shape = CircleShape, brush = Brush.linearGradient(
                            colors = listOf(
                                Color.Yellow, Color.Blue, Color.Red, Color.Yellow, Color.Green
                            )
                        )
                    ),
            )
        }

        Spacer(modifier = Modifier
            .size(15.dp))

        Text(text = "Hello $name!", fontWeight = FontWeight.Bold, fontSize = 20.sp)

        Spacer(modifier = Modifier
            .size(5.dp))

        Text(text = "Today is - $day $date, $month $year at $time")

        Spacer(modifier = Modifier
            .size(20.dp))
    }
}

@Composable
fun Meeting(
    day: String,
    date: String,
    month: String,
    year: Int,
    startTime: String,
    endTime: String,
    title: String
){
    Row(
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .padding(bottom = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.rounded_calendar_month_24),
            contentDescription = R.drawable.rounded_calendar_month_24.toString()
        )
        Spacer(modifier = Modifier.size(10.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "$day $date - $month $year at $startTime-$endTime",
                fontSize = 14.sp
            )
            Text(
                text = "$title.",
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun Todo(
    title: String,
){
    Row(
        modifier = Modifier
            .fillMaxWidth(0.8f),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painter = painterResource(R.drawable.round_edit_24),
                contentDescription = R.drawable.round_edit_24.toString(),
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "$title.",
                fontSize = 12.sp
            )
        }

        TextButton(
            onClick = {

            },
        ) {
            Text(
                text = "remove",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 12.sp
            )
        }
    }
}

//@Preview
//@Composable
//fun HomePreview(){
//    Home(title = stringResource(R.string.menu))
//}