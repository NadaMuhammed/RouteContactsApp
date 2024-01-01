package com.example.routecontactsapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact (var name: String, var phone: String, var description: String) : Parcelable