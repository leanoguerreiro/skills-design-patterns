# Estudo de Padrões de Projeto (Design Patterns) em Kotlin

Este repositório contém implementações de exemplos práticos de Padrões de Projeto de Software (Design Patterns) utilizando a linguagem de programação Kotlin. O objetivo é demonstrar como cada padrão pode ser aplicado para resolver problemas comuns de design de software de forma elegante e eficiente.

O projeto está organizado por categorias de padrões, começando com os **Padrões Criacionais** e seguindo para os **Padrões Estruturais**.

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

### 2. Padrões Estruturais (Structural Patterns)

Esses padrões se concentram em como classes e objetos podem ser compostos para formar estruturas maiores, mantendo essas estruturas flexíveis e eficientes.

---

#### **Adapter**

O padrão Adapter permite que interfaces incompatíveis trabalhem juntas. Ele atua como uma ponte entre duas interfaces, convertendo a interface de uma classe na interface que o cliente espera.

**Implementação:**

Neste exemplo, temos uma interface `Pato` e uma interface `Peru`. Um `Peru` não pode ser usado onde um `Pato` é esperado. A classe `PeruAdapter` envolve um objeto `Peru` e implementa a interface `Pato`. Seus métodos `quack()` e `fly()` delegam as chamadas para os métodos correspondentes do `Peru` (`gooble()` e `fly()`), adaptando o comportamento.

* `src/structuralPatterns/adapter/PeruAdapter.kt`

    ```kotlin
    package structuralPatterns.adapter

    class PeruAdapter(private val peru: Peru) : Pato {
        override fun quack() {
            peru.gooble() // Adapta o grasnar para o som do peru
        }

        override fun fly() {
            println("Simulando um voo longo: ")
            (0..4).forEach { i ->
                peru.fly() // Peru voa curtas distâncias, então simulamos um voo longo
            }
        }
    }
    ```

**Exemplo de Uso:**

O arquivo `AdapterTest01.kt` mostra uma função `testarPato` que só aceita objetos `Pato`. Graças ao `PeruAdapter`, podemos passar um peru adaptado para essa função.

* `src/structuralPatterns/adapter/AdapterTest01.kt`

    ```kotlin
    fun main() {
        val peruSelvagem = PeruSelvagem()
        val peruAdapter: Pato = PeruAdapter(peruSelvagem)

        println("----- Testando o Peru Adaptado para Pato -----")
        testarPato(peruAdapter)
    }

    fun testarPato(pato: Pato) {
        println("O 'pato' faz: ")
        pato.quack()
        pato.fly()
    }
    ```

---

#### **Decorator**

O padrão Decorator anexa responsabilidades adicionais a um objeto dinamicamente. Os decoradores fornecem uma alternativa flexível à herança para estender a funcionalidade.

**Implementação:**

O projeto contém dois exemplos:
1.  **Café:** Um `SimpleCoffee` pode ser "decorado" com `WithMilk` ou `WithWhippedCream`. Cada decorador adiciona seu próprio custo e descrição ao envolver o objeto de café original.
2.  **Processador de Imagem:** Um `BasicImageProcessor` pode ser decorado com `ResizeImageProcessor` e `WatermarkedImageProcessor` para adicionar passos de processamento.

* `src/structuralPatterns/decorator01/WithMilk.kt` (Decorador de Café)

    ```kotlin
    package structuralPatterns.decorator01

    class WithMilk(decoratedCoffee: Coffee) : CoffeeDecorator(decoratedCoffee) {
        override fun getCost(): Double {
            return super.getCost() + 1.5
        }
        override fun getDescription(): String {
            return super.getDescription() + ", com leite"
        }
    }
    ```

* `src/structuralPatterns/decorator02/WatermarkedImageProcessor.kt` (Decorador de Imagem)

    ```kotlin
    package structuralPatterns.decorator02

    class WatermarkedImageProcessor (wrappedProcessor: ImageProcessor) : ImageProcessorDecorator(wrappedProcessor) {
        override fun process(image: String): String {
            val processedImage = super.process(image)
            return "$processedImage + Marca D'ágia da Empresa"
        }
    }
    ```

**Exemplo de Uso:**

Os arquivos de teste demonstram como envolver um objeto base com múltiplos decoradores para compor funcionalidades.

