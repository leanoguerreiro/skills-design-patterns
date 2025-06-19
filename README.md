# Estudo de Padrões de Projeto (Design Patterns) em Kotlin

Este repositório contém implementações de exemplos práticos de Padrões de Projeto de Software (Design Patterns) utilizando a linguagem de programação Kotlin. O objetivo é demonstrar como cada padrão pode ser aplicado para resolver problemas comuns de design de software de forma elegante e eficiente.

O projeto está organizado por categorias de padrões, começando com os **Padrões Criacionais**.

**Licença:** Este projeto está licenciado sob a [GNU General Public License v3.0](LICENSE).

## Estrutura do Projeto

O código-fonte está localizado no diretório `src` e segue a seguinte estrutura:

```
└── src/
    └── creationalPatterns/
        ├── builder/
        │   ├── BuilderTest01.kt
        │   └── Person.kt
        ├── factory/
        │   ├── Country.kt
        │   ├── Currency.kt
        │   ├── CurrencyFactory.kt
        │   ├── FactoryTest01.kt
        │   ├── Real.kt
        │   └── UsDollar.kt
        └── singleton/
            ├── AppConfig.kt
            └── SingletonTest01.kt
```

## Padrões de Projeto Implementados

### 1. Padrões Criacionais (Creational Patterns)

Esses padrões lidam com os mecanismos de criação de objetos, tentando criar objetos de uma maneira adequada a cada situação.

---

#### **Singleton**

O padrão Singleton garante que uma classe tenha apenas uma instância e fornece um ponto de acesso global a essa instância. Em Kotlin, isso é alcançado de forma idiomática e segura (thread-safe) usando a declaração `object`.

**Implementação:**

O objeto `AppConfig` representa as configurações globais de um aplicativo. Qualquer parte do código que acessar `AppConfig` estará, garantidamente, acessando a mesma e única instância.

* `src/creationalPatterns/singleton/AppConfig.kt`
    ```kotlin
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
    ```

**Exemplo de Uso:**

O arquivo `SingletonTest01.kt` simula o acesso e a modificação do objeto `AppConfig` a partir de diferentes telas de um aplicativo, demonstrando que a instância e seus dados são compartilhados.

* `src/creationalPatterns/singleton/SingletonTest01.kt`
    ```kotlin
    package creationalPatterns.singleton

    fun main () {
        println(">>> Acessando a configuração pela primeira vez (na tela principal)...")
        AppConfig.exibirConfiguracoes()

        println(">>> Usuário ativou o Modo Noturno...")
        AppConfig.modoNoturnoAtivado = true

        simularAcessoDeOutraTela()

        println(">>> Acessando a configuração novamente (ainda na tela principal)...")
        AppConfig.exibirConfiguracoes()
    }

    fun simularAcessoDeOutraTela() {
        println(">>> Acessando a configuração de uma tela diferente...")
        AppConfig.exibirConfiguracoes()
    }
    ```
---

#### **Builder**

O padrão Builder é usado para construir um objeto complexo passo a passo. Ele permite a criação de diferentes representações de um objeto usando o mesmo processo de construção. É especialmente útil quando um objeto pode ter muitos parâmetros de construtor, alguns opcionais e outros obrigatórios.

**Implementação:**

A classe `Person` tem um construtor privado e uma classe interna `Builder`. O `Builder` possui métodos "setter" fluentes (`apply`) para configurar os atributos e um método `build()` que retorna a instância final e imutável de `Person`.

* `src/creationalPatterns/builder/Person.kt`
    ```kotlin
    package creationalPatterns.builder

    class Person private constructor(
        val firstName: String?,
        val lastName: String?,
        val username: String?,
        val email: String?,
    ) {
        class Builder {
            private var firstName: String? = null
            private var lastName: String? = null
            private var username: String? = null
            private var email: String? = null

            fun setFirstName(firstName: String?) = apply { this.firstName = firstName }
            fun setLastName(lastName: String?) = apply {this.lastName = lastName }
            fun setUserName(username: String?) = apply { this.username = username }
            fun setEmail(email: String?) = apply {this.email = email}

            fun build(): Person {
                return Person(firstName, lastName, username, email)
            }
        }
    }
    ```

