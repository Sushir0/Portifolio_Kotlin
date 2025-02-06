package aplication.services

import aplication.dto.historico.HistoricoInputDTO
import aplication.validation.historico.IHistoricoValidator
import domain.models.Historico
import domain.repository.HistoricoRepository
import java.util.*

class HistoricoService(
    private val historicoRepository: HistoricoRepository,
    private val historicoValidator: IHistoricoValidator,
) {
    suspend fun createHistorico(
        name: String,
        text: String,
        idProjeto: Int,
        projetoService: ProjetoService
    ): Result<String> {
        // validação
        val errors = historicoValidator.validarCreate(
            name = name,
            text = text
        )
        if(errors.isNotEmpty()) return Result.failure(IllegalArgumentException(errors.joinToString(",")))

        //criar historico
        val historicoInputDTO = HistoricoInputDTO(
            name = name,
            text = text,
            idProjeto = idProjeto
        )

        projetoService.changeUpdatedAt(idProjeto)
        historicoRepository.create(historicoInputDTO)
        return Result.success("historico criado com sucesso")
    }

    suspend fun getById(
        id: Int
    ): Result<Historico> {
        return historicoRepository.getById(id)
    }

    suspend fun getAllPresentationModelFromProjetoId(
        idProjeto: Int
    ): Result<List<Historico>> {
        return historicoRepository.getAllFromProjeto(idProjeto)
    }


    suspend fun updateHistorico(
        id: Int,
        name: String,
        text: String
    ): Result<Historico> {
        // validação
        val errors = historicoValidator.validarUpdate(
            name = name,
            text = text
        )
        if(errors.isNotEmpty()) return Result.failure(IllegalArgumentException(errors.joinToString(",")))

        val historicoResult = getById(id)
        if(historicoResult.isFailure) return Result.failure(historicoResult.exceptionOrNull()!!)

        val newHistorico = historicoResult.getOrThrow().copy(
            name = name,
            text = text
        )

        return historicoRepository.update(id, newHistorico)
    }

    suspend fun deleteHistoricLog(id: Int): Boolean {
        return historicoRepository.delete(id)
    }
}