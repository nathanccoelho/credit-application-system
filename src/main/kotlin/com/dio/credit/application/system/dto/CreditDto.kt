package com.dio.credit.application.system.dto

import com.dio.credit.application.system.model.Credit
import com.dio.credit.application.system.model.Customer
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    @field:NotNull (message= "Invalid credit value")val creditValue: BigDecimal,
    @field:Future (message = "First day of installmente need ini future")val dayFirstInstallment : LocalDate,
    @field:Positive(message = "Number need positive") val numberOfInstallment : Int,
    @field:NotNull (message= "Invalid customerId")val customerId : Long
)

{
    fun toEntity() : Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstInstallment,
        numberOfInstallment = this.numberOfInstallment,
        customer = Customer(id = this.customerId)
    )
}
