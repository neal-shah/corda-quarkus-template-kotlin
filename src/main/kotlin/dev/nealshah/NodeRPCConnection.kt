package dev.nealshah

import net.corda.client.rpc.CordaRPCClient
import net.corda.client.rpc.CordaRPCConnection
import net.corda.client.rpc.GracefulReconnect
import net.corda.client.rpc.RPCException
import net.corda.client.rpc.internal.ReconnectingCordaRPCOps
import net.corda.core.messaging.CordaRPCOps
import net.corda.core.utilities.NetworkHostAndPort
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.slf4j.LoggerFactory
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy
import javax.enterprise.context.ApplicationScoped

private const val CORDA_USER_NAME = "config.rpc.username"
private const val CORDA_USER_PASSWORD = "config.rpc.password"
private const val CORDA_NODE_HOST = "config.rpc.host"
private const val CORDA_RPC_PORT = "config.rpc.port"

@ApplicationScoped
class NodeRPCConnection(
    @ConfigProperty(name = "config.rpc.host") private val host: String,
    @ConfigProperty(name = "config.rpc.port") private val rpcPort: Int,
    @ConfigProperty(name = "config.rpc.username") private val username: String,
    @ConfigProperty(name = "config.rpc.password") private val password: String
) : AutoCloseable {

    lateinit var rpcConnection: CordaRPCConnection
    lateinit var proxy: CordaRPCOps

    companion object {
        private val log = LoggerFactory.getLogger(NodeRPCConnection::class.java)
    }

    @PostConstruct
    fun initialiseNodeRPCConnection() {
        val rpcAddress = NetworkHostAndPort(host, rpcPort)
        val rpcClient = CordaRPCClient(rpcAddress)

        val gracefulReconnect = GracefulReconnect(
            onDisconnect = {
                log.info("RPC Disconnection handled.")
            },
            onReconnect = {
                log.info("RPC Connection handled.")
            },
            maxAttempts = 5
        )

        val rpcConnection = rpcClient.start(username, password, gracefulReconnect)
        proxy = rpcConnection.proxy
    }

    @PreDestroy
    override fun close() {
        rpcConnection.notifyServerAndClose()
    }
}