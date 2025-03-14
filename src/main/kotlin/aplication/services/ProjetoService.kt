package aplication.services

import aplication.dto.projeto.ProjetoInputDTO
import aplication.exceptions.ValidationException
import domain.models.Projeto
import aplication.validation.projeto.IProjetoValidator
import appModule.historicoService
import domain.repository.ProjetoRepository
import java.util.*

class ProjetoService(
    private val projetoRepository: ProjetoRepository,
    private val imageService: ImageService,
    private val projetoValidator: IProjetoValidator,
    private val featureService: FeatureService
) {
    suspend fun createProjeto(
        name: String,
        description: String,
        text: String,
        gitHubUrl: String,
        tags: List<String>,
        imageOriginalName: String,
        imageBytes: ByteArray? = null,
        importance: Int?
    ): Result<String> {
        // validação
       val erros = projetoValidator.validarCreate(
           name = name,
           description = description,
           text = text,
           gitHubUrl = gitHubUrl,
           imageOriginalName = imageOriginalName,
           tags = tags,
           imageBytes = imageBytes,
           importance = importance
       )
        if (erros.isNotEmpty()) return Result.failure(ValidationException(erros))

        // adicionar imagem
        val pathResult = imageService.addImagem(imageOriginalName, imageBytes!!)

        // validar imagem adicionada
        if (pathResult.isFailure) return Result.failure(pathResult.exceptionOrNull()!!)
        val imagePath = pathResult.getOrThrow()

        // criar projeto
        val projetoInputDTO = ProjetoInputDTO(
            name = name,
            description = description,
            text = text,
            githubUrl = gitHubUrl,
            tags = tags,
            imagePath = imagePath,
            importance = importance ?: 0
        )
        projetoRepository.create(projetoInputDTO)
        return Result.success("projeto criado com sucesso")
    }

    suspend fun getProjetoById(id: Int): Result<Projeto?>{
        return projetoRepository.getById(id)
    }

    suspend fun getProjetoPresentationModelById(id: Int): Result<Projeto> {
        return projetoRepository.getById(id).mapCatching { projeto ->

            // povoar features
            val featuresResult = featureService.getAllFeaturesFromProjeto(projeto!!.id)
            if(featuresResult.isFailure) throw featuresResult.exceptionOrNull()!!
            val features = featuresResult.getOrThrow()

            // porovar historicos
            val historicosResult = historicoService.getAllPresentationModelFromProjetoId(projeto.id)
            if(historicosResult.isFailure) throw historicosResult.exceptionOrNull()!!
            val historicos = historicosResult.getOrThrow().sortedByDescending { it.createdAt }

            projeto.copy(
                features = features,
                historicos = historicos
                )
        }
    }

    suspend fun getAllProjetos(): Result<List<Projeto>> {
        try {
            val projetosResult = projetoRepository.getAll()
            if (projetosResult.isFailure) throw projetosResult.exceptionOrNull()!!
            val projetos = projetosResult.getOrThrow()
            return Result.success(projetos.sortedByDescending{ it.importance })
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun updateProjeto(
        id: Int,
        description: String,
        name: String,
        text: String,
        gitHubUrl: String,
        tags: List<String>,
        imageOriginalName: String? = null,
        imageBytes: ByteArray? = null,
        importance: Int?
    ): Result<Projeto> {
        // validação
        val erros = projetoValidator.validarUpdate(
            id = id,
            name = name,
            description = description,
            text = text,
            gitHubUrl = gitHubUrl,
            imageOriginalName = imageOriginalName,
            tags = tags,
            imageBytes = imageBytes,
            importance = importance
        )
        if (erros.isNotEmpty()) return Result.failure(ValidationException(erros))

        // adicionar imagem
        if (!imageOriginalName.isNullOrEmpty()) {
            val pathResult = imageService.addImagem(imageOriginalName, imageBytes!!)

            // validar imagem adicionada
            if (pathResult.isFailure) return Result.failure(pathResult.exceptionOrNull()!!)
            val imagePath = pathResult.getOrThrow()

            val projetoResult = getProjetoById(id)
            if(projetoResult.isFailure) return Result.failure(projetoResult.exceptionOrNull()!!)
            if(projetoResult.getOrThrow() == null) return Result.failure(NullPointerException("Projeto not found"))

            val projeto = projetoResult.getOrThrow()!!.copy(
                name = name,
                description = description,
                text = text,
                gitHubUrl = gitHubUrl,
                tags = tags,
                updatedAt = Date(),
                imagePath = imagePath,
                importance = importance ?: 0
            )
            return projetoRepository.update(id, projeto)
        }else{
            val projetoResult = getProjetoById(id)
            if(projetoResult.isFailure) return Result.failure(projetoResult.exceptionOrNull()!!)
            if(projetoResult.getOrThrow() == null) return Result.failure(NullPointerException("Projeto not found"))

            val projeto = projetoResult.getOrThrow()!!.copy(
                name = name,
                description = description,
                text = text,
                gitHubUrl = gitHubUrl,
                tags = tags,
                updatedAt = Date(),
                importance = importance ?: 0
            )
            return projetoRepository.update(id = id, projeto = projeto)
        }
    }

    suspend fun deleteProjeto(id: Int): Boolean {
        return projetoRepository.delete(id)
    }

    suspend fun changeUpdatedAt(id: Int){
        val projetoResult = projetoRepository.getById(id)
        if(projetoResult.isSuccess){
            val projeto = projetoResult.getOrThrow()
            projetoRepository.update(id, projeto!!.copy(updatedAt = Date()))
        }
    }
}