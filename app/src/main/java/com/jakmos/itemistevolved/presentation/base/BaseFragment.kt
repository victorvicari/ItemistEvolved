package com.jakmos.itemistevolved.presentation.base

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.jakmos.itemistevolved.R
import timber.log.Timber
import java.lang.Exception

open class BaseFragment : Fragment() {

    private var alertDialog: AlertDialog? = null

    fun showError(
        positiveAction: () -> Unit
    ) {
        if (null == activity) return

        val context = activity as? Context? ?: return

        val title = context.getString(R.string.oh_no)
        val message = context.getString(R.string.something_went_wrong)
        val positiveButtonTitle = context.getString(R.string.retry)
        val negativeButtonTitle = context.getString(R.string.exit)

        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        setPositiveAction(builder, positiveAction, positiveButtonTitle)
        setNegativeAction(builder, { activity?.onBackPressed() }, negativeButtonTitle)

        builder.setCancelable(false)

        alertDialog = builder.show()
    }

    private fun setPositiveAction(
        builder: AlertDialog.Builder,
        positiveAction: () -> Unit,
        buttonTitle: String
    ) {
        builder.setPositiveButton(buttonTitle) { _, _ ->
            try {
                positiveAction.invoke()
            } catch (e: Exception) {
                Timber.tag("KUBA").e("setPositiveAction $e")
            }
        }
    }

    private fun setNegativeAction(
        builder: AlertDialog.Builder,
        negativeAction: () -> Unit,
        buttonTitle: String
    ) {
        builder.setNegativeButton(buttonTitle) { _, _ ->
            try {
                negativeAction.invoke()
            } catch (e: Exception) {
                Timber.tag("KUBA").e("setNegativeAction ")
            }
        }
    }
}