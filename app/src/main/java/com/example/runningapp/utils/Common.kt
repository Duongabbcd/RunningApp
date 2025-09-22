package com.example.runningapp.utils

import android.app.Activity
import android.app.AlertDialog
import android.app.Application.MODE_PRIVATE
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import android.widget.Toast
import java.util.Locale
import kotlin.also
import kotlin.collections.joinToString
import kotlin.collections.map
import kotlin.collections.mapNotNull
import kotlin.let
import kotlin.runCatching
import kotlin.text.isNotEmpty
import kotlin.text.split
import kotlin.text.toInt
import kotlin.text.toIntOrNull
import kotlin.to
import kotlin.toString
import com.example.runningapp.R

object Common {
    fun View.visible() {
        visibility = View.VISIBLE
    }

    fun View.inVisible() {
        visibility = View.INVISIBLE
    }

    fun View.gone() {
        visibility = View.GONE
    }

    fun Context.toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun getLang(mContext: Context): String {
        val preferences =
            mContext.getSharedPreferences(mContext.packageName, MODE_PRIVATE)
        return preferences.getString("KEY_LANG", "English") ?: "English"
    }

    fun setLang(context: Context, open: String?) {
        val preferences =
            context.getSharedPreferences(context.packageName, MODE_PRIVATE)
        preferences.edit().putString("KEY_LANG", open).apply()
    }

    fun getPreLanguageflag(mContext: Context): Int {
        val preferences = mContext.getSharedPreferences(mContext.packageName, MODE_PRIVATE)
        return preferences.getInt("KEY_FLAG", R.drawable.english)
    }

    fun setPreLanguageflag(context: Context, flag: Int) {
        val preferences = context.getSharedPreferences(context.packageName, MODE_PRIVATE)
        preferences.edit().putInt("KEY_FLAG", flag).apply()
    }

    fun getPreLanguage(mContext: Context): String {
        val preferences = mContext.getSharedPreferences(mContext.packageName, MODE_PRIVATE)
        return preferences.getString("KEY_LANGUAGE", "en").toString()
    }

    fun setPreLanguage(context: Context, language: String?) {
        if (TextUtils.isEmpty(language)) return
        val preferences = context.getSharedPreferences(context.packageName, MODE_PRIVATE)
        preferences.edit().putString("KEY_LANGUAGE", language).apply()
    }


    fun setLocale(context: Context, lang: String?) {
        val myLocale = lang?.let { Locale(it) }
        val res = context.resources
        val dm = res.displayMetrics
        val conf = res.configuration
        conf.setLocale(myLocale)
        res.updateConfiguration(conf, dm)
    }
    

    fun Context.openUrl(url: String) {
        runCatching {
            Intent(Intent.ACTION_VIEW, Uri.parse(url)).also {
                this.startActivity(it)
            }
        }
    }

    fun Context.openPrivacy() {
        openUrl("https://docs.google.com/document/d/1QXF3ntK3PG9uxOALSgkoChrpN80-hx6PZsXDNVRQzig/edit?pli=1&tab=t.0")
    }

    // Handle case where no email app is available
//    fun Context.composeEmail(recipient: String, subject: String) {
//
//        val emailIntent = Intent(Intent.ACTION_SENDTO)
//        Intent.setData = Uri.parse("mailto:")
//        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
//        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
//
//        try {
//            this.startActivity(Intent.createChooser(emailIntent, "Send Email"))
//        } catch (e: ActivityNotFoundException) {
//          
//        }
//    }

    fun Context.rateApp() {
        val applicationID = this.packageName
        val playStoreUri = Uri.parse("market://details?id=$applicationID")

        val rateIntent = Intent(Intent.ACTION_VIEW, playStoreUri)
        rateIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)

