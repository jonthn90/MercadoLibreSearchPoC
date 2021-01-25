package dev.jonthn.mercadolibresearch

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

object SwissTool {

    fun hideKeyboard(activity: Activity) {
        val inputManager: InputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        try {
            inputManager.hideSoftInputFromWindow(
                activity.currentFocus!!.windowToken,
                InputMethodManager.RESULT_UNCHANGED_SHOWN
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showKeyboard(activity: Activity) {
        val inputManager: InputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        try {
            inputManager.toggleSoftInput(
                InputMethodManager.SHOW_FORCED,
                InputMethodManager.RESULT_UNCHANGED_SHOWN
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}