package cz.demo.monetaproject.rest

import cz.demo.monetaproject.core.CleanUpDb
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.ResultActions

import static cz.demo.monetaproject.rest.TestData.defaultNumberBody
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class TransformedNumberApiSpec extends BaseSpec implements CleanUpDb {


    def "create transformedNumber"() {

        given:
        def transformedNumberBody = defaultNumberBody()
        def numberBodyMap = toMap(transformedNumberBody)

        when:
        ResultActions postNumberResponse = mockMvc.perform(post("/numbers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transformedNumberBody)
        )

        def postAuthorResponseBody = extractBodyFromResponseAsMap(postNumberResponse)

        then:
        postNumberResponse.andExpect(status().isCreated())
        postAuthorResponseBody.inputNumber == numberBodyMap.inputNumber
        postAuthorResponseBody.transformedNumber == 11331545

    }
}
