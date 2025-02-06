package aplication.validation.historico

object HistoricoValidatorMock :IHistoricoValidator{
    override fun validarCreate(name: String, text: String): List<String> {
        return emptyList()
    }

    override fun validarUpdate(name: String, text: String): List<String> {
        return emptyList()
    }

}