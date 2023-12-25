package com.example.kelineyt.util


//회원가입 유효성 검사
sealed class RegisterValidation(){
    //회원가입 옵션
    object Success: RegisterValidation()
    data class Failed(val message: String): RegisterValidation()
}

data class RegisterFieldsState(
    val email: RegisterValidation,  //email 유효성검사
    val password: RegisterValidation    //password 유효성검사
)
