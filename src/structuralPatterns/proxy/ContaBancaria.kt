package structuralPatterns.proxy

class ContaBancaria(
    var saldo: Double
) : Pagamento {
    fun depositar(valor: Double) {
        saldo += valor

        println("CONTA BANCÁRIA: Depósito de R$ $valor, Saldo atual: R$ $saldo")
    }

    override fun realizarPagamento(valor: Double) {
        println("CONTA BANCÁRIA: Debitando R$ $valor da conta.")
        saldo -= valor
        println("CONTA BANCÁRIO: Pagamento realizado. Saldo restante: R$ $saldo")
    }
}