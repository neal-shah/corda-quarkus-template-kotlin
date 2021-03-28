package dev.nealshah.dtos

object NodeInfoDTO {
    data class Response(
        val organisation: String,
        val organisationUnit: String?,
        val commonName: String?,
        val locality: String,
        val country: String,
        val serial: String,
        val platformVersion: Int
    )
}