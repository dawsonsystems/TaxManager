package com.dawsonsystems.taxmanager

import org.joda.time.DateTime

class IncomeItem {

    Company company
    Money amount
    DateTime date
    byte[] content
    String contentType

    static embedded = ['amount']

    static constraints = {
        content(maxSize:1073741824)
        amount(nullable:true)
        company(nullable:false)
        date(nullable:true)

    }
}

