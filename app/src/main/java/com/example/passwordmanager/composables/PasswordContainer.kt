package com.example.passwordmanager.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passwordmanager.PasswordGeneratorViewState
import com.example.passwordmanager.R
import com.example.passwordmanager.ui.theme.JetBrainsMono
import com.example.passwordmanager.ui.theme.clrAlmostWhite
import com.example.passwordmanager.ui.theme.clrDarkGrey
import com.example.passwordmanager.ui.theme.clrGrey
import com.example.passwordmanager.ui.theme.clrNeonGreen
import com.example.passwordmanager.ui.theme.clrVeryDarkGrey
import com.example.passwordmanager.ui.theme.clrYellow
import com.example.passwordmanager.viewmodels.PasswordGeneratorViewModel

@Composable
fun PasswordContainer(passwordGeneratorViewModel : PasswordGeneratorViewModel) {
    val state by passwordGeneratorViewModel.state.collectAsState()
    Column ( modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp, 0.dp)
        .background(clrGrey)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 20.dp, 20.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text (text = "Character Length",
                style = TextStyle(
                    fontFamily = JetBrainsMono,
                    fontSize = 16.sp
                ),
                color = clrAlmostWhite
            )
            Text ( modifier = Modifier,
                style = TextStyle(
                    fontFamily = JetBrainsMono,
                    fontSize = 18.sp
                ),
                text = "${state.characterLength.toInt()}",
                color = clrNeonGreen
            )
        }
        PasswordSlider(state.characterLength, onSliderPositionChange = { newCount ->
                passwordGeneratorViewModel.setCharacterLength(newCount)
                passwordGeneratorViewModel.updateStrength()

        } )
        PasswordType(state, onCheckBoxTicked = {checked, checkboxNumber -> when (checkboxNumber) {
            0 -> {
                passwordGeneratorViewModel.setIsUpperCaseSelected(checked)
                passwordGeneratorViewModel.updateStrength()
            }
            1 -> {
                passwordGeneratorViewModel.setIsLowerCaseSelected(checked)
                passwordGeneratorViewModel.updateStrength()
            }
            2 -> {
                passwordGeneratorViewModel.setIsNumbersSelected(checked)
                passwordGeneratorViewModel.updateStrength()
            }
            else -> {
                passwordGeneratorViewModel.setIsSymbolsSelected(checked)
                passwordGeneratorViewModel.updateStrength()
            }
        }})
        PasswordStrength(state.strength)
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 20.dp, 10.dp, 20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = clrNeonGreen
            ),
            shape = RoundedCornerShape(0.dp),
            onClick = {
                passwordGeneratorViewModel.generatePassword()
        }) {
            Text(
                modifier = Modifier
                    .padding(10.dp, 10.dp),
                text = "Generate",
                color = clrVeryDarkGrey,
                style = TextStyle(
                    fontFamily = JetBrainsMono,
                    fontSize = 18.sp
                )
            )
            Image(
                modifier = Modifier
                    .size(18.dp),
                painter = painterResource(id = R.drawable.right_arrow),
                contentDescription = "generate"
            )
        }
    }
}
@Composable
fun PasswordSlider(sliderPosition : Float, onSliderPositionChange :(Float) -> Unit) {
    Slider(
        modifier = Modifier.padding(20.dp, 0.dp),
        value = sliderPosition,
        onValueChange = { onSliderPositionChange(it) },
        colors = SliderDefaults.colors(
            thumbColor = Color.White,
            activeTrackColor = clrNeonGreen,
            inactiveTrackColor = clrDarkGrey,
        ),
        valueRange = 5f..20f
    )
}

