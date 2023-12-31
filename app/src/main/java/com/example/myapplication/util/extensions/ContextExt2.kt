package com.smart.smartapp.util.extensions

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.location.LocationManager
import android.os.Build
import android.provider.Settings
import android.text.InputFilter
import android.text.InputType
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView
import com.smart.smartapp.R
import com.smart.smartapp.ui.dialogs.ConfirmInfoDialog
import com.smart.smartapp.ui.dialogs.LocalNotificationsDialog
import com.smart.smartapp.ui.dialogs.LocalNotificationsType
import com.smart.smartapp.util.LocalNotificationType
import java.util.*

fun Context.getApplicationVersionNumber(): String {
    return try {
        val pInfo: PackageInfo = this.packageManager.getPackageInfo(this.packageName, 0)
        pInfo.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        e.message.toString()
    }
}

fun Context.showLocalNotification(type: LocalNotificationType, message: CharSequence) {
    when (type) {
        LocalNotificationType.SUCCESS -> {
            LocalNotificationsDialog(
                LocalNotificationsType.SUCCESS,
                message.toString()
            ).show((this as AppCompatActivity).supportFragmentManager, "LocalNotifications")
        }
        LocalNotificationType.INFO -> {
            LocalNotificationsDialog(
                LocalNotificationsType.INFO,
                message.toString()
            ).show((this as AppCompatActivity).supportFragmentManager, "LocalNotifications")
        }
        LocalNotificationType.WARNING -> {
            LocalNotificationsDialog(
                LocalNotificationsType.WARNING,
                message.toString()
            ).show((this as AppCompatActivity).supportFragmentManager, "LocalNotifications")
        }
        LocalNotificationType.ERROR -> {
            LocalNotificationsDialog(
                LocalNotificationsType.ERROR,
                message.toString()
            ).show((this as AppCompatActivity).supportFragmentManager, "LocalNotifications")
        }
    }
}


fun Context.createMaterialCard(): MaterialCardView {
    return MaterialCardView(this).apply {
        useCompatPadding = true
        radius = resources.getDimension(R.dimen._4sdp)
        elevation = resources.getDimension(R.dimen._2sdp)
    }
}

fun Context.createSpinner(items: List<Any>): Spinner {
    return Spinner(this).apply {
        adapter = ArrayAdapter(
            this@createSpinner,
            android.R.layout.simple_spinner_dropdown_item,
            items
        )
        setPadding(32, 0, 32, 0)
    }
}


fun Context.getStringByLocale(@StringRes stringRes: Int, locale: Locale): String {
    val configuration = Configuration(resources.configuration)
    configuration.setLocale(locale)
    return createConfigurationContext(configuration).resources.getString(stringRes)
}

@RequiresApi(Build.VERSION_CODES.M)
fun Context.checkGPSEnabledAndShowRationale(): Boolean {

    var manager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val isEnabled = manager.isProviderEnabled(LocationManager.GPS_PROVIDER)

    if (isEnabled.not()){
        ConfirmInfoDialog(
            context = this,
            info = getString(R.string.cannot_access_location),
            positiveText = getString(R.string.open),
            showPositiveButton = true,
            positiveClickAction = {
                startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            },
            negativeText = getString(R.string.cancel),
            showNegativeButton = true,
            negativeClickAction = {
            },
            isCancelable = false
        ).show()
    }

    return isEnabled
}

