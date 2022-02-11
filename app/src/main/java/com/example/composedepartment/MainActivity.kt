package com.example.composedepartment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composedepartment.ui.theme.ComposeDepartmentTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDepartmentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation(rememberNavController())
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun Splash(onFinished: () -> Unit) {
    //todo custom layout
    Greeting(name = "Splash")
    LaunchedEffect(key1 = Unit, block = {
        delay(1000L)
        onFinished()
    })
}

@Composable
fun Main() {
    Greeting(name = "Main")
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = "Splash") {
        composable("Splash") {
            Splash(onFinished = { navController.navigate("Main") })
        }
        composable("Main") {
            Main()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeDepartmentTheme {
        Greeting("Android")
    }
}