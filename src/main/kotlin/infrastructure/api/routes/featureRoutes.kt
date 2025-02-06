package infrastructure.api.routes

import aplication.exceptions.ValidationException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import aplication.services.FeatureService
import infrastructure.auth.needAuth
import io.ktor.http.content.*

fun Route.featureRoutes(
    featureService: FeatureService
){

    route("/feature/create"){
        needAuth()
        post{
            val multipart = call.receiveMultipart()
            var name = ""
            var text = ""
            var idProjeto = 0
            var imageOriginalName = ""
            var imageBytes: ByteArray? = null

            multipart.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        when (part.name) {
                            "name" -> name = part.value
                            "text" -> text = part.value
                            "idProjeto" -> idProjeto = part.value.toIntOrNull() ?: 0
                        }
                    }
                    is PartData.FileItem -> {
                        imageOriginalName = part.originalFileName ?: "unknown_file"
                        imageBytes = part.streamProvider().readBytes()
                    }
                    else -> {}
                }
                part.dispose()
            }


            val idFeatureResult = featureService.createFeature(
                name = name,
                text = text,
                idProjeto = idProjeto,
                imageOriginalName = imageOriginalName,
                imageBytes = imageBytes,
                projetoService = appModule.projetoService
            )


            if (idFeatureResult.isSuccess) {
                call.respondRedirect("/")
            } else {
                if (idFeatureResult.exceptionOrNull() is ValidationException){
                    val validationException = idFeatureResult.exceptionOrNull() as ValidationException
                    call.respond(FreeMarkerContent("/feature/create_feature.ftl", mapOf(
                        "error" to validationException.message,
                        "name" to name,
                        "text" to text,
                        "idProjeto" to idProjeto
                    )))
                }else{
                    call.respondText("Failed to create feature", status = HttpStatusCode.InternalServerError)
                }
            }
        }
    }

    route("/projeto/{idProjeto}/feature/create"){
        needAuth()
        get{
            val idProjeto = call.parameters["idProjeto"]?.toIntOrNull() ?: return@get call.respondText("Invalid projeto ID", status = HttpStatusCode.BadRequest)
            call.respond(FreeMarkerContent("/feature/create_feature.ftl", mapOf("idProjeto" to idProjeto)))
        }
    }

    route("/feature/{id}/excluir"){
        needAuth()
        get{
            val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respondText("Invalid feature ID", status = HttpStatusCode.BadRequest)
            if(featureService.deleteFeature(id)){
                call.respondRedirect("/admin")
            } else {
                call.respondText("Failed to delete feature", status = HttpStatusCode.InternalServerError)
            }
        }
    }

    route("/feature/{id}/editar"){
        needAuth()
        get{
            val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respondText("Invalid feature ID",status = HttpStatusCode.BadRequest)
            val featureResult = featureService.getFeatureById(id)
            if(featureResult.isSuccess){
                call.respond(FreeMarkerContent("/feature/editar_feature.ftl", mapOf(
                    "name" to featureResult.getOrThrow().name,
                    "text" to featureResult.getOrThrow().text,
                    "id" to featureResult.getOrThrow().id,
                    "idProjeto" to featureResult.getOrThrow().idProjeto
                )))
            } else {
                call.respondText("Failed to load feature", status = HttpStatusCode.InternalServerError)
            }
        }

        post{
            val id = call.parameters["id"]?.toIntOrNull() ?: return@post call.respondText("Invalid feature ID", status = HttpStatusCode.BadRequest)
            val multipart = call.receiveMultipart()
            var name = ""
            var text = ""
            var imageOriginalName = ""
            var imageBytes: ByteArray? = null

            multipart.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        when (part.name) {
                            "name" -> name = part.value
                            "text" -> text = part.value
                        }
                    }
                    is PartData.FileItem -> {
                        if (!part.originalFileName.isNullOrEmpty()) {
                            imageOriginalName = part.originalFileName ?: "unknown_file"
                            imageBytes = part.streamProvider().readBytes()
                        }
                    }
                    else -> {}
                }
                part.dispose()
            }
            val updateResult = featureService.updateFeature(
                id = id,
                name = name,
                text = text,
                imageOriginalName = imageOriginalName,
                imageBytes = imageBytes
            )
            if (updateResult.isSuccess) {
                call.respondRedirect("/projeto/${updateResult.getOrThrow().idProjeto}")
            } else {
                if (updateResult.exceptionOrNull() is ValidationException){
                    val validationException = updateResult.exceptionOrNull() as ValidationException
                    call.respond(FreeMarkerContent("/feature/editar_feature.ftl", mapOf(
                        "error" to validationException.message,
                        "name" to name,
                        "text" to text,
                        "id" to id
                    )))
                }else{
                    call.respondText("Failed to update feature", status = HttpStatusCode.InternalServerError)
                }
            }
        }
    }

}