**Exemplo de Uso:**

O arquivo `BuilderTest01.kt` mostra como é simples e legível criar uma instância de `Person` usando a classe `Builder`.

* `src/creationalPatterns/builder/BuilderTest01.kt`
    ```kotlin
    package creationalPatterns.builder

    fun main() {
        val person1 = Person.Builder()
            .setFirstName("Leano")
            .setLastName("Baba")
            .setUserName("808kojisosa")
            .setEmail("kojisosa@gmail.com")
            .build()

        println(person1.toString())
    }
    ```
---

#### **Factory (Simple Factory)**

O padrão Factory define uma interface para criar um objeto, mas deixa as subclasses decidirem qual classe instanciar. A implementação neste projeto é um "Simple Factory", que encapsula a lógica de criação de objetos em uma única classe, centralizando a decisão de qual objeto criar.

**Implementação:**

O `CurrencyFactory` é um objeto que fornece um método estático `newCurrency`. Com base no `Country` fornecido, ele instancia e retorna a classe de moeda (`Currency`) apropriada (`Real` ou `UsDollar`). Isso desacopla o cliente da lógica de criação e das classes concretas de moeda.

* `src/creationalPatterns/factory/CurrencyFactory.kt`
    ```kotlin
    package creationalPatterns.factory

    object CurrencyFactory {
        fun newCurrency(country: Country) : Currency {
            return when (country) {
                Country.BRAZIL -> Real()
                Country.USA -> UsDollar()
                else -> throw IllegalArgumentException("Unknown currency code")
            }
        }
    }
    ```
* `src/creationalPatterns/factory/Currency.kt` (Interface)
    ```kotlin
    package creationalPatterns.factory

    interface Currency {
        fun getSymbol(): String
    }
    ```
* `src/creationalPatterns/factory/Real.kt` (Classe Concreta)
    ```kotlin
    package creationalPatterns.factory

    class Real : Currency {
        override fun getSymbol(): String {
            return "R$"
        }
    }
    ```

**Exemplo de Uso:**

O arquivo `FactoryTest01.kt` utiliza a fábrica para obter objetos de moeda sem conhecer suas implementações concretas, apenas o país.

* `src/creationalPatterns/factory/FactoryTest01.kt`
    ```kotlin
    package creationalPatterns.factory

    fun main () {
        val currencyBrazil = CurrencyFactory.newCurrency(Country.BRAZIL)
        println("Moeda do ${Country.BRAZIL}: ${currencyBrazil.getSymbol()}")

        val currencyUSA = CurrencyFactory.newCurrency(Country.USA)
        println("Moeda do ${Country.USA}: ${currencyUSA.getSymbol()}")
    }
    ```
---

## Como Executar os Testes

Para executar os exemplos:
1.  Clone o repositório.
2.  Abra o projeto em uma IDE que suporte Kotlin, como o IntelliJ IDEA.
3.  Navegue até os arquivos de teste (ex: `SingletonTest01.kt`, `BuilderTest01.kt`, `FactoryTest01.kt`).
4.  Clique no ícone de "play" ao lado da função `main` para executar o exemplo.

---

## Referências

1.  **Maratona Java - DevDojo:** Repositório com vasto material de estudo sobre Java, que serviu como inspiração para a estrutura de aprendizado.
    * [https://github.com/devdojobr/maratona-java-virado-no-jiraya/tree/master](https://github.com/devdojobr/maratona-java-virado-no-jiraya/tree/master)

2.  **Documentação Oficial do Kotlin:** A fonte primária para aprender sobre a sintaxe, os recursos e os conceitos idiomáticos da linguagem Kotlin.
    * [https://kotlinlang.org/](https://kotlinlang.org/)

3.  **Refactoring.Guru:** Um excelente recurso visual e textual para aprender sobre Padrões de Projeto, princípios de refatoração e SOLID de forma clara e com exemplos em várias linguagens.
    * [https://refactoring.guru/](https://refactoring.guru/)