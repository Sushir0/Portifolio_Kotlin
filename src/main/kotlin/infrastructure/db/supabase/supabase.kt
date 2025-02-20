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
    val tableVisitLog = System.getenv("SUPABASE_TABLE_VISIT_LOGS") ?: dotenv["SUPABASE_TABLE_VISIT_LOGS"] ?: throw IllegalArgumentException("SUPABASE_TABLE_VISIT_LOGS environment variable not set")
    val viewCountUniqueIp = System.getenv("SUPABASE_COUNT_UNIQUE_IP_VIEW") ?: dotenv["SUPABASE_COUNT_UNIQUE_IP_VIEW"] ?: throw IllegalArgumentException("SUPABASE_COUNT_UNIQUE_IP_VIEW environment variable not set")
    val viewTotalCount = System.getenv("SUPABASE_COUNT_TOTAL_VIEW") ?: dotenv["SUPABASE_COUNT_TOTAL_VIEW"] ?: throw IllegalArgumentException("SUPABASE_COUNT_TOTAL_VIEW environment variable not set")
    val viewGraficoTotalSemanal = System.getenv("SUPABASE_GRAFICO_TOTAL_SEMANAL_VIEW") ?: dotenv["SUPABASE_GRAFICO_TOTAL_SEMANAL_VIEW"] ?: throw IllegalArgumentException("SUPABASE_GRAFICO_TOTAL_SEMANAL_VIEW environment variable not set")
    val viewGraficoTotalMensal = System.getenv("SUPABASE_GRAFICO_TOTAL_MENSAL_VIEW") ?: dotenv["SUPABASE_GRAFICO_TOTAL_MENSAL_VIEW"] ?: throw IllegalArgumentException("SUPABASE_GRAFICO_TOTAL_MENSAL_VIEW environment variable not set")
    val viewGraficoTotalAnual = System.getenv("SUPABASE_GRAFICO_TOTAL_ANUAL_VIEW") ?: dotenv["SUPABASE_GRAFICO_TOTAL_ANUAL_VIEW"] ?: throw IllegalArgumentException("SUPABASE_GRAFICO_TOTAL_ANUAL_VIEW environment variable not set")
    val viewGraficoUniqueIpSemanal = System.getenv("SUPABASE_GRAFICO_UNIQUE_IP_SEMANAL_VIEW") ?: dotenv["SUPABASE_GRAFICO_UNIQUE_IP_SEMANAL_VIEW"] ?: throw IllegalArgumentException("SUPABASE_GRAFICO_UNIQUE_IP_SEMANAL_VIEW environment variable not set")
    val viewGraficoUniqueIpMensal = System.getenv("SUPABASE_GRAFICO_UNIQUE_IP_MENSAL_VIEW") ?: dotenv["SUPABASE_GRAFICO_UNIQUE_IP_MENSAL_VIEW"] ?: throw IllegalArgumentException("SUPABASE_GRAFICO_UNIQUE_IP_MENSAL_VIEW environment variable not set")
    val viewGraficoUniqueIpAnual = System.getenv("SUPABASE_GRAFICO_UNIQUE_IP_ANUAL_VIEW") ?: dotenv["SUPABASE_GRAFICO_UNIQUE_IP_ANUAL_VIEW"] ?: throw IllegalArgumentException("SUPABASE_GRAFICO_UNIQUE_IP_ANUAL_VIEW environment variable not set")

    val supabaseProjetos = supabase.postgrest[tableProjetos]
    val supabaseHistoricos = supabase.postgrest[tableHistoricos]
    val supabaseFeatures = supabase.postgrest[tableFeatures]
    val supabaseVisitLogs = supabase.postgrest[tableVisitLog]
    val supabaseImages = supabase.storage[storageImagens]

    val supabaseViewCountUniqueIp = supabase.postgrest[viewCountUniqueIp]
    val supabaseViewCountTotalVisits = supabase.postgrest[viewTotalCount]
    val supabaseViewGraficoTotalSemanal = supabase.postgrest[viewGraficoTotalSemanal]
    val supabaseViewGraficoTotalMensal = supabase.postgrest[viewGraficoTotalMensal]
    val supabaseViewGraficoTotalAnual = supabase.postgrest[viewGraficoTotalAnual]
    val supabaseViewGraficoUniqueIpSemanal = supabase.postgrest[viewGraficoUniqueIpSemanal]
    val supabaseViewGraficoUniqueIpMensal = supabase.postgrest[viewGraficoUniqueIpMensal]
    val supabaseViewGraficoUniqueIpAnual = supabase.postgrest[viewGraficoUniqueIpAnual]
}