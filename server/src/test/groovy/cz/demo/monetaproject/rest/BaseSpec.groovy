package cz.demo.monetaproject.rest

import cz.demo.monetaproject.core.CleanUpDb
import cz.demo.monetaproject.core.WebMockSpec

class BaseSpec extends WebMockSpec implements CleanUpDb{

    def setupSpec() {
    }

    def cleanup() {
        cleanUpDb()
    }

    def cleanupSpec() {
    }

}
