package dev.nealshah.services.impl

import dev.nealshah.NodeRPCConnection
import dev.nealshah.services.FlowInfoService
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class FlowInfoServiceImpl(rpcConnection: NodeRPCConnection) : FlowInfoService {

    private val proxy = rpcConnection.proxy

    override fun getRegisteredFlows(): List<String> {
        return proxy.registeredFlows()
    }

    override fun getCompletedFlows(): List<Pair<String, Boolean>> {
        val completedFlows = proxy.finishedFlowsWithClientIds()
        return completedFlows.toList()
    }
}