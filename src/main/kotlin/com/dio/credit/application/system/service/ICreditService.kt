package com.dio.credit.application.system.service

import com.dio.credit.application.system.model.Credit
import java.util.UUID

interface ICreditService {

    fun save(credit: Credit): Credit

    fun findByAllCustomer(customerId: Long): List<Credit>

    fun findByCreditCode(creditCode: UUID): Credit
}