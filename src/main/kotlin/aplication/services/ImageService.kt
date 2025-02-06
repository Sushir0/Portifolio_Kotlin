package aplication.services

import domain.repository.ImageRepository
import infrastructure.utils.Utils

class ImageService(
    private val imageRepository: ImageRepository,
    private val utils: Utils
) {
    suspend fun addImagem(
        originalFileName: String,
        imageBytes: ByteArray
    ): Result<String> {
        val hashedName = utils.hashFileName(originalFileName)

        val fileExtension = originalFileName.substringAfterLast('.', "")
        val finalFileName = if (fileExtension.isNotEmpty()) {
            "$hashedName.$fileExtension"
        } else {
            hashedName
        }
        return imageRepository.storeImage(finalFileName, imageBytes)
    }

    suspend fun getImage(imageName: String): Result<ByteArray> {
        return imageRepository.getImage(imageName)
    }
}