package com.example.runningapp.local

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

class Converters {


    fun  toBitmap(bytes: ByteArray) : Bitmap {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }


    fun fromBitmap(bmp: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }

}