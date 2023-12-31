package com.smart.smartapp.util

enum class ImageType { COUNTRY, PURE_PATH, CATEGORY }

enum class LocalNotificationType { SUCCESS, INFO, WARNING, ERROR }

enum class LocationType(val type:Int) { PICK(0), DELIVERY(1) }
enum class WorkType(val type:Int) { START(0), END(1) }


enum class Language(val lang:String) { ARABIC("ar"), ENGLISH("en") }

enum class ServiceType(val serviceName:String) { EMERGENCY("Emergency"), TOW_TRUCK("Tow Truck")  ,COURIER("Courier") , BUY("Buy")}

