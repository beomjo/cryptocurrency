package k.bs.cryptocurrency.common.util

fun String.round(decimalPoint: Int): String = String.format("%.${decimalPoint}f", this.toDouble())
