package dev.nealshah

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test

@QuarkusTest
class NodeResourceTest {

    @Test
    fun testNodeInfoEndpoint() {
        RestAssured.given()
            .`when`().get("/node")
            .then()
            .statusCode(200)
            .body("organisation", Matchers.notNullValue(),
            "organisation", Matchers.equalTo("Party A"),
            "organisationUnit", Matchers.nullValue(),
            "locality", Matchers.equalTo("London"))
    }
}