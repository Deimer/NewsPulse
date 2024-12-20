package com.testdeymer.newspulse.features.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.testdeymer.newspulse.navigation.AppNavigation
import com.testdeymer.presentation.theme.NewsPulseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val snackbarHostState = remember { SnackbarHostState() }
            NewsPulseTheme {
                AppNavigation(snackbarHostState = snackbarHostState)
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun NewsPulsePreview() {
    val snackbarHostState = remember { SnackbarHostState() }
    NewsPulseTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
        ) {
            AppNavigation(snackbarHostState)
        }
    }
}