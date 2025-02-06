package domain.repository

import aplication.dto.feature.FeatureInputDTO
import domain.models.Feature

interface FeatureRepository {
    suspend fun create(featureInputDTO: FeatureInputDTO)
    suspend fun getById(id: Int): Result<Feature>
    suspend fun getAllFromProjeto(idProjeto: Int): Result<List<Feature>>
    suspend fun update(id: Int, feature: Feature): Result<Feature>
    suspend fun delete(id: Int): Boolean
}