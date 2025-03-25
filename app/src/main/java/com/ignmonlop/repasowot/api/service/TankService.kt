package com.ignmonlop.repasowot.api.service

import com.ignmonlop.repasowot.api.Tank
import com.ignmonlop.repasowot.api.common.Constants
import retrofit2.http.GET

interface TankService {
    @GET(Constants.PATH_TANKS)
    suspend fun getTanks(): MutableList<Tank>
}