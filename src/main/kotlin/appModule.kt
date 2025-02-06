import aplication.services.FeatureService
import aplication.services.HistoricoService
import aplication.services.ImageService
import aplication.services.ProjetoService
import aplication.validation.feature.FeatureValidatorMock
import aplication.validation.historico.HistoricoValidatorMock
import aplication.validation.projeto.ProjetoValidatorMock
import infrastructure.db.supabase.repositories.SupabaseFeatureRepository
import infrastructure.db.supabase.repositories.SupabaseHistoricoRepository
import infrastructure.db.supabase.repositories.SupabaseImagensRepository
import infrastructure.db.supabase.repositories.SupabaseProjetoRepository
import infrastructure.utils.Utils
import io.github.cdimascio.dotenv.dotenv

object appModule {

    // utils
    val utils = Utils
    val dotenv = dotenv()

    // image
    val imageRepository = SupabaseImagensRepository()
    val imageService = ImageService(imageRepository, utils)

    // feature
    val featureRepository = SupabaseFeatureRepository()
    val featureValidator = FeatureValidatorMock
    val featureService = FeatureService(featureRepository, imageService, featureValidator)

    // projeto
    val projetoRepository = SupabaseProjetoRepository()
    val projetoValidator = ProjetoValidatorMock
    val projetoService = ProjetoService(projetoRepository, imageService, projetoValidator, featureService)

    // historico
    val historicoRepository = SupabaseHistoricoRepository()
    val historicoValidator = HistoricoValidatorMock
    val historicoService = HistoricoService(historicoRepository, historicoValidator)
}