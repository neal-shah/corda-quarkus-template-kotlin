package dev.nealshah.services

interface FlowInfoService {

    fun getRegisteredFlows() : List<String>

    fun getCompletedFlows() : List<Pair<String, Boolean>>

}