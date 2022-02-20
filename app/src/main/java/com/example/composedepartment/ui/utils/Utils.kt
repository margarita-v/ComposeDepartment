package com.example.composedepartment.ui.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.PluralsRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.composedepartment.R

/** Get plural string resource based on given value */
@Composable
fun pluralResource(
    @PluralsRes resId: Int,
    quantity: Int,
    vararg formatArgs: Any? = emptyArray()
): String {
    return LocalContext.current.resources
        .getQuantityString(resId, quantity, *formatArgs)
}

/** Open system phone app */
fun goToPhoneSystemApp(context: Context, phone: String) {
    val rawPhone = StringBuilder(phone.filter(Char::isDigit))
        .apply {
            if (startsWith("7")) {
                insert(0, "+")
            }
        }
    val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$rawPhone"))
        .apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK }
    val intent = Intent.createChooser(dialIntent, context.getString(R.string.dial_phone))
    context.startActivity(intent)
}