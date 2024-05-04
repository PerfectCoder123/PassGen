package com.example.passwordmanager.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.PasswordGeneratorViewState
import com.example.passwordmanager.usecases.GeneratePasswordUseCase
import com.example.passwordmanager.usecases.UpdatePasswordStrengthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PasswordGeneratorViewModel : ViewModel() {
    private val _state = MutableStateFlow(PasswordGeneratorViewState())
    val state: StateFlow<PasswordGeneratorViewState> = _state
    private val generatePasswordUseCase = GeneratePasswordUseCase()
    private val updatePasswordStrengthUseCase = UpdatePasswordStrengthUseCase()

    private fun setPasswordField(value: String) {
        _state.value = _state.value.copy(password = value)
    }

    fun setCharacterLength(value: Float) {
        _state.value = _state.value.copy(characterLength = value)
    }

    fun setIsUpperCaseSelected(value: Boolean) {
        _state.value = _state.value.copy(isUpperCaseSelected = value)
    }

    fun setIsLowerCaseSelected(value: Boolean) {
        _state.value = _state.value.copy(isLowerCaseSelected = value)
    }

    fun setIsNumbersSelected(value: Boolean) {
        _state.value = _state.value.copy(isNumbersSelected = value)
    }

    fun setIsSymbolsSelected(value: Boolean) {
        _state.value = _state.value.copy(isSymbolsSelected = value)
    }

    fun updateStrength() {
        val strength = updatePasswordStrengthUseCase.execute(state.value)
        _state.value = _state.value.copy(strength = strength)
    }

    fun generatePassword(){
        viewModelScope.launch{
           setPasswordField(generatePasswordUseCase.execute(state.value))
        }
    }
}
