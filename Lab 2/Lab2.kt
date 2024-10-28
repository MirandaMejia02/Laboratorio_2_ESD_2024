import java.util.LinkedList
import java.util.Stack

data class Orden(
    val nombreCliente: String,
    val pupusas: List<Pupusa>
)

data class Pupusa(
    val tipo: String,
    val cantidad: Int
)
fun main() {
    val sistema = PupuseriaSystem()
    var opcion: Int

    do {
        sistema.mostrarMenu()
        opcion = readLine()?.toIntOrNull() ?: 5
        when (opcion) {
            1 -> {
                println("Ingrese el nombre del cliente:")
                val nombreCliente = readLine() ?: ""
                val pupusas = mutableListOf<Pupusa>()
                println("Cuantos tipos de pupusas desea ordenar?")
                val tipos = readLine()?.toIntOrNull() ?: 0
                for (i in 1..tipos) {
                    println("Ingrese el tipo de pupusa #$i:")
                    val tipo = readLine() ?: ""
                    println("Ingrese la cantidad de pupusas de $tipo:")
                    val cantidad = readLine()?.toIntOrNull() ?: 0
                    pupusas.add(Pupusa(tipo, cantidad))
                }
                sistema.registrarOrden(nombreCliente, pupusas)
            }
            2 -> sistema.verOrdenesPendientes()
            3 -> sistema.despacharOrden()
            4 -> sistema.verOrdenesDespachadas()
            5 -> println("Saliendo del programa...")
            else -> println("Opcion no valida, intenta de nuevo.")
        }
    } while (opcion != 5)
}

class PupuseriaSystem {
    private val ordenesPendientes: LinkedList<Orden> = LinkedList()
    private val ordenesDespachadas: Stack<Orden> = Stack()

    // Método para registrar una nueva orden
    fun registrarOrden(nombreCliente: String, pupusas: List<Pupusa>) {
        val orden = Orden(nombreCliente, pupusas)
        ordenesPendientes.add(orden)
        println("Orden registrada para $nombreCliente")
    }
    

    // Método para ver órdenes pendientes
    fun verOrdenesPendientes() {
        if (ordenesPendientes.isEmpty()) {
            println("No hay órdenes pendientes.")
        } else {
            println("ordenes pendientes:")
            ordenesPendientes.forEach { orden ->
                println("${orden.nombreCliente}: ${orden.pupusas.joinToString { "${it.cantidad} pupusas de ${it.tipo}" }}")
            }
        }
    }

   // Método para despachar una orden
    fun despacharOrden() {
    if (ordenesPendientes.isNotEmpty()) {
        val orden = ordenesPendientes.poll()
        ordenesDespachadas.push(orden)
        
        // Imprimir en el formato específico solicitado
        val detalleOrden = orden.pupusas.joinToString(", ") { "${it.cantidad} pupusas de ${it.tipo}" }
        println("Despachando la orden de: ${orden.nombreCliente}: $detalleOrden")
    } else {
        println("No hay órdenes pendientes para despachar.")
    }
}


    // Método para ver órdenes despachadas
    fun verOrdenesDespachadas() {
        if (ordenesDespachadas.isEmpty()) {
            println("No hay ordenes despachadas.")
        } else {
            println("Ordenes despachadas:")
            ordenesDespachadas.forEach { orden ->
                println("${orden.nombreCliente}: ${orden.pupusas.joinToString { "${it.cantidad} pupusas de ${it.tipo}" }}")
            }
        }
    }

    // Método para mostrar el menú
    fun mostrarMenu() {
        println("Bienvenido a la Pupuseria \"El Comalito\"")
        println("Seleccione una opcion:")
        println("1. Registrar una nueva orden")
        println("2. Ver ordenes pendientes")
        println("3. Despachar una orden")
        println("4. Ver ordenes despachadas")
        println("5. Salir")
    }
}
5