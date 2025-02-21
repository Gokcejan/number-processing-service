package cz.demo.monetaproject.core

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate

trait CleanUpDb {

    @Autowired
    JdbcTemplate jdbcTemplate

    def cleanUpDb() {
        jdbcTemplate.execute("DELETE FROM TRANSFORMED_NUMBER")

    }

}