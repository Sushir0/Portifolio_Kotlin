package infrastructure.db.supabase.repositories

import aplication.dto.projeto.ProjetoInputDTO
import appModule.utils
import domain.models.Projeto
import domain.repository.ProjetoRepository
import infrastructure.db.supabase.SupabaseService
import infrastructure.db.supabase.models.ProjetoSupabaseModel
import infrastructure.utils.Utils
import io.github.jan.supabase.annotations.SupabaseExperimental
import java.util.Date

class SupabaseProjetoRepository(): ProjetoRepository {
    override suspend fun create(projetoInputDTO: ProjetoInputDTO){
        try {
            val projetoSupabaseModel = ProjetoSupabaseModel(
                name = projetoInputDTO.name,
                description = projetoInputDTO.description,
                text = projetoInputDTO.text,
                github_url = projetoInputDTO.githubUrl,
                image_path = projetoInputDTO.imagePath,
                tags = projetoInputDTO.tags,
                importance = projetoInputDTO.importance
            )


            SupabaseService.supabaseProjetos
                .insert(projetoSupabaseModel)
        } catch (e: Exception) {
            println(e.message)
        }
    }

    override suspend fun getById(id: Int): Result<Projeto?> {
        return try {
            val responseList = SupabaseService.supabaseProjetos.select {
                filter { eq("id", id) }
            }.decodeList<ProjetoSupabaseModel>()


            val response = responseList.firstOrNull() ?: return Result.success(null)

            Result.success(
                Projeto(
                    id = response.id!!,
                    name = response.name ?: "",
                    description = response.description ?: "",
                    text = response.text ?: "",
                    gitHubUrl = response.github_url ?: "",
                    imagePath = response.image_path ?: "",
                    tags = response.tags ?: emptyList(),
                    importance = response.importance ?: 0,
                    createdAt = utils.toDate(response.created_at!!),
                    updatedAt = utils.toDate(response.updated_at!!)
                )
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getAll(): Result<List<Projeto>> {
        return try{
            val responseList = SupabaseService.supabaseProjetos.select().decodeList<ProjetoSupabaseModel>()

            val projetos = responseList.map { response ->
                Projeto(
                    id = response.id!!,
                    name = response.name ?: "",
                    description = response.description ?: "",
                    text = response.text ?: "",
                    gitHubUrl = response.github_url ?: "",
                    imagePath = response.image_path ?: "",
                    tags = response.tags ?: emptyList(),
                    createdAt = utils.toDate(response.created_at!!),
                    updatedAt = utils.toDate(response.updated_at!!),
                    importance = response.importance ?: 0
                )
            }

            Result.success(projetos)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun update(id: Int, projeto: Projeto): Result<Projeto> {
        return try{
            val projetoSupabaseModel = ProjetoSupabaseModel(
                id = id,
                name = projeto.name,
                description = projeto.description,
                text = projeto.text,
                github_url = projeto.gitHubUrl,
                image_path = projeto.imagePath,
                tags = projeto.tags,
                created_at = utils.fromDate(projeto.createdAt),
                updated_at = utils.fromDate(Date()),
                importance = projeto.importance
            )
            val responseList = SupabaseService.supabaseProjetos.upsert(projetoSupabaseModel){
                select()
            }.decodeList<ProjetoSupabaseModel>()

            val response = responseList.firstOrNull() ?: return Result.failure(IllegalArgumentException("Projeto not found"))

            Result.success(
                Projeto(
                    id = response.id!!,
                    name = response.name ?: "",
                    description = response.description ?: "",
                    text = response.text ?: "",
                    gitHubUrl = response.github_url ?: "",
                    imagePath = response.image_path ?: "",
                    tags = response.tags ?: emptyList(),
                    createdAt = utils.toDate(response.created_at!!),
                    updatedAt = utils.toDate(response.updated_at!!),
                    importance = response.importance ?: 0

                )
            )
        }catch (e:Exception){
            Result.failure(e)
        }
    }

    override suspend fun delete(id: Int): Boolean {
        return try{
            SupabaseService.supabaseProjetos.delete {
                filter {
                    eq("id", id)
                }
            }
            true
        } catch (e: Exception) {
            false
        }

    }

}