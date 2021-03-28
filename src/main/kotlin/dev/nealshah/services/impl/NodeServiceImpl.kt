package dev.nealshah.services.impl

import dev.nealshah.NodeRPCConnection
import dev.nealshah.dtos.NodeInfoDTO
import dev.nealshah.services.NodeService
import net.corda.client.rpc.ConnectionFailureException
import net.corda.client.rpc.RPCException
import net.corda.core.node.NetworkParameters
import net.corda.core.node.NodeDiagnosticInfo
import java.time.Instant
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.core.Response

@ApplicationScoped
class NodeServiceImpl(rpcConnection: NodeRPCConnection) : NodeService {

    private val proxy = rpcConnection.proxy

    override fun getNodeIdentityInfo(): NodeInfoDTO.Response {
        val info = proxy.nodeInfo()
        return NodeInfoDTO.Response(
            organisation = info.legalIdentities[0].name.organisation,
            organisationUnit = info.legalIdentities[0].name.organisationUnit,
            commonName = info.legalIdentities[0].name.commonName.toString(),
            locality = info.legalIdentities[0].name.locality,
            country = info.legalIdentities[0].name.country,
            serial = info.serial.toString(),
            platformVersion = info.platformVersion
        )
    }

    override fun getNodeDiagnosticsInfo(): NodeDiagnosticInfo {
        return proxy.nodeDiagnosticInfo()
    }

    override fun getNetworkMap(): List<String> {
        val response = proxy.networkMapSnapshot()
        return response.map {
            it.legalIdentities[0].name.toString()
        }
    }

    override fun getNetworkParameters(): NetworkParameters {
        val response = proxy.networkParameters
        return response.copy(notaries = emptyList())
    }

    override fun getNotaries(): List<String> {
        return proxy.notaryIdentities().map { it.name.toString() }
    }

    override fun getNodeTime(): Instant {
        return proxy.currentNodeTime()
    }
}