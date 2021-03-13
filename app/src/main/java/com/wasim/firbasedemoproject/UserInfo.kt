package com.wasim.firbasedemoproject

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.PropertyName

@IgnoreExtraProperties
data class UserInfo(
    @Exclude
    var first_name: String? = "",
    @get:PropertyName("second_name")
    @set:PropertyName("second_name")
    var secondName: String? = ""
)