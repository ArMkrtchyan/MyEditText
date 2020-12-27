package com.example.edittext

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.appcompat.widget.AppCompatEditText

fun AppCompatEditText.showPassword(){
    transformationMethod = HideReturnsTransformationMethod.getInstance()
    setSelection(text.toString().length)
}

fun AppCompatEditText.hidePassword(){
    transformationMethod = PasswordTransformationMethod.getInstance()
    setSelection(text.toString().length)
}