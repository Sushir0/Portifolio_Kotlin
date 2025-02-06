package aplication.validation.historico

interface IHistoricoValidator {
    fun validarCreate(name: String, text: String): List<String>
    fun validarUpdate(name: String, text: String): List<String>
}