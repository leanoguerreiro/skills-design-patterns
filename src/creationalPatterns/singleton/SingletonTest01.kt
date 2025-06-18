package creationalPatterns.singleton

fun main () {
    println(">>> Acessando a configuração pela primeira vez (na tela principal)...")
    AppConfig.exibirConfiguracoes()

    println(">>> Usuário ativou o Modo Noturno...")
    AppConfig.modoNoturnoAtivado = true

    println(">>> Acessando a configuração novamente (ainda na tela principal)...")
    AppConfig.exibirConfiguracoes()

    simularAcessoDeOutraTela()
}

fun simularAcessoDeOutraTela() {
    println(">>> Acessando a configuração de uma tela diferente...")

    AppConfig.exibirConfiguracoes()

    println(">>> Mudando o idioma para inglês...")
    AppConfig.idioma = "en-US"
    AppConfig.exibirConfiguracoes()
}