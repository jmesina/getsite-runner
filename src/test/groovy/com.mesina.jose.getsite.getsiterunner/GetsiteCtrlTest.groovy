package com.mesina.jose.getsite.getsiterunner

import com.mesina.jose.getsite.getsiterunner.getsite.Getsite
import com.mesina.jose.getsite.getsiterunner.getsite.GetsiteController
import com.mesina.jose.getsite.getsiterunner.getsite.GetsiteRepository
import com.mesina.jose.getsite.getsiterunner.getsite.GetsiteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import spock.lang.Specification
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import spock.mock.DetachedMockFactory

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ContextConfiguration(classes = [GetsiteRunnerApplication])
@WebMvcTest(GetsiteController)
class GetsiteCtrlTest extends Specification {
    @Autowired
    MockMvc mockMvc

    @Autowired
    GetsiteRepository mockGetsiteRepo

    void setup() {
    }

    void cleanup() {
    }

    def "Supports reading existing Getsites"() {
        given: "an existing list of getsites"
        List<Getsite> getsites = getsites()

        when: "a read request is made for all getsites"

        ResultActions resultActions = mockMvc.perform(get("/getsite").accept(MediaType.APPLICATION_JSON))

        then: "a success status is returned"
        resultActions.andExpect(status().is(HttpStatus.OK.value()))

        and: "the list of existing getsites is returned"
        1 * mockGetsiteRepo.findAll() >> getsites
        resultActions.andExpect(jsonPath('$').isArray())
        getsites.eachWithIndex { gs, i ->
            resultActions.andExpect(jsonPath("\$[${i}].diid").value(gs.diid))
        }
    }


    def "Supports creating a getsite entry"() {

        given: "a new getsite"
        Getsite getsite = new Getsite("1234", "www.somedomain.com", "1234@db_name",
                "somedomain")

        when: "a create request is made for a getsite"
            ResultActions resultActions = mockMvc.perform(post("/getsite")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"diid\": \"DI4755\", \"fidn\": \"www.myaffinitybanking.com\", " +
                    "\"dbname\": \"abbinternet_data@db_206\", \"backend\": \"myaffinitybanking\"}"))

        then: "the getsite is added to the repo"
        1 * mockGetsiteRepo.save(_) >> getsite

        and: "a success status is returned"
        resultActions.andExpect(status().isCreated())

    }

    def "Supports updating a getsite entry"(){

        given: "an existing gesite"
        Getsite getsite = new Getsite("1234")

        when: "a update request is made for a getsite"
        ResultActions resultActions = mockMvc.perform(put("/getsite/{diid}", getsite.getDiid())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"diid\": \"1234\", \"fidn\": \"www.test.com\", \"dbname\": \"test@db_206\", \"backend\": \"test\"}"))

        then: "a request is made to find the getsite by id"
        1 * mockGetsiteRepo.findOne(getsite.getDiid()) >> getsite

        then: "the getsite is updated in the repo"
        1 * mockGetsiteRepo.save(getsite) >> getsite

        and: "a success status is returned"
        resultActions.andExpect(status().isNoContent())
    }

    def "Supports deleting a getsite entry"(){

        given: "an existing diid for a getsite"
        Getsite getsite = new Getsite("1234")

        when: "a delete request is made for a getsite"
        ResultActions resultActions = mockMvc.perform(delete("/getsite/{id}", getsite.getDiid())
                .contentType(MediaType.APPLICATION_JSON))

        then: "a getsite is delete from the repo"
        mockGetsiteRepo.delete(getsite.getDiid())

        and: "a success status is returned"
        resultActions.andExpect(status().isNoContent())
    }



    List<Getsite> getsites() {
        [
                new Getsite("1234", "www.somedomain.com", "1234@db_name", "somedomain"),
                new Getsite("2345", "www.test.com", "2345@db_name", "test")
        ]
    }

    @TestConfiguration
    static class MockConfig {
        DetachedMockFactory detachedMockFactory = new DetachedMockFactory()

        @Bean
        GetsiteRepository getsiteRepoMock() {
            detachedMockFactory.Mock(GetsiteRepository)
        }

        @Bean
        GetsiteService getsiteService() {
            new GetsiteService(getsiteRepoMock())
        }
    }

}