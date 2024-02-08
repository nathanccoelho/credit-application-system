package com.dio.credit.application.system.service.impl

import com.dio.credit.application.system.model.Credit
import com.dio.credit.application.system.repository.CreditRepository
import com.dio.credit.application.system.service.ICreditService
import org.springframework.stereotype.Service
import java.util.*
@Service
class CreditService(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService): ICreditService {
    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)
    }

    override fun findByAllCustomer(customerId: Long): List<Credit> {
        return this.creditRepository.findByAllCustomerId(customerId)
    }

    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
       val credit: Credit = this.creditRepository.findByCreditCode(creditCode) ?: throw RuntimeException("Credit code $creditCode not found")
         if(credit.customer?.id == customerId){
             return credit
         } else throw RuntimeException("Contact admin")
    }
}