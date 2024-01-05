package com.example.kelineyt.dialog

import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.kelineyt.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

//비밀번호 대화상자 호출
fun Fragment.setupBottomSheetDialog(
    //이메일을 람다함수 사용해서 전송
    onSendClick: (String) -> Unit
){

    val dialog = BottomSheetDialog(requireContext(),R.style.DialogStyle)
    val view = layoutInflater.inflate(R.layout.reset_passowrd_dialog,null)
    dialog.setContentView(view)
    dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
    dialog.show()

    //email, 취소버튼,보내기버튼 호출
    val edEmail = view.findViewById<EditText>(R.id.edResetPassword)
    val buttonSend = view.findViewById<Button>(R.id.buttonSendResetPassword)
    val buttonCancel = view.findViewById<Button>(R.id.buttonCancelResetPassword)

    //buttonSend버튼 눌렀을때
    buttonSend.setOnClickListener {
        val email = edEmail.text.toString().trim()
        onSendClick(email)
        dialog.dismiss()
    }

    //buttonCancel버튼 눌렀을때
    buttonCancel.setOnClickListener {
        dialog.dismiss()    //대화상자 닫아서 숨김
    }
}