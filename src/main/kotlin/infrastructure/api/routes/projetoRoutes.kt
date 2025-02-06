package infrastructure.api.routes

import aplication.exceptions.ValidationException
import aplication.services.AuthService
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import aplication.services.ProjetoService
import infrastructure.auth.needAuth

fun Route.projetoRoutes(projetoService: ProjetoService) {

    route("/projeto/create"){
        needAuth()
        get {

            call.respond(FreeMarkerContent("projeto/create_projeto.ftl", emptyMap<String, Any>()))
        }

        post {
            val multipart = call.receiveMultipart()
            var name = ""
            var description = ""
            var text = ""
            var githubUrl = ""
            var tags = listOf<String>()
            var imagePath = ""
            var imageOriginalName = ""
            var imageBytes: ByteArray? = null


            multipart.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        when (part.name) {
                            "name" -> name = part.value
                            "description" -> description = part.value
                            "text" -> text = part.value
                            "githubUrl" -> githubUrl = part.value
                            "tags" -> tags = part.value.split(",")
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
            val idProjetoResult = projetoService.createProjeto(
                name = name,
                description = description,
                text = text,
                gitHubUrl = githubUrl,
                imageOriginalName = imageOriginalName,
                imageBytes = imageBytes,
                tags = tags
            )
            if (idProjetoResult.isSuccess) {
                call.respondRedirect("/projeto/${idProjetoResult.getOrThrow()}")
            }else {
                if(idProjetoResult.exceptionOrNull() is ValidationException){
                    val validationException = idProjetoResult.exceptionOrNull() as ValidationException
                        call.respond(FreeMarkerContent("projeto/create_projeto.ftl", mapOf(
                            "error" to validationException.message,
                            "name" to name,
                            "description" to description,
                            "text" to text,
                            "githubUrl" to githubUrl,
                            "tags" to tags.joinToString(",")
                        )))
                }else{
                    call.respondText("Failed to create project", status = HttpStatusCode.InternalServerError)
                }

            }
        }
    }


    route("/projeto/{id}/editar"){
        needAuth()
        get{
            val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respondText("Invalid project ID", status = HttpStatusCode.BadRequest)
            val projetoResult = projetoService.getProjetoPresentationModelById(id)
            if (projetoResult.isSuccess){
                call.respond(FreeMarkerContent("projeto/editar_projeto.ftl", mapOf(
                    "name" to projetoResult.getOrThrow().name,
                    "description" to projetoResult.getOrThrow().description,
                    "text" to projetoResult.getOrThrow().text,
                    "githubUrl" to projetoResult.getOrThrow().gitHubUrl,
                    "tags" to projetoResult.getOrThrow().tags.joinToString(",")
                    )))
            } else {
                call.respondText("Failed to load project", status = HttpStatusCode.InternalServerError)
            }
        }

        // editar projeto
        post {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@post call.respondText("Invalid project ID", status = HttpStatusCode.BadRequest)
            val multipart = call.receiveMultipart()
            var name = ""
            var description = ""
            var text = ""
            var githubUrl = ""
            var tags = listOf<String>()
            var imageOriginalName = ""
            var imageBytes: ByteArray? = null

            multipart.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        when (part.name) {
                            "name" -> name = part.value
                            "description" -> description = part.value
                            "text" -> text = part.value
                            "githubUrl" -> githubUrl = part.value
                            "tags" -> tags = part.value.split(",")
                        }
                    }
                    is PartData.FileItem -> {
                        // caso a foto seja não seja adicionada, permanecer a mesma foto
                        if (!part.originalFileName.isNullOrEmpty()) {
                            imageOriginalName = part.originalFileName ?: "unknown_file"
                            imageBytes = part.streamProvider().readBytes()
                        }
                    }
                    else -> {}
                }
                part.dispose()
            }
            val updateResult = projetoService.updateProjeto(
                id = id,
                name = name,
                description = description,
                text = text,
                gitHubUrl = githubUrl,
                imageOriginalName = imageOriginalName,
                imageBytes = imageBytes,
                tags = tags
            )
            if (updateResult.isSuccess) {
                call.respondRedirect("/projeto/$id")
            } else {
                if(updateResult.exceptionOrNull() is ValidationException){
                    val validationException = updateResult.exceptionOrNull() as ValidationException
                    call.respond(FreeMarkerContent("projeto/editar_projeto.ftl", mapOf(
                        "error" to validationException.message,
                        "name" to name,
                        "description" to description,
                        "text" to text,
                        "githubUrl" to githubUrl,
                        "tags" to tags.joinToString(",")
                    )))
                }else{
                    call.respondText("Failed to update project", status = HttpStatusCode.InternalServerError)
                }
            }
        }
    }

    get("/projeto/{id}") {
        val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respondText("Invalid project ID", status = HttpStatusCode.BadRequest)
        val projetoResult = projetoService.getProjetoPresentationModelById(id) ?: return@get call.respondText("Project not found", status = HttpStatusCode.NotFound)
        if (projetoResult.isSuccess) {
            val authService = AuthService(call)
            val isAuthenticated = authService.isAuthenticated()

            call.respond(FreeMarkerContent("projeto/projeto.ftl", mapOf("projeto" to projetoResult.getOrThrow(), "isAuthenticated" to isAuthenticated)))
        } else {
            call.respondText("Failed to load project", status = HttpStatusCode.InternalServerError)
        }
    }


    route("/projeto/{id}/excluir"){
        needAuth()
        get {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respondText("Invalid project ID", status = HttpStatusCode.BadRequest)
            if (projetoService.deleteProjeto(id)) {
                call.respondRedirect("/admin")
            } else {
                call.respondText("Failed to delete project", status = HttpStatusCode.InternalServerError)
            }
        }
    }






}