package com.dio.credit.application.system.controller

import com.dio.credit.application.system.dto.CustomerDto
import com.dio.credit.application.system.dto.CustomerUpdateDto
import com.dio.credit.application.system.model.Address
import com.dio.credit.application.system.model.Customer
import com.dio.credit.application.system.repository.CustomerRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.math.BigDecimal
import java.util.Random

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
class CustomerControllerTest {
    @Autowired private lateinit var customerRepository: CustomerRepository
    @Autowired private lateinit var mockMvc: MockMvc
    @Autowired private lateinit var objectMapper: ObjectMapper

    companion object{
        const val URL: String = "/customers"
    }

    @BeforeEach fun setup() = customerRepository.deleteAll()
    @AfterEach fun tearDown() = customerRepository.deleteAll()

    @Test
    fun `should create a customer and return 201 status`(){
        // given
        val customerDto: CustomerDto = buildCustomerDto()
        val valueAsString: String = objectMapper.writeValueAsString(customerDto)
        // when
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(valueAsString))
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Nathan"))
            .andDo(MockMvcResultHandlers.print())
        // then

    }


    @Test
    fun `should not save a customer with same cpf and return 409 status`(){
        // given
        customerRepository.save(buildCustomerDto().toEntity())
        val customerDto: CustomerDto = buildCustomerDto()
        val valueAsString: String = objectMapper.writeValueAsString(customerDto)

        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(valueAsString))
            .andExpect(MockMvcResultMatchers.status().isConflict)
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(409))
    }

    @Test
    fun `should not save a customer with firtsName empty and return 400 status`(){
        // given
        val customerDto: CustomerDto = buildCustomerDto(firstName = "")
        val valueAsString: String = objectMapper.writeValueAsString(customerDto)
        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(valueAsString))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
    }

    @Test
    fun `should find customer by id and return 200 status`(){
        // given
      val customer: Customer =  customerRepository.save(buildCustomerDto().toEntity())
        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders.get("URL/${customer.id}")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Nathan"))
            .andDo(MockMvcResultHandlers.print())

    }

    @Test
    fun `shoul not find customer whith invalid id and return 400 status`(){
        // given
        val invalidId: Long = 2L
        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders.get("URL/$invalidId")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))

    }
    @Test
    fun `should delete customer by id and return status 204`(){
        // given
        val customer: Customer =  customerRepository.save(buildCustomerDto().toEntity())
        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders.delete("URL/${customer.id}")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNoContent)
            .andDo(MockMvcResultHandlers.print())


    }

    @Test
    fun `should delete customer by id and return status 400`(){
        // given
        val invalidId: Long = 2L
        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders.delete("URL/$invalidId")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andDo(MockMvcResultHandlers.print())


    }

    @Test
    fun `should update a customer and return status 200`(){
        // given
        val customer: Customer = customerRepository.save(buildCustomerDto().toEntity())
        val customerUpdateDto: CustomerUpdateDto = buildCustomerUpdateDto()
        val valueAsString: String = objectMapper.writeValueAsString(customerUpdateDto)
        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders.patch("URL?customerId=${customer.id}")
            .contentType(MediaType.APPLICATION_JSON)
            .content(valueAsString))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Nathan"))
            .andDo(MockMvcResultHandlers.print())


    }
    @Test
    fun `should not update a customer with invalid id and return status 400`(){
        // given
       val invalidId: Long = Random().nextLong()
        val customerUpdateDto: CustomerUpdateDto = buildCustomerUpdateDto()
        val valueAsString: String = objectMapper.writeValueAsString(customerUpdateDto)
        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders.patch("URL?customerId=$invalidId")
            .contentType(MediaType.APPLICATION_JSON)
            .content(valueAsString))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)

    }










    private fun buildCustomerDto(
        firstName: String = "Nathan",
        lastName: String = "Coelho",
        cpf: String = "48092652856",
        email: String = "nathan@gmail.com",
        password: String = "123456789",
        zipCode: String = "85722000",
        street: String = "Fernando vasconcelos rossi",
        income: BigDecimal = BigDecimal.valueOf(1000.0)
    ) = CustomerDto(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        password = password,
        zipCode = zipCode,
        street = street,
        income = income,
    )

    private fun buildCustomerUpdateDto(
        firstName: String = "Nathan",
        lastName: String = "Coelho",
        email: String = "nathan@gmail.com",
        password: String = "123456789",
        zipCode: String = "85722000",
        street: String = "Fernando vasconcelos rossi",
        income: BigDecimal = BigDecimal.valueOf(1000.0)
    ) = CustomerUpdateDto(
        firstName = firstName,
        lastName = lastName,
        email = email,
        password = password,
        zipCode = zipCode,
        street = street,
        income = income,
    )
}