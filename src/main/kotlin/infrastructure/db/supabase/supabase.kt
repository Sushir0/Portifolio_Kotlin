package infrastructure.db.supabase

import appModule.dotenv
import infrastructure.db.supabase.repositories.SupabaseProjetoRepository
import infrastructure.utils.Utils
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.runBlocking

object SupabaseService{
     private val supabase = createSupabaseClient(
        supabaseUrl = System.getenv("SUPABASE_URL") ?: dotenv["SUPABASE_URL"] ?: throw IllegalArgumentException("SUPABASE_URL environment variable not set"),
        supabaseKey = System.getenv("SUPABASE_KEY") ?: dotenv["SUPABASE_KEY"] ?: throw IllegalArgumentException("SUPABASE_KEY environment variable not set")
    ){
        install(Postgrest)
        install(Storage)
    }

    val tableProjetos = System.getenv("SUPABASE_TABLE_PROJETOS") ?: dotenv["SUPABASE_TABLE_PROJETOS"] ?: throw IllegalArgumentException("SUPABASE_TABLE_PROJETOS environment variable not set")
    val tableHistoricos = System.getenv("SUPABASE_TABLE_HISTORICOS") ?: dotenv["SUPABASE_TABLE_HISTORICOS"] ?: throw IllegalArgumentException("SUPABASE_TABLE_HISTORICOS environment variable not set")
    val tableFeatures = System.getenv("SUPABASE_TABLE_FEATURES") ?: dotenv["SUPABASE_TABLE_FEATURES"] ?: throw IllegalArgumentException("SUPABASE_TABLE_FEATURES environment variable not set")
    val storageImagens = System.getenv("SUPABASE_STORAGE_IMAGENS") ?: dotenv["SUPABASE_STORAGE_IMAGENS"] ?: throw IllegalArgumentException("SUPABASE_STORAGE_IMAGENS environment variable not set")


    val supabaseProjetos = supabase.postgrest[tableProjetos]
    val supabaseHistoricos = supabase.postgrest[tableHistoricos]
    val supabaseFeatures = supabase.postgrest[tableFeatures]
    val supabaseImages = supabase.storage[storageImagens]

}