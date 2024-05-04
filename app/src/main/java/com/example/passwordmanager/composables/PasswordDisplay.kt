package com.example.passwordmanager.composables

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passwordmanager.PasswordGeneratorViewState
import com.example.passwordmanager.R
import com.example.passwordmanager.ui.theme.JetBrainsMono
import com.example.passwordmanager.ui.theme.JetBrainsMonoBold
import com.example.passwordmanager.ui.theme.clrAlmostWhite
import com.example.passwordmanager.ui.theme.clrGrey

@Composable
fun PasswordDisplay(passwordGeneratorViewState: PasswordGeneratorViewState) {

        Row ( modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp,0.dp, 20.dp,0.dp)
            .background(clrGrey),

            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text ( modifier = Modifier
                .padding(15.dp,20.dp),
                text = passwordGeneratorViewState.password,
                style = TextStyle(
                    fontFamily = JetBrainsMonoBold,
                    fontSize = 20.sp
                ),
                color = clrAlmostWhite
            )
            val context = LocalContext.current
            Image ( modifier = Modifier
                .clickable {
                    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    clipboard.setPrimaryClip(ClipData.newPlainText("Password", passwordGeneratorViewState.password))
                    Toast.makeText(context, "Password copied to clipboard", Toast.LENGTH_SHORT).show()
                }
                .padding(0.dp,0.dp,15.dp,0.dp)
                .size(24.dp),
                painter = painterResource(id = R.drawable.copy_green),
                contentDescription = "copy"
            )
        }
}