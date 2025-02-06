package infrastructure.api.routes

import aplication.exceptions.ValidationException
import aplication.services.HistoricoService
import appModule.projetoService
import infrastructure.auth.needAuth
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.historicoRoutes(
    historicoService: HistoricoService
){

    route("/historico/create"){
        needAuth()
        post{

            val parameters = call.receiveParameters()
            val name = parameters["name"] ?: ""
            val text = parameters["text"] ?: ""
            val idProjeto = parameters["idProjeto"]?.toIntOrNull() ?: 0

            val createResult = historicoService.createHistorico(name, text, idProjeto, projetoService)

            if(createResult.isSuccess){
                call.respondRedirect("/")
            } else {
                if(createResult.exceptionOrNull() is ValidationException){
                    val validationException = createResult.exceptionOrNull() as ValidationException
                    call.respond(FreeMarkerContent("/historico/create_historico.ftl", mapOf(
                        "error" to validationException.message,
                        "name" to name,
                        "text" to text,
                        "idProjeto" to idProjeto
                    )))
                }else{
                    call.respondText("Failed to create historico", status = HttpStatusCode.InternalServerError)
                }
            }
        }
    }

    route("/projeto/{idProjeto}/historico/create"){
        needAuth()
        get{
            val idProjeto = call.parameters["idProjeto"]?.toIntOrNull() ?: return@get call.respondText("Invalid projeto ID", status = HttpStatusCode.BadRequest)
            call.respond(FreeMarkerContent("/historico/create_historico.ftl", mapOf("idProjeto" to idProjeto)))
        }
    }

    route("/historico/{id)/excluir"){
        needAuth()
        get{
            val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respondText("Invalid historico ID", status = HttpStatusCode.BadRequest)
            if(historicoService.deleteHistoricLog(id)){
                call.respondRedirect("/admin")
            } else {
                call.respondText("Failed to delete historico", status = HttpStatusCode.InternalServerError)
            }
        }
    }

    route("historico/{id}/editar"){
        needAuth()
        get{
            val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respondText("Invalid historico ID",status = HttpStatusCode.BadRequest)
            val historicoResult = historicoService.getById(id)
            if(historicoResult.isSuccess){
                call.respond(FreeMarkerContent("/historico/editar_historico.ftl", mapOf(
                    "id" to historicoResult.getOrThrow().id,
                    "name" to historicoResult.getOrThrow().name,
                    "text" to historicoResult.getOrThrow().text
                )))
            } else {
                call.respondText("Failed to load historico", status = HttpStatusCode.InternalServerError)
            }
        }

        post{
            val id = call.parameters["id"]?.toIntOrNull() ?: return@post call.respondText("Invalid historico ID", status = HttpStatusCode.BadRequest)
            val parameters = call.receiveParameters()
            val name = parameters["name"] ?: ""
            val text = parameters["text"] ?: ""

            val updateResult = historicoService.updateHistorico(id, name, text)
            if (updateResult.isSuccess) {
                call.respondRedirect("/projeto/${updateResult.getOrThrow().idProjeto}")
            } else {
                if(updateResult.exceptionOrNull() is ValidationException){
                    val validationException = updateResult.exceptionOrNull() as ValidationException
                    call.respond(FreeMarkerContent("/historico/editar_historico.ftl", mapOf(
                        "error" to validationException.message,
                        "id" to id,
                        "name" to name,
                        "text" to text
                    )))
                }else{
                    call.respondText("Failed to update historico", status = HttpStatusCode.InternalServerError)
                }
            }
        }
    }

}