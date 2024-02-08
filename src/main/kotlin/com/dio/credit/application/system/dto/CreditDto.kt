package com.dio.credit.application.system.dto

import com.dio.credit.application.system.model.Credit
import com.dio.credit.application.system.model.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    val creditValue: BigDecimal,
    val dayFirstInstallment : LocalDate,
    val numberOfInstallment : Int,
    val customerId : Long
)

{
    fun toEntity() : Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstInstallment,
        numberOfInstallment = this.numberOfInstallment,
        customer = Customer(id = this.customerId)
    )
}
