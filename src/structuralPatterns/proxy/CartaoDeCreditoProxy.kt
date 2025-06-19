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