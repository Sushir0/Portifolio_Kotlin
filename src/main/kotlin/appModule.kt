import aplication.services.*
import aplication.validation.feature.FeatureValidatorMock
import aplication.validation.historico.HistoricoValidatorMock
import aplication.validation.projeto.ProjetoValidatorMock
import infrastructure.db.supabase.repositories.*
import infrastructure.utils.Utils
import io.github.cdimascio.dotenv.dotenv

object appModule {

    // utils
    val utils = Utils
    val dotenv = dotenv{ ignoreIfMissing = true}

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

    // visitLog
    val visitLogRepository = SupabaseVisitLogRepository()
    val visitLogService = VisitLogService(visitLogRepository)

}