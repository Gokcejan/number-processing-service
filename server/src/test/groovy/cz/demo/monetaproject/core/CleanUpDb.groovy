package cz.demo.monetaproject.core

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate

trait CleanUpDb {

    @Autowired
    JdbcTemplate jdbcTemplate

    def cleanUpDb() {
        jdbcTemplate.execute("DELETE FROM transformed_number")
        jdbcTemplate.execute("DELETE FROM ticket")


    }

}