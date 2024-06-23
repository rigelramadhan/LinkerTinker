package one.reevdev.linkertinker.utils

import com.google.gson.Gson
import one.reevdev.linkertinker.core.data.model.LinkCategory

fun LinkCategory.toJson() = Gson().toJson(this)

fun String.toObject() = Gson().fromJson(this, LinkCategory::class.java)