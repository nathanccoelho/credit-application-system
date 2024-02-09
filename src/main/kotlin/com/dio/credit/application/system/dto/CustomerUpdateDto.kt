package com.dio.credit.application.system.dto

import com.dio.credit.application.system.model.Customer
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class CustomerUpdateDto(
    @field:NotEmpty(message = "Invalid First Name") val firstName: String,
    @field:NotEmpty(message = "Invalid last Name")val lastName: String,
    @field:NotNull(message = "Invalid income") val income: BigDecimal,
    @field:NotEmpty(message = "Invalid email")@Email val email: String,
    @field:NotEmpty(message = "Invalid password") val password: String,
    @field:NotEmpty(message = "Invalid zip code")val zipCode: String,
    @field:NotEmpty(message = "Invalid street")val street: String

){
    fun toEntity (customer : Customer ): Customer {
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.email = this.email
        customer.password = this.password
        customer.address.zipCode = this.zipCode
        customer.address.street = this.street
        return customer
    }
}
