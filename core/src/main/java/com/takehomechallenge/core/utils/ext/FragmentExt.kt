package com.takehomechallenge.core.utils.ext

import android.app.AlertDialog
import androidx.fragment.app.Fragment

fun Fragment.showConfirmDialog(
    title: String,
    message: String,
    positiveButtonText: String,
    onPositiveClick: () -> Unit,
    negativeButtonText: String,
) {
    AlertDialog.Builder(requireContext()).apply {
        setTitle(title)
        setMessage(message)
        setNegativeButton(negativeButtonText) { p0, _ ->
            p0.dismiss()
        }
        setPositiveButton(positiveButtonText) { _, _ ->
            onPositiveClick()
        }
    }.create().show()
}