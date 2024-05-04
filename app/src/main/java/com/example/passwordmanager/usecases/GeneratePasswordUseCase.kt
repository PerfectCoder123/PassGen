package com.example.passwordmanager.usecases

import com.example.passwordmanager.PasswordGeneratorViewState

class GeneratePasswordUseCase {

     fun execute(state: PasswordGeneratorViewState): String {
        val characterLength = state.characterLength.toInt()
        val selectedCharacters = mutableListOf<Char>()

        if (state.isLowerCaseSelected) {
            selectedCharacters.addAll('a'..'z')
        }
        if (state.isUpperCaseSelected) {
            selectedCharacters.addAll('A'..'Z')
        }
        if (state.isNumbersSelected) {
            selectedCharacters.addAll('0'..'9')
        }
        if (state.isSymbolsSelected) {
            selectedCharacters.addAll("!@#$%^&*()_+{}[];:,.<>?".toList())
        }
        if(selectedCharacters.size == 0)selectedCharacters.add(' ')
        return buildString {
            repeat(characterLength) {
                append(selectedCharacters.random())
            }
        }
    }
}
