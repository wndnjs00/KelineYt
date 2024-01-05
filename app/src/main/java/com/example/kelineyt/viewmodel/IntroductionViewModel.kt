package com.example.kelineyt.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kelineyt.R
import com.example.kelineyt.util.Constants.INTRODUCTION_KEY
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntroductionViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _navigate = MutableStateFlow(0)
     val navigate: StateFlow<Int> = _navigate

    companion object{
        const val SHOPPING_ACTIVITY = 23
        const val ACCOUNT_OPTIONS_FRAGMENT = R.id.action_introductionFragment_to_accountOptionsFragment
    }


    //로그인을 하고 앱을 나갔다 들어오면, ShoppingActivity로 바로 이동하게(IntroductionFragment와 연결)
    //버튼이 클릭되었는지 or 사용자가 이미 시작했는지 체크
    init {
        val isButtonClicked = sharedPreferences.getBoolean(INTRODUCTION_KEY,false)
        //사용자가 이미 로그인했는지 체크
        val user = firebaseAuth.currentUser

        if (user != null){
            //SHOPPING_ACTIVITY으로 내보내기
            viewModelScope.launch {
                _navigate.emit(SHOPPING_ACTIVITY)
            }

        }else if (isButtonClicked){
            //ACCOUNT_OPTIONS_FRAGMENT으로 내보내기
            viewModelScope.launch {
                _navigate.emit(ACCOUNT_OPTIONS_FRAGMENT)
            }

        }else{
            //아무활동도 하지않음
            Unit
        }
    }

    //기본설정 변경 함수
    fun startButtonClick(){
        sharedPreferences.edit().putBoolean(INTRODUCTION_KEY,true).apply()
    }
}