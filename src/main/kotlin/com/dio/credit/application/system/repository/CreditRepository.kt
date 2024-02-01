package com.dio.credit.application.system.repository

import com.dio.credit.application.system.model.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CreditRepository: JpaRepository<Credit, Long>{

}