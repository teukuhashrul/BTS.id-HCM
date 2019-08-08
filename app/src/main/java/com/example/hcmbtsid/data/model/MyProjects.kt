package com.example.hcmbtsid.data.model

/**
 * data container for per item in recyler view in fragment profile
 */
data class MyProjects(
    val projectsName: String ,
    val status: String,
    val clientName: String,
    val categories: String ,
    val startDate: String,
    val endDate: String
)