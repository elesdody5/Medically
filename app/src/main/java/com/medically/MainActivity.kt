package com.medically

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.medically.nav.MedicallyNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedicallyApp()
        }
    }
}

@Composable
fun MedicallyApp() {
    MedicallyNavHost()
}

