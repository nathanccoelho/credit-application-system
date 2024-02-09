package com.dio.credit.application.system.service.impl

import com.dio.credit.application.system.exception.BusinessException
import com.dio.credit.application.system.model.Customer
import com.dio.credit.application.system.repository.CustomerRepository
import com.dio.credit.application.system.service.ICustomerService
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
): ICustomerService {


    override fun save(customer: Customer): Customer {
        return customerRepository.save(customer)
    }

    override fun findById(id: Long): Customer {
        return customerRepository.findById(id).orElseThrow{
            throw BusinessException("Id $id not found")
        }
    }

    override fun delete(id: Long){

       val customer: Customer = this.findById(id)
       this.customerRepository.delete(customer)
    }
}