package dev.vladmakarenko.financialPlanning.core.utils

import android.text.Editable

fun String.toEditable(): Editable = Editable.Factory().newEditable(this)