package com.example.companylogin

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.companylogin.LocationJson.officeList
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Offices() {
    val context = LocalContext.current
    val officeJson = remember { officeList(context, "location.json") }

    // Default to USA center if no data or for initialization
    val defaultPosition = LatLng(38.7946, -106.5348)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(defaultPosition, 4f) // Zoom level for the entire USA
    }

    Scaffold {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(isBuildingEnabled = false)
        ) {
            officeJson.forEach { office ->
                Marker(
                    state = MarkerState(LatLng(office.latitude, office.longitude)),
                    title = office.name
                )
            }
        }
    }
}

@Preview
@Composable
fun OfficesPreview() {
    Offices()
}
