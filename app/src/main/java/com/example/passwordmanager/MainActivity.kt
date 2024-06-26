package com.example.passwordmanager

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.passwordmanager.composables.PasswordContainer
import com.example.passwordmanager.composables.PasswordDisplay
import com.example.passwordmanager.ui.theme.JetBrainsMono
import com.example.passwordmanager.ui.theme.PasswordManagerTheme
import com.example.passwordmanager.ui.theme.clrVeryDarkGrey
import com.example.passwordmanager.viewmodels.PasswordGeneratorViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PasswordManagerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun App() {
    val passwordGeneratorViewModel: PasswordGeneratorViewModel = viewModel()
    val state by passwordGeneratorViewModel.state.collectAsState()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(clrVeryDarkGrey)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        VaultIcon()
        PasswordDisplay(state)
        PasswordContainer(passwordGeneratorViewModel)
    }
}

@Composable
fun VaultIcon() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 0.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Spacer(modifier = Modifier.size(24.dp))
        Text (
            text = "Password Generator",
            style = TextStyle(
                fontFamily = JetBrainsMono,
                fontSize = 20.sp
            ),
            color = Color.LightGray
        )
        val context = LocalContext.current
        Image(
            modifier = Modifier
                .clickable {
                    Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()
                }
                .size(24.dp),
            painter = painterResource(id = R.drawable.img),
            contentDescription = "vault"
        )
    }
}