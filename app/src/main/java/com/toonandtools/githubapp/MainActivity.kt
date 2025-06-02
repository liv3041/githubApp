//package com.toonandtools.githubapp
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import com.toonandtools.githubapp.ui.theme.GithubAppTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            GithubAppTheme {
//                SplashScreen()
//            }
//        }
//    }
//}
//
//@Composable
//fun SplashScreen() {
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colorScheme.primary
//    ) {
//        Box(
//            contentAlignment = Alignment.Center,
//            modifier = Modifier.fillMaxSize()
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.logo), // Replace with your actual image name
//                contentDescription = "Splash Logo",
//                modifier = Modifier
//                    .width(200.dp)
//                    .height(200.dp)
//            )
//        }
//    }
//}
package com.toonandtools.githubapp

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.toonandtools.githubapp.ui.theme.GithubAppTheme
import kotlinx.coroutines.delay
import com.toonandtools.auth.AuthActivity // ← Import from auth module

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
//            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
//                requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 101)
//            }
//        }

        enableEdgeToEdge()

        setContent {
            GithubAppTheme {
                var navigate by remember { mutableStateOf(false) }

                LaunchedEffect(Unit) {
                    delay(2000)
                    navigate = true
                }

                if (navigate) {
                    startActivity(AuthActivity.createIntent(this@MainActivity))
                    finish() // optional: remove MainActivity from back stack
                } else {
                    SplashScreen()
                }
            }
        }
    }
}

@Composable
fun SplashScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primary
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Splash Logo",
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
            )
        }
    }
}


