package dev.nealshah.mockServices

import dev.nealshah.dtos.NodeInfoDTO
import dev.nealshah.services.NodeService
import io.quarkus.test.Mock
import net.corda.core.node.NetworkParameters
import net.corda.core.node.NodeDiagnosticInfo
import java.time.Instant
import javax.enterprise.context.ApplicationScoped

@Mock
@ApplicationScoped
class MockNodeService : NodeService {

    @Override
    override fun getNodeIdentityInfo(): NodeInfoDTO.Response {
        return NodeInfoDTO.Response(
            organisation = "Party A",
            organisationUnit = null,
            commonName = null,
            locality = "London",
            country = "GB",
            serial = "123456789",
            platformVersion = 9
        )
    }

    override fun getNodeDiagnosticsInfo(): NodeDiagnosticInfo {
        TODO("Not yet implemented")
    }

    override fun getNetworkMap(): List<String> {
        TODO("Not yet implemented")
    }

    override fun getNetworkParameters(): NetworkParameters {
        TODO("Not yet implemented")
    }

    override fun getNotaries(): List<String> {
        TODO("Not yet implemented")
    }

    override fun getNodeTime(): Instant {
        TODO("Not yet implemented")
    }
}