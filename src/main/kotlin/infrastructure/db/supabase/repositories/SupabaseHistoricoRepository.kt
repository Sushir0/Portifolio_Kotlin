package infrastructure.db.supabase.repositories

import aplication.dto.historico.HistoricoInputDTO
import appModule.utils
import domain.models.Historico
import domain.repository.HistoricoRepository
import infrastructure.db.supabase.SupabaseService
import infrastructure.db.supabase.models.HistoricoSupabaseModel

class SupabaseHistoricoRepository(): HistoricoRepository {
    override suspend fun create(historicoInputDTO: HistoricoInputDTO) {
        try {
            val historicoSupabaseModel = HistoricoSupabaseModel(
                name = historicoInputDTO.name,
                text = historicoInputDTO.text,
                id_projeto = historicoInputDTO.idProjeto,
            )

            SupabaseService.supabaseHistoricos.insert(historicoSupabaseModel)
        }catch (e: Exception){
            println(e.message)
        }
    }

    override suspend fun getById(id: Int): Result<Historico> {
        return try{
            val responseList = SupabaseService.supabaseHistoricos.select {
                filter { eq("id", id) }
            }.decodeList<HistoricoSupabaseModel>()

            val response = responseList.firstOrNull() ?: return Result.failure(NullPointerException("Historico not found"))

            Result.success(
                Historico(
                    id = response.id!!,
                    name = response.name ?: "",
                    text = response.text ?: "",
                    createdAt = utils.toDate(response.created_at!!),
                    idProjeto = response.id_projeto!!
                )
            )
        }catch (e: Exception){
            Result.failure(e)
        }

    }

    override suspend fun getAllFromProjeto(idProjeto: Int): Result<List<Historico>> {
        return try{
            val responseList = SupabaseService.supabaseHistoricos.select {
                filter { eq("id_projeto", idProjeto) }
            }.decodeList<HistoricoSupabaseModel>()

            val historicos = responseList.map { response ->
                Historico(
                    id = response.id!!,
                    name = response.name ?: "",
                    text = response.text ?: "",
                    createdAt = utils.toDate(response.created_at!!),
                    idProjeto = response.id_projeto!!
                )
            }

            Result.success(historicos)
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun update(id: Int, historico: Historico): Result<Historico> {
        return try{
            val historicoSupabaseModel = HistoricoSupabaseModel(
                id = id,
                name = historico.name,
                text = historico.text,
                id_projeto = historico.idProjeto,
                created_at = utils.fromDate(historico.createdAt),
            )
            val responseList = SupabaseService.supabaseHistoricos.upsert(historicoSupabaseModel){
                select()
            }.decodeList<HistoricoSupabaseModel>()

            val response = responseList.firstOrNull() ?: return Result.failure(IllegalArgumentException("Historico not found"))

            Result.success(
                Historico(
                    id = response.id!!,
                    name = response.name ?: "",
                    text = response.text ?: "",
                    createdAt = utils.toDate(response.created_at!!),
                    idProjeto = response.id_projeto!!
                )
            )
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun delete(id: Int): Boolean {
        return try {
            SupabaseService.supabaseHistoricos.delete {
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