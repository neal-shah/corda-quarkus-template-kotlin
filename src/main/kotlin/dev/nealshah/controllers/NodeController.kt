package dev.nealshah.controllers

import dev.nealshah.dtos.NodeInfoDTO
import dev.nealshah.services.NodeService
import net.corda.client.rpc.RPCException
import net.corda.core.node.NetworkParameters
import net.corda.core.node.NodeDiagnosticInfo
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import java.time.Instant
import javax.ws.rs.Produces
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("node")
@Tag(name = "Node Resources", description = "Corda node information endpoints.")
class NodeController {

    @Inject
    lateinit var service: NodeService

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    fun getNodeIdentityInfo(): NodeInfoDTO.Response {
        return service.getNodeIdentityInfo()
    }

    @GET
    @Path("/diagnostics")
    @Produces(MediaType.APPLICATION_JSON)
    fun getNodeDiagnosticsInfo(): NodeDiagnosticInfo {
        return service.getNodeDiagnosticsInfo()
    }

    @GET
    @Path("/network-map")
    @Produces(MediaType.APPLICATION_JSON)
    fun getNetworkMap(): List<String> {
        return service.getNetworkMap()
    }

    @GET
    @Path("/network-parameters")
    @Produces(MediaType.APPLICATION_JSON)
    fun getNetworkParameters(): NetworkParameters {
        return service.getNetworkParameters()
    }

    @GET
    @Path("/notaries")
    @Produces(MediaType.APPLICATION_JSON)
    fun getNotaries(): List<String> {
        return service.getNotaries()
    }

    @GET
    @Path("/time")
    @Produces(MediaType.APPLICATION_JSON)
    fun getNodeTime(): Instant {
        return service.getNodeTime()
    }

}