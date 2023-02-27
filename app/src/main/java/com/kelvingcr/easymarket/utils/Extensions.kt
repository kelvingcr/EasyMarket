package com.kelvingcr.easymarket.utils

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.text.NumberFormat
import java.time.LocalDate

import java.time.format.DateTimeFormatter
import java.util.*

fun Fragment.showToast(str: String, time: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), str, time).show()
}
fun Activity.showToast(str: String, time: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, str, time).show()
}

fun Fragment.formatCurrencyToBR(valor: Double): String {
    val formatoMoeda = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    return formatoMoeda.format(valor)
}

fun ViewHolder.formatCurrencyToBR(valor: Double): String {
    val formatoMoeda = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    return formatoMoeda.format(valor)
}

fun Fragment.getDateFormated(): String {
    val dataAtual = LocalDate.now()
    val formato = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return dataAtual.format(formato)
}
