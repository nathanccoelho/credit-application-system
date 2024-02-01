package com.dio.credit.application.system.service

import com.dio.credit.application.system.model.Customer

interface ICustomerService {

    fun save(customer: Customer): Customer

    fun findById(id: Long): Customer

    fun delete(id: Long): Customer
}