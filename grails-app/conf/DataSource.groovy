environments {
    development {
        grails.mongo.databaseName = "taxmanager-dev"
    }
    test {
        grails.mongo.databaseName = "taxmanager-test"
    }
    production {
        grails.mongo.databaseName = "taxmanager"
    }
}
