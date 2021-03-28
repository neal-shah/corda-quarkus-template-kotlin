package dev.nealshah.controllers

import dev.nealshah.services.FlowInfoService
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("flows")
@Tag(name = "Flow Resources", description = "CorDapp workflow endpoints.")
class FlowController {

    @Inject
    lateinit var flowInfoService: FlowInfoService

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    fun getRegisteredFlows(): List<String> {
        return flowInfoService.getRegisteredFlows()
    }

    @GET
    @Path("/completed")
    @Produces(MediaType.APPLICATION_JSON)
    fun getCompletedFlows(): List<Pair<String, Boolean>> {
        return flowInfoService.getCompletedFlows()
    }
}