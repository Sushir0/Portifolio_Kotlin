package domain.repository

import aplication.dto.historico.HistoricoInputDTO
import domain.models.Historico

interface HistoricoRepository {
    suspend fun create(historicoInputDTO: HistoricoInputDTO)
    suspend fun getById(id: Int): Result<Historico>
    suspend fun getAllFromProjeto(idProjeto: Int): Result<List<Historico>>
    suspend fun update(id: Int, historico: Historico): Result<Historico>
    suspend fun delete(id: Int): Boolean
}