package infrastructure.db.supabase.repositories

import domain.repository.ImageRepository
import infrastructure.db.supabase.SupabaseService.supabaseImages

class SupabaseImagensRepository(): ImageRepository {
    override suspend fun storeImage(fileName: String, imageBytes: ByteArray): Result<String> {
        supabaseImages.upload(fileName, imageBytes)
        val publicUrl = supabaseImages.publicUrl(fileName)
        return Result.success(publicUrl)

    }

    override suspend fun getImage(imageName: String): Result<ByteArray> {
        TODO("Not yet implemented")
    }
}