* `src/structuralPatterns/decorator01/DecoratorTest01.kt`

    ```kotlin
    var myCoffee: Coffee = SimpleCoffee()
    myCoffee = WithMilk(myCoffee)
    myCoffee = WithWhippedCream(myCoffee)
    println("Pedido: ${myCoffee.getDescription()} | Custo: R$ ${myCoffee.getCost()}")
    // Saída: Pedido: Café simples, com leite, com chantilly | Custo: R$ 9.0
    ```

---

#### **Facade**

O padrão Facade fornece uma interface unificada e simplificada para um conjunto de interfaces em um subsistema. Ele esconde a complexidade do sistema e fornece uma interface de alto nível ao cliente.

**Implementação:**

A classe `ComputerFacade` simplifica a interação com os componentes complexos de um computador (`Cpu`, `Memory`, `HardDrive`). Em vez de o cliente ter que interagir com cada componente para ligar o computador, ele simplesmente chama o método `startComputer()` da facade.

* `src/structuralPatterns/facade/ComputerFacade.kt`

    ```kotlin
    package structuralPatterns.facade

    class ComputerFacade {
        private val cpu = Cpu()
        private val memory = Memory()
        private val hardDrive = HardDrive()

        fun startComputer() {
            println(">>> Iniciando o computador (via Facade)...")
            cpu.start()
            val bootData = hardDrive.read(0L, 1024)
            memory.load(0L, bootData)
            cpu.execute()
            println(">>> Computador pronto para uso!")
        }

        fun shutdownComputer() {
            println(">>> Desligando o computador (via Facade)...")
            cpu.stop()
            memory.clear()
            println(">>> Computador desligado.")
        }
    }
    ```

**Exemplo de Uso:**

O arquivo `FacadeTest01.kt` mostra como o cliente interage com o sistema complexo de forma simples.

* `src/structuralPatterns/facade/FacadeTest01.kt`

    ```kotlin
    package structuralPatterns.facade

    fun main() {
        val computer = ComputerFacade()
        computer.startComputer()
        println("... Usando o computador por algumas horas ...\n")
        computer.shutdownComputer()
    }
    ```

---

#### **Proxy**

O padrão Proxy fornece um substituto ou placeholder para outro objeto para controlar o acesso a ele. É usado para adicionar uma camada de indireção, que pode ser útil para logging, controle de acesso, lazy initialization, etc.

**Implementação:**

`CartaoDeCreditoProxy` atua como um proxy para a `ContaBancaria` real. Antes de executar um pagamento, o proxy realiza uma verificação de saldo. Somente se o saldo for suficiente, ele delega a operação de pagamento para o objeto `ContaBancaria`. Isso protege o objeto real de operações inválidas (tentar pagar sem saldo).

* `src/structuralPatterns/proxy/CartaoDeCreditoProxy.kt`

    ```kotlin
    package structuralPatterns.proxy

    class CartaoDeCreditoProxy (
        private val conta: ContaBancaria
    ) : Pagamento {
        override fun realizarPagamento(valor: Double) {
            println("CARTÃO (PROXY): Tentativa de pagamento de R$ $valor recebida.")

            if (!verificarSaldo(valor)) {
                println("CARTÃO (PROXY): Pagamento de R$$valor RECUSADO. Saldo insuficiente.")
                return
            }
            println("CARTÃO (PROXY): Saldo suficiente. Transação autorizada.")
            conta.realizarPagamento(valor)
        }

        private fun verificarSaldo(valor: Double): Boolean {
            println("CARTÃO (PROXY): Verificando se o saldo de R$${conta.saldo} é suficiente...")
            return conta.saldo >= valor
        }
    }
    ```

**Exemplo de Uso:**

O cliente em `ProxyTest01.kt` interage com o proxy (`meuCartao`) como se fosse o objeto de pagamento real, sem saber da lógica de verificação de saldo que ele contém.

* `src/structuralPatterns/proxy/ProxyTest01.kt`

    ```kotlin
    fun main() {
        val minhaConta = ContaBancaria(500.0)
        val meuCartao: Pagamento = CartaoDeCreditoProxy(minhaConta)

        println("--- TENTATIVA 1: Compra de R$350.0 ---")
        meuCartao.realizarPagamento(350.0) // Sucesso

        println("--- TENTATIVA 2: Compra de R$200.0 ---")
        meuCartao.realizarPagamento(200.0) // Falha, saldo insuficiente
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
