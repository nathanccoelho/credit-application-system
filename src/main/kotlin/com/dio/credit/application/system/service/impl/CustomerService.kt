package com.dio.credit.application.system.service.impl

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
            throw RuntimeException("Id $id not found")
        }
    }

    override fun delete(id: Long){
        return customerRepository.deleteById(id)
    }
}