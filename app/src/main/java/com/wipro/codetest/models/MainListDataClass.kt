package com.wipro.codetest.models

import com.google.gson.annotations.SerializedName

data class MainListDataClass(
    @SerializedName("title") val title: String,
    @SerializedName("rows") val rows: List<Rows>
)