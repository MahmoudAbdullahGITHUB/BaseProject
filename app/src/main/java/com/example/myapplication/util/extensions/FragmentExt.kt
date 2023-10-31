package com.smart.smartapp.util.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.smart.smartapp.ui.dialogs.LocalNotificationsDialog
import com.smart.smartapp.ui.dialogs.LocalNotificationsType
import com.smart.smartapp.util.LocalNotificationType

fun Fragment.getNavigationResult(navController: NavController, key: String = "result") =
    navController.currentBackStackEntry?.savedStateHandle?.getLiveData<Any>(key)

fun Fragment.setNavigationResult(
    navController: NavController,
    result: Any,
    key: String = "result",
) = navController.previousBackStackEntry?.savedStateHandle?.set(key, result)


fun Fragment.showLocalNotification(type: LocalNotificationType, message: CharSequence) {
    this.activity?.let { it as? AppCompatActivity }?.let {
        when (type) {
            LocalNotificationType.SUCCESS -> {
                LocalNotificationsDialog(
                    LocalNotificationsType.SUCCESS,
                    message.toString()
                ).show(it.supportFragmentManager, "LocalNotifications")
            }
            LocalNotificationType.INFO -> {
                LocalNotificationsDialog(
                    LocalNotificationsType.INFO,
                    message.toString()
                ).show(it.supportFragmentManager, "LocalNotifications")
            }
            LocalNotificationType.WARNING -> {
                LocalNotificationsDialog(
                    LocalNotificationsType.WARNING,
                    message.toString()
                ).show(it.supportFragmentManager, "LocalNotifications")
            }
            LocalNotificationType.ERROR -> {
                LocalNotificationsDialog(
                    LocalNotificationsType.ERROR,
                    message.toString()
                ).show(it.supportFragmentManager, "LocalNotifications")
            }
        }
    }
}