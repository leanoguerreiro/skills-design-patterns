package creationalPatterns.singleton

object AppConfig {
    var modoNoturnoAtivado: Boolean = false
    var idioma: String = "pt-BR"

    fun exibirConfiguracoes() {
        println("----- Configurações do App -----")
        val statusModoNoturno = if ( modoNoturnoAtivado) "Ativado" else "Desativado"
        println("Status Modo Noturno: $statusModoNoturno")
        println("Idioma: $idioma")
        println("ID do Objeto: ${this.hashCode()}")
        println("--------------------------------")
        println()
    }
}