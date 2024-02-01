package com.dio.credit.application.system.model

data class Customer(
    var firtsName: String = "",
    var lastName: String ="",
    val cpf: String,
    var email: String= "",
    var password: String= "",
    var address: Address = Address(),
    var credits: List<Credit> = mutableListOf(),
    val id: Long? = null
)
