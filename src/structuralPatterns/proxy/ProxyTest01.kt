package structuralPatterns.proxy

fun main() {
    val minhaConta = ContaBancaria(500.0)

    val meuCartao: Pagamento = CartaoDeCreditoProxy(minhaConta)

    println("Saldo inicial da conta: R$${minhaConta.saldo}\n")

    println("--- TENTATIVA 1: Compra de R$350.0 ---")
    meuCartao.realizarPagamento(350.0)
    println("----------------------------------------\n")

    println("--- TENTATIVA 2: Compra de R$200.0 ---")
    meuCartao.realizarPagamento(200.0)
    println("----------------------------------------\n")

    println("--- TENTATIVA 3: Compra de R$200.0 ---")
    minhaConta.depositar(200.0)
    meuCartao.realizarPagamento(200.0)
    println("----------------------------------------\n")

    println(">>> Saldo final na conta: R$${minhaConta.saldo} (permaneceu inalterado após a tentativa falha)")

}