@Composable
fun PasswordType(state: PasswordGeneratorViewState, onCheckBoxTicked : (Boolean, Int) -> Unit) {

    Column {
        Row (
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = state.isUpperCaseSelected,
                onCheckedChange = { isChecked -> onCheckBoxTicked(isChecked, 0) },
                colors = CheckboxDefaults.colors(
                    checkedColor = clrNeonGreen,
                    checkmarkColor = clrGrey
                )
            )

            Text (
                text = "Include Uppercase Letters",
                style = TextStyle(
                    fontFamily = JetBrainsMono,
                    fontSize = 16.sp
                ),
                color = clrAlmostWhite
            )
        }

        Row (
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = state.isLowerCaseSelected,
                onCheckedChange = { isChecked -> onCheckBoxTicked(isChecked, 1) },
                colors = CheckboxDefaults.colors(
                    checkedColor = clrNeonGreen,
                    checkmarkColor = clrGrey
                )
            )
            Text (
                text = "Include Lowercase Letters",
                style = TextStyle(
                    fontFamily = JetBrainsMono,
                    fontSize = 16.sp
                ),
                color = clrAlmostWhite
            )
        }

        Row (
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = state.isNumbersSelected,
                onCheckedChange = { isChecked -> onCheckBoxTicked(isChecked, 2) },
                colors = CheckboxDefaults.colors(
                    checkedColor = clrNeonGreen,
                    checkmarkColor = clrGrey
                )
            )
            Text (
                text = "Include Numbers",
                style = TextStyle(
                    fontFamily = JetBrainsMono,
                    fontSize = 16.sp
                ),
                color = clrAlmostWhite
            )
        }

        Row (
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = state.isSymbolsSelected,
                onCheckedChange = { isChecked -> onCheckBoxTicked(isChecked, 3) },
                colors = CheckboxDefaults.colors(
                    checkedColor = clrNeonGreen,
                    checkmarkColor = clrGrey
                )
            )
            Text (
                text = "Include Symbols",
                style = TextStyle(
                    fontFamily = JetBrainsMono,
                    fontSize = 16.sp
                ),
                color = clrAlmostWhite
            )
        }
    }
}

@Composable
fun PasswordStrength(strength: Int) {
     Row (
         modifier = Modifier
             .fillMaxWidth()
             .padding(10.dp)
             .background(clrVeryDarkGrey),
         verticalAlignment = Alignment.CenterVertically,
         horizontalArrangement = Arrangement.SpaceBetween
     ) {
         Text(
             modifier = Modifier
                 .padding(10.dp,20.dp),
             text = "Strength",
             color = Color.LightGray,
             style = TextStyle(
                 fontFamily = JetBrainsMono,
                 fontSize = 16.sp
             )
         )
         Row (
             modifier = Modifier
                 .padding(10.dp),
             verticalAlignment = Alignment.CenterVertically
         ) {
             Text(
                 modifier = Modifier
                     .padding(4.dp, 0.dp),
                 text = when (strength) {
                     0,1 -> "Weak"
                     2 -> "Medium"
                     else -> "Strong"
                 },
                 color = clrAlmostWhite,
                 style = TextStyle(
                     fontFamily = JetBrainsMono,
                     fontSize = 16.sp
                 )
             )
             Box(
                 modifier = Modifier
                     .padding(4.dp, 0.dp)
                     .width(8.dp)
                     .height(30.dp)
                     .background(if (strength >= 1) clrYellow else Color.Transparent)
                     .border(if (strength < 1) 1.dp else 0.dp, Color.White)
             )
             Box(
                 modifier = Modifier
                     .padding(4.dp, 0.dp)
                     .width(8.dp)
                     .height(30.dp)
                     .background(if (strength >= 2) clrYellow else Color.Transparent)
                     .border(if (strength < 2) 1.dp else 0.dp, Color.White)

             )
             Box(
                 modifier = Modifier
                     .padding(4.dp, 0.dp)
                     .width(8.dp)
                     .height(30.dp)
                     .background(if (strength >= 3) clrYellow else Color.Transparent)
                     .border(if (strength < 3) 1.dp else 0.dp, Color.White)

             )
         }
     }
}