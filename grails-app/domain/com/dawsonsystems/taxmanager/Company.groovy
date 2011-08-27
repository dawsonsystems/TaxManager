package com.dawsonsystems.taxmanager

import org.joda.time.DateTime

class Company {

    String companyName
    DateTime yearEnd

    static hasMany = [clients:Client]

    static constraints = {
    }

//    static mapping = {
//        yearEnd type: PersistentDateTime, lazy: false
//
//    }
}
