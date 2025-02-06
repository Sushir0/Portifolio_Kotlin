package domain.repository

import aplication.dto.projeto.ProjetoInputDTO
import domain.models.Projeto

interface ProjetoRepository {
    suspend fun create(projetoInputDTO: ProjetoInputDTO)
    suspend fun getById(id: Int): Result<Projeto?>
    suspend fun getAll(): Result<List<Projeto>>
    suspend fun update(id: Int, projeto: Projeto): Result<Projeto>
    suspend fun delete(id: Int): Boolean
}