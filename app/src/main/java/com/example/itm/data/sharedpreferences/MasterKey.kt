package com.example.itm.data.sharedpreferences

import android.content.Context
import androidx.security.crypto.MasterKey

object MasterKey {

    fun getMasterKey(context: Context): MasterKey {
        return MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }
}