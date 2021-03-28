package dev.nealshah.services

import dev.nealshah.dtos.NodeInfoDTO
import net.corda.core.node.NetworkParameters
import net.corda.core.node.NodeDiagnosticInfo
import java.time.Instant
import javax.ws.rs.core.Response

interface NodeService {

    fun getNodeIdentityInfo() : NodeInfoDTO.Response

    fun getNodeDiagnosticsInfo() : NodeDiagnosticInfo

    fun getNetworkMap() : List<String>

    fun getNetworkParameters() : NetworkParameters

    fun getNotaries() : List<String>

    fun getNodeTime() : Instant

}