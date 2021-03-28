package dev.nealshah.configuration.jackson

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.databind.ObjectMapper
import dev.nealshah.NodeRPCConnection
import io.quarkus.jackson.ObjectMapperCustomizer
import org.slf4j.LoggerFactory
import javax.enterprise.inject.Instance
import javax.inject.Singleton
import net.corda.client.jackson.JacksonSupport

class ObjectMapperConfiguration {

    companion object {
        private val log = LoggerFactory.getLogger(ObjectMapperConfiguration::class.java)
    }

    @Singleton
    fun myObjectMapper(customizers: Instance<ObjectMapperCustomizer>, rpcConnection: NodeRPCConnection): ObjectMapper {
        val mapper: ObjectMapper = JacksonSupport.createDefaultMapper(rpcConnection.proxy, JsonFactory(), false, false)
        for (customizer in customizers) {
            log.info("Configuring Jackson mapper [{}]", mapper)
            customizer.customize(mapper)
        }
        return mapper
    }
}