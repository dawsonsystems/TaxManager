package com.dawsonsystems.taxmanager

import org.joda.time.DateTime

class IncomeItem {

    Company company
    BigDecimal amount
    DateTime date
    byte[] content
    String contentType

    static constraints = {
        content(maxSize:1073741824)
        amount(nullable:true)
        company(nullable:false)
        date(nullable:true)

    }
}

