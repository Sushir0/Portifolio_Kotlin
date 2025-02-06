package domain.repository

interface ImageRepository {
    suspend fun storeImage(
        fileName: String,
        imageBytes: ByteArray
    ): Result<String>

    suspend fun getImage(imageName: String): Result<ByteArray>
}