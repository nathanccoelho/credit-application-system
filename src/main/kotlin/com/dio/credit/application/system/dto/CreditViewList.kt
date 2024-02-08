package com.dio.credit.application.system.dto

import com.dio.credit.application.system.model.Credit
import java.math.BigDecimal
import java.util.UUID

data class CreditViewList(
    val creditCode:UUID,
    val creditValue: BigDecimal,
    val numberOfInstallment: Int

){
    constructor(credit: Credit ): this(
        creditCode = credit.creditCode,
        creditValue = credit.creditValue,
        numberOfInstallment = credit.numberOfInstallment
    )
}
