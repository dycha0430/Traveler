package com.example.traveler.util

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.EditText
import java.text.DecimalFormat

class NumberTextWatcher(et: EditText) : TextWatcher {
    private val decimalFormat = DecimalFormat("#,###")
    private val et: EditText = et
    private var result = ""

    override fun afterTextChanged(s: Editable) {
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (!TextUtils.isEmpty(s.toString()) && s.toString() != result){
            result = decimalFormat.format(s.toString().replace(",", "").toDouble());
            et.setText(result)
            et.setSelection(result.length)
        }
    }
}