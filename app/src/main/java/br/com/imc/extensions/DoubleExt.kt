package br.com.imc.extensions

fun Double.format(digits: Int) = java.lang.String.format("%.${digits}f", this)
