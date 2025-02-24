package infrastructure.db.supabase.repositories

import aplication.dto.feature.FeatureInputDTO
import appModule.utils
import domain.models.Feature
import domain.repository.FeatureRepository
import infrastructure.db.supabase.SupabaseService
import infrastructure.db.supabase.models.FeatureSupabaseModel
import infrastructure.utils.Utils

class SupabaseFeatureRepository(): FeatureRepository {
    override suspend fun create(featureInputDTO: FeatureInputDTO){
        try {
            val featureSupabaseModel = FeatureSupabaseModel(
                name = featureInputDTO.name,
                text = featureInputDTO.text,
                image_path = featureInputDTO.imagePath,
                id_projeto = featureInputDTO.idProjeto,
                importance = featureInputDTO.importance
            )

            SupabaseService.supabaseFeatures.insert(featureSupabaseModel)
        }catch (e: Exception){
            println(e.message)
        }
    }

    override suspend fun getById(id: Int): Result<Feature> {
        return try{
            val responseList = SupabaseService.supabaseFeatures.select {
                filter { eq("id", id) }
            }.decodeList<FeatureSupabaseModel>()

            val response = responseList.firstOrNull() ?: return Result.failure(NullPointerException("Feature not found"))

            Result.success(
                Feature(
                    id = response.id!!,
                    name = response.name ?: "",
                    text = response.text ?: "",
                    imagePath = response.image_path ?: "",
                    createdAt = utils.toDate(response.created_at!!),
                    idProjeto = response.id_projeto!!,
                    importance = response.importance ?: 0
                )
            )
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun getAllFromProjeto(idProjeto: Int): Result<List<Feature>> {
        return try{
            val responseList = SupabaseService.supabaseFeatures.select {
                filter { eq("id_projeto", idProjeto) }
            }.decodeList<FeatureSupabaseModel>()

            val features = responseList.map { response ->
                Feature(
                    id = response.id!!,
                    name = response.name ?: "",
                    text = response.text ?: "",
                    imagePath = response.image_path ?: "",
                    createdAt = utils.toDate(response.created_at!!),
                    idProjeto = response.id_projeto!!,
                    importance = response.importance ?: 0
                )
            }

            Result.success(features)
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun update(id: Int, feature: Feature): Result<Feature> {
        return try{
            val featureSupabaseModel = FeatureSupabaseModel(
                id = id,
                name = feature.name,
                text = feature.text,
                image_path = feature.imagePath,
                id_projeto = feature.idProjeto,
                created_at = utils.fromDate(feature.createdAt),
                importance = feature.importance
            )
            val responseList = SupabaseService.supabaseFeatures.upsert(featureSupabaseModel){
                select()
            }.decodeList<FeatureSupabaseModel>()

            val response = responseList.firstOrNull() ?: return Result.failure(IllegalArgumentException("Feature not found"))

            Result.success(
                Feature(
                    id = response.id!!,
                    name = response.name ?: "",
                    text = response.text ?: "",
                    imagePath = response.image_path ?: "",
                    createdAt = utils.toDate(response.created_at!!),
                    idProjeto = response.id_projeto!!,
                    importance = response.importance ?: 0
                )
            )
        }catch (e: Exception){
            Result.failure(e)
        }

    }

    override suspend fun delete(id: Int): Boolean {
        return try{
            SupabaseService.supabaseFeatures.delete {
                filter {
                    eq("id", id)
                }
            }
            true
        }catch (e: Exception){
            false
        }

    }
}