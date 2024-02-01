package com.dio.credit.application.system.controller

import com.dio.credit.application.system.dto.CustomerDto
import com.dio.credit.application.system.model.Customer
import com.dio.credit.application.system.repository.CreditRepository
import com.dio.credit.application.system.repository.CustomerRepository
import com.dio.credit.application.system.service.impl.CustomerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customers")
class CustomerController(
    private val customerService: CustomerService
) {

    @PostMapping
    fun saveCustomer(@RequestBody customerDto: CustomerDto): String{
        val savedCustomer = this.customerService.save(customerDto.toEntity())
        return "Customer ${savedCustomer.email} saved!"
    }

}