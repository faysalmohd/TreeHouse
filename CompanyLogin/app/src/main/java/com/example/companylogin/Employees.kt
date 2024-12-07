package com.example.companylogin

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.companylogin.EmployeesJson.employeesList

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "RememberReturnType")
@Composable
fun Employees() {
    val context = LocalContext.current
    val employeesJson = remember { employeesList(context, "employees.json") }

    Scaffold {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 110.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(employeesJson) { employee ->
                val img = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(employee.img)  // Use the img URL here
                        .crossfade(true)
                        .build()
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .height(85.dp)
                            .width(85.dp),
                    ) {
                        Image(
                            painter = img,
                            contentDescription = "Employee Image",
                            modifier = Modifier
                                .height(85.dp)
                                .width(85.dp)
                                .fillMaxWidth(),
                        )
                    }
                    Spacer(modifier = Modifier.size(15.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "${employee.title}. ${employee.name}",
                            fontWeight = FontWeight(700),
                            fontSize = 16.sp,
                            modifier = Modifier.wrapContentSize()
                        )
                        Text(
                            text = employee.position,
                            fontSize = 13.sp,
                            color = Color.Gray
                        )
                        Row(
                            modifier = Modifier
                                .padding(0.dp, 3.dp)
                                .clickable {
                                    openEmailApp(context, employee.email)
                                },
                            verticalAlignment = Alignment.CenterVertically,
                            ) {
                            Icon(
                                painter = painterResource(R.drawable.baseline_mail_24),
                                contentDescription = "email",
                                modifier = Modifier.size(17.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.size(5.dp))
                            Text(
                                text = employee.email,
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 14.sp,
                            )
                        }
                        Row(
                            modifier = Modifier
                                .padding(0.dp, 3.dp)
                                .clickable {
                                    openPhoneApp(context, employee.phone)
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.baseline_phone_24),
                                contentDescription = "phone",
                                modifier = Modifier.size(17.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.size(5.dp))
                            Text(
                                text = employee.phone,
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 14.sp,
                            )
                        }
                    }
                }
                Divider(thickness = 0.5.dp, color = Color.Gray)
            }
        }
    }
}

fun openEmailApp(context: Context, email: String) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:$email")
    }
    context.startActivity(intent)
}

fun openPhoneApp(context: Context, phone: String) {
    val formattedPhone = phone.replace("-", "").replace(" ", "")
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$formattedPhone")
    }
    context.startActivity(intent)
}

@Preview(showBackground = true)
@Composable
fun EmployeesPreview() {
    Employees()
}
