package com.trunghtluu.breakingbadapp.model

data class Character (
    var id: Int = 0,
    var name: String? = null,
    var birthday: String? = null,
    var occupation: List<String>? = null,
    var img: String? = null,
    var status: String? = null,
    var nickname: String? = null,
    var appearance: List<Int>? = null,
    var portrayed: String? = null,
    var category: String? = null,
    var betterCallSaulAppearance: List<Any>? = null
)