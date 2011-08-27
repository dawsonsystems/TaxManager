package com.dawsonsystems.taxmanager

import org.joda.time.DateTime

class ExpenseItem {

    Company company
    BigDecimal amount
    Date date
    byte[] content
    String contentType
    String description

    static constraints = {
        content(maxSize:1073741824)
        amount(nullable:true)
        company(nullable:true)
        date(nullable:true)
    }
}
