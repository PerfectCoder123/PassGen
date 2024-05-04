package com.example.passwordmanager.usecases

import com.example.passwordmanager.PasswordGeneratorViewState

class UpdatePasswordStrengthUseCase {
     fun execute(state : PasswordGeneratorViewState): Int {
        var strength = 0
        if(state.characterLength >= 8 && (state.isLowerCaseSelected || state.isUpperCaseSelected)) strength++
        if(state.isSymbolsSelected) strength++
        if(state.isNumbersSelected) strength++
        return strength
    }
}