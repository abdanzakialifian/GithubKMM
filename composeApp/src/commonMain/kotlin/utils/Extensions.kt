package utils

fun String?.orHyphen(): String = if (this.isNullOrEmpty()) "-" else this