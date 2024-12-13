package com.frxhaikal_plg.ingrevia.data.repository

import com.frxhaikal_plg.ingrevia.data.local.UserPreferences
import com.frxhaikal_plg.ingrevia.data.network.NetworkResult
import com.frxhaikal_plg.ingrevia.data.remote.model.auth.LoginResponse
import com.frxhaikal_plg.ingrevia.data.remote.source.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class AuthRepository(
    private val remoteDataSource: RemoteDataSource,
    private val userPreferences: UserPreferences
) {
    suspend fun login(email: String, password: String): Flow<NetworkResult<LoginResponse>> {
        return remoteDataSource.login(email, password)
    }

    suspend fun saveUserSession(
        token: String,
        userId: String,
        email: String,
        displayName: String
    ) {
        userPreferences.saveUserSession(token, userId, email, displayName)
    }
} 