        try {
            this.startActivity(rateIntent)
        } catch (e: ActivityNotFoundException) {
            val webPlayStoreUri =
                Uri.parse("https://play.google.com/store/apps/details?id=$applicationID")
            val webRateIntent = Intent(Intent.ACTION_VIEW, webPlayStoreUri)
            this.startActivity(webRateIntent)
        }
    }


    fun getCountOpenApp(mContext: Context): Int {
        val preferences = mContext.getSharedPreferences(
            mContext.packageName,
            MODE_PRIVATE
        )
        return preferences.getInt("KEY_CountOpenApp", 0)
    }

    fun setCountOpenApp(context: Context, flag: Int) {
        val preferences = context.getSharedPreferences(
            context.packageName,
            MODE_PRIVATE
        )
        preferences.edit().putInt("KEY_CountOpenApp", flag).apply()
    }

    fun setAllFavouriteWallpaper(context: Context, list: List<Int> = emptyList()) {
        val preferences = context.getSharedPreferences(
            context.packageName,
            MODE_PRIVATE
        )
        preferences.edit().putString("KEY_Fav_Wall", list.joinToString(",")).apply()
    }


    fun setAllFavouriteGenres(context: Context, list: List<Int> = emptyList()) {
        val preferences = context.getSharedPreferences(
            context.packageName,
            MODE_PRIVATE
        )
        preferences.edit().putString("KEY_Fav_Gen", list.joinToString(",")).apply()
    }


    fun getAllFavouriteGenres(mContext: Context): List<Int> {
        val preferences = mContext.getSharedPreferences(
            mContext.packageName,
            MODE_PRIVATE
        )
        val savedList = preferences.getString("KEY_Fav_Gen", null)
        return savedList?.split(",")?.mapNotNull { it.toIntOrNull() } ?: emptyList()
    }

    fun getAllFavouriteWallpaper(mContext: Context): List<Int?> {
        val preferences = mContext.getSharedPreferences(
            mContext.packageName,
            MODE_PRIVATE
        )
        val savedList = preferences.getString("KEY_Fav_Wall", null)
        return savedList?.split(",")?.mapNotNull { it.toIntOrNull() } ?: listOf()
    }

    fun getAllFreeRingtones(mContext: Context): List<String> {
        val preferences = mContext.getSharedPreferences(
            mContext.packageName,
            MODE_PRIVATE
        )
        val savedList = preferences.getString("KEY_Free_Ringtones", null)
        return savedList?.split(",")?.mapNotNull { it } ?: listOf()
    }

    fun setAllFreeRingtones(context: Context, list: List<String> = emptyList()) {
        val preferences = context.getSharedPreferences(
            context.packageName,
            MODE_PRIVATE
        )
        preferences.edit().putString("KEY_Free_Ringtones", list.joinToString(",")).apply()
    }

    fun getAllFreeWallpapers(mContext: Context): List<Int> {
        val preferences = mContext.getSharedPreferences(
            mContext.packageName,
            MODE_PRIVATE
        )
        val savedList = preferences.getString("KEY_Free_Wallpapers", null)
        return savedList?.split(",")?.mapNotNull { it.toIntOrNull() } ?: listOf()
    }

    fun setAllFreeWallpapers(context: Context, list: List<Int> = emptyList()) {
        val preferences = context.getSharedPreferences(
            context.packageName,
            MODE_PRIVATE
        )
        preferences.edit().putString("KEY_Free_Wallpapers", list.joinToString(",")).apply()
    }

    fun getAllNewRingtones(mContext: Context): List<Int> {
        val preferences = mContext.getSharedPreferences(
            mContext.packageName,
            MODE_PRIVATE
        )
        val savedList = preferences.getString("KEY_Fixed_New", null)
        return savedList?.split(",")?.mapNotNull { it.toIntOrNull() } ?: listOf()
    }

    fun setAllNewRingtones(context: Context, list: List<Int> = emptyList()) {
        val preferences = context.getSharedPreferences(
            context.packageName,
            MODE_PRIVATE
        )
        preferences.edit().putString("KEY_Fixed_New", list.joinToString(",")).apply()
    }

    fun getAllTrendingRingtones(mContext: Context): List<Int> {
        val preferences = mContext.getSharedPreferences(
            mContext.packageName,
            MODE_PRIVATE
        )
        val savedList = preferences.getString("KEY_Fixed_Trend", null)
        return savedList?.split(",")?.mapNotNull { it.toIntOrNull() } ?: listOf()
    }

    fun setAllWeeklyTrendingRingtones(context: Context, list: List<Int> = emptyList()) {
        val preferences = context.getSharedPreferences(
            context.packageName,
            MODE_PRIVATE
        )
        preferences.edit().putString("KEY_Fixed_Trend", list.joinToString(",")).apply()
    }

    fun getAllEditorChoices(mContext: Context): List<Int> {
        val preferences = mContext.getSharedPreferences(
            mContext.packageName,
            MODE_PRIVATE
        )
        val savedList = preferences.getString("KEY_Fixed_Choice", null)
        return savedList?.split(",")?.mapNotNull { it.toIntOrNull() } ?: listOf()
    }

    fun setAllEditorChoices(context: Context, list: List<Int> = emptyList()) {
        val preferences = context.getSharedPreferences(
            context.packageName,
            MODE_PRIVATE
        )
        preferences.edit().putString("KEY_Fixed_Choice", list.joinToString(",")).apply()
    }

    fun setTheme(context: Context, selectedTheme: Int) {
        val preferences = context.getSharedPreferences(
            context.packageName,
            MODE_PRIVATE
        )
        preferences.edit().putInt("KEY_UsedTheme", selectedTheme).apply()
    }


    fun showDialogGoToSetting(context: Context, onClickListener: (Boolean) -> Unit) {
        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setTitle(R.string.title_grant_Permission)
        alertDialog.setMessage(context.getString(R.string.message_grant_Permission))
        alertDialog.setCancelable(true)
        alertDialog.setButton(
            DialogInterface.BUTTON_POSITIVE, context.getString(R.string.goto_setting)
        ) { _: DialogInterface?, _: Int ->
            onClickListener(true)
            alertDialog.dismiss()
        }

        // Negative button: Cancel
        alertDialog.setButton(
            DialogInterface.BUTTON_NEGATIVE, context.getString(R.string.cancel)
        ) { _: DialogInterface?, _: Int ->
            onClickListener(false)
            alertDialog.dismiss()
        }

        // Handle cancel (e.g., tapped outside or pressed back)
        alertDialog.setOnCancelListener {
            onClickListener(false)
        }

        alertDialog.show ()

        // Set background to white
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))

        // Set positive button text color to black
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(Color.BLACK)
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.setTextColor(Color.BLACK)
        // Set title and message text color to black
        val titleId = context.resources.getIdentifier("alertTitle", "id", "android")
        val messageId = android.R.id.message

        alertDialog.findViewById<TextView>(titleId)?.setTextColor(Color.BLACK)
        alertDialog.findViewById<TextView>(messageId)?.setTextColor(Color.BLACK)
    }

    fun getDeviceId(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

    fun generateJwt(deviceId: String, secret: String): String {
        val algorithm = com.auth0.jwt.algorithms.Algorithm.HMAC256(secret)
        val data = mapOf("client_id" to deviceId, "type" to 1)
        return com.auth0.jwt.JWTCreator.Builder.sign(algorithm)
    }

    fun setSortOrder(context: Context, sortOrder: String)  {
        val preferences = context.getSharedPreferences(context.packageName, MODE_PRIVATE)
       preferences.edit().putString("KEY_SortOrder", sortOrder).apply()
    }

    fun getSortOrder(context: Context) : String {
        val preferences = context.getSharedPreferences(context.packageName, MODE_PRIVATE)
        return preferences.getString("KEY_SortOrder", "name+asc").toString()
    }

    fun setSortWppOrder(context: Context, sortOrder: String) {
        val preferences = context.getSharedPreferences(context.packageName, MODE_PRIVATE)
        preferences.edit().putString("KEY_Wpp_SortOrder", sortOrder).apply()
    }

    fun setNotificationEnable(context: Context, isEnable: Boolean) {
        val preferences = context.getSharedPreferences(context.packageName, MODE_PRIVATE)
        preferences.edit().putBoolean("KEY_NOTIF_ENABLE", isEnable).apply()
    }

    private val isTiramisuOrAbove by lazy {
        Build.VERSION.SDK_INT compareTo Build.VERSION_CODES.TIRAMISU
    }

    fun getNotificationEnable(context: Context): Boolean {
        val preferences = context.getSharedPreferences(context.packageName, MODE_PRIVATE)
        val defaultValue = isTiramisuOrAbove
        return preferences.getBoolean("KEY_NOTIF_ENABLE", defaultValue)
    }


    fun setFirstUse(context: Context, isFistUse: Boolean) {
        val preferences = context.getSharedPreferences(context.packageName, MODE_PRIVATE)
        preferences.edit().putBoolean("KEY_IS_FIRST_USE", isFistUse).apply()
    }

    fun getFirstUse(context: Context): Boolean {
        val preferences = context.getSharedPreferences(context.packageName, MODE_PRIVATE)
        return preferences.getBoolean("KEY_IS_FIRST_USE", false)
    }


    fun setFavRingtone(context: Context, favRingtones: List<Int>) {
        val prefs = context.getSharedPreferences(context.packageName, MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString("Key_FavRingtone", favRingtones.joinToString(","))
        editor.apply()
    }


    fun getFavRingtone(context: Context): List<Int> {
        val preferences = context.getSharedPreferences(context.packageName, MODE_PRIVATE)
        val origin = preferences.getString("Key_FavRingtone", "") ?: ""
        return if (origin.isNotEmpty()) {
            origin.split(",").map { it.toInt() }
        } else {
            emptyList()
        }
    }

    fun setFavSingleWpp(context: Context, favSingleWpp: List<Int>) {
        val prefs = context.getSharedPreferences(context.packageName, MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString("Key_FavSingleWpp", favSingleWpp.joinToString(","))
        editor.apply()
    }

    fun getFavSingleWpp(context: Context): List<Int> {
        val preferences = context.getSharedPreferences(context.packageName, MODE_PRIVATE)
        val origin = preferences.getString("Key_FavSingleWpp", "") ?: ""
        return if (origin.isNotEmpty()) {
            origin.split(",").map { it.toInt() }
        } else {
            emptyList()
        }
    }

    fun setFavSlideWpp(context: Context, favSingleWpp: List<Int>) {
        val prefs = context.getSharedPreferences(context.packageName, MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString("Key_FavSlideWpp", favSingleWpp.joinToString(","))
        editor.apply()
    }

    fun getFavSlideWpp(context: Context): List<Int> {
        val preferences = context.getSharedPreferences(context.packageName, MODE_PRIVATE)
        val origin = preferences.getString("Key_FavSlideWpp", "") ?: ""
        return if (origin.isNotEmpty()) {
            origin.split(",").map { it.toInt() }
        } else {
            emptyList()
        }
    }

    fun setFavShortVideoWpp(context: Context, favSingleWpp: List<Int>) {
        val prefs = context.getSharedPreferences(context.packageName, MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString("Key_FavShortVideoWpp", favSingleWpp.joinToString(","))
        editor.apply()
    }

    fun getFavShortVideoWpp(context: Context): List<Int> {
        val preferences = context.getSharedPreferences(context.packageName, MODE_PRIVATE)
        val origin = preferences.getString("Key_FavShortVideoWpp", "") ?: ""
        return if (origin.isNotEmpty()) {
            origin.split(",").map { it.toInt() }
        } else {
            emptyList()
        }
    }


}