package com.dio.credit.application.system.dto

import com.dio.credit.application.system.model.Address
import com.dio.credit.application.system.model.Customer
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDto(
    @field:NotEmpty(message = "Invalid First Name") val firstName: String,
    @field:NotEmpty(message = "Invalid Last Name") val lastName: String,
    @field:NotEmpty(message = "Invalid CPF") @field:CPF val cpf : String,
    @field:NotNull(message = "Invalid income")val income: BigDecimal,
    @field:NotEmpty(message = "Invalid email")@Email val email: String,
    @field:NotEmpty(message = "Invalid password") val password: String,
    @field:NotEmpty(message = "Invalid zip code") val zipCode: String,
    @field:NotEmpty(message = "Invalid street") val street: String

) {

    fun toEntity(): Customer = Customer(
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        email = this.email,
        password = this.password,
        address = Address(
            zipCode = this.zipCode,
            street = this.street
        )

    )
}
