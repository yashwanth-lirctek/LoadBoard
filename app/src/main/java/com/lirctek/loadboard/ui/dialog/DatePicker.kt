package com.lirctek.loadboard.ui.dialog

import android.app.Activity
import android.view.ContextThemeWrapper
import android.widget.CalendarView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.lirctek.loadboard.R
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

@Composable
fun CustomCalendarView(
    openCalander: Boolean,
    onDateSelected: (String, Long) -> Unit,
    onDismissOffer: () -> Unit
) {
    val picker = MaterialDatePicker.Builder.datePicker()
        .setSelection(MaterialDatePicker.todayInUtcMilliseconds()).build()

    if (openCalander) {
//        (LocalContext.current.applicationContext).let {
//            picker.show(it.applicationContext, picker.toString())
//            picker.addOnPositiveButtonClickListener {
//                onDateSelected(getTimeStampToDate(it), it)
//            }
//            picker.addOnDismissListener {
//                onDismissOffer()
//            }
//        }
    }
}

val LOGS_DATE_FORMATE_TO_SHOW = "EEE, MMM dd"
fun getTimeStampToDate(timestamp: Long) : String {
    val formatter = getDateFormatter(LOGS_DATE_FORMATE_TO_SHOW)
    val date = Date(timestamp)
    return formatter.format(date)
}

fun getDateFormatter(format: String) : SimpleDateFormat {
    val simpleDateFormat = SimpleDateFormat(format, Locale.US)
    return simpleDateFormat
}