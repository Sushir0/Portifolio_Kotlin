package aplication.services

import aplication.dto.feature.FeatureInputDTO
import aplication.validation.feature.IFeatureValidator
import domain.models.Feature
import domain.repository.FeatureRepository
import java.util.*

class FeatureService(
    private val FeatureRepository: FeatureRepository,
    private val imageService: ImageService,
    private val featureValidator: IFeatureValidator,
) {
    suspend fun createFeature(
        name: String,
        text: String,
        idProjeto: Int,
        imageOriginalName: String,
        imageBytes: ByteArray? = null,
        projetoService: ProjetoService
    ): Result<String> {
        // validação
        val erros = featureValidator.validarCreate(
            name = name,
            text = text,
            idProjeto = idProjeto,
            imageOriginalName = imageOriginalName,
            imageBytes = imageBytes
        )
        if (erros.isNotEmpty()) return Result.failure(IllegalArgumentException(erros.joinToString(",")))

        // adicionar imagem
        val pathResult = imageService.addImagem(imageOriginalName, imageBytes!!)

        // validar imagem adicionada
        if (pathResult.isFailure) return Result.failure(pathResult.exceptionOrNull()!!)
        val imagePath = pathResult.getOrThrow()

        // criar feature
        val featureInputDTO = FeatureInputDTO(
            name = name,
            text = text,
            idProjeto = idProjeto,
            imagePath = imagePath,
        )

        projetoService.changeUpdatedAt(idProjeto)
        FeatureRepository.create(featureInputDTO)
        return Result.success("feature criada com sucesso")
    }

    suspend fun getFeatureById(id: Int): Result<Feature> {
        return FeatureRepository.getById(id)
    }

    suspend fun getAllFeaturesFromProjeto(idProjeto: Int): Result<List<Feature>> {
        return FeatureRepository.getAllFromProjeto(idProjeto)
    }

    suspend fun updateFeature(
        id: Int,
        name: String,
        text: String,
        imageOriginalName: String? = null,
        imageBytes: ByteArray? = null
        ): Result<Feature> {
        // validação
        val erros = featureValidator.validarUpdate(
            id = id,
            name = name,
            text = text,
            imageOriginalName = imageOriginalName,
            imageBytes = imageBytes
        )
        if (erros.isNotEmpty()) return Result.failure(IllegalArgumentException(erros.joinToString(",")))

        val featureResult = getFeatureById(id)
        if(featureResult.isFailure) return Result.failure(featureResult.exceptionOrNull()!!)
        val feature = featureResult.getOrThrow()

        // adicionar imagem
        val pathResult = if (!imageOriginalName.isNullOrEmpty()) {
            imageService.addImagem(imageOriginalName, imageBytes!!)
        } else {
            Result.success(feature.imagePath)
        }

        // validar imagem adicionada
        if (pathResult.isFailure) return Result.failure(pathResult.exceptionOrNull()!!)
        val imagePath = pathResult.getOrThrow()

        val featureUpdate = feature.copy(
            name = name,
            text = text,
            imagePath = imagePath
        )
        return FeatureRepository.update(id, featureUpdate)
    }

    suspend fun deleteFeature(id: Int): Boolean {
        return FeatureRepository.delete(id)
    }
}