package com.ignmonlop.repasowot.api.service

import com.ignmonlop.repasowot.api.Zone
import com.ignmonlop.repasowot.api.common.Constants
import retrofit2.http.GET

interface ZoneService {
    @GET(Constants.PATH_ZONES)
    suspend fun getZones(): MutableList<Zone>
}