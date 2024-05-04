package com.example.passwordmanager

data class PasswordGeneratorViewState(
    var password: String = "password",
    var characterLength: Float = 8.0f,
    var isUpperCaseSelected: Boolean = false,
    var isLowerCaseSelected: Boolean = true,
    var isNumbersSelected: Boolean = false,
    var isSymbolsSelected: Boolean = false,
    var strength: Int = 1
)
