package com.agnitt.vdt.database
//
//import androidx.room.TypeConverter
//
//class DataListConverter {
//    @TypeConverter
//    fun fromList(list: List<Float>?): String =
//        list?.joinToString(separator = ";") { it.toString() } ?: ""
//
//    @TypeConverter
//    fun toList(string: String?): List<Float> =
//        (string?.split(";")?.mapNotNull { it.toFloatOrNull() } ?: emptyList()).toList()
//}
//
//class IdsListConverter {
//    @TypeConverter
//    fun fromList(list: List<Long>?): String =
//        list?.joinToString(separator = ";") { it.toString() } ?: ""
//
//    @TypeConverter
//    fun toList(string: String?): List<Long> =
//        (string?.split(";")?.mapNotNull { it.toLongOrNull() } ?: emptyList()).toList()
//}