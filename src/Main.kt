//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    println("\n------Bienvenido/a a la tienda digital------")
    println("\tA continuación, escoja una opción:\n")
    mostrarMenu()
    print("Digite la opción que desee: ")
    var opcion:Int = readLine()?.toIntOrNull() ?: 0;

    when (opcion){
        1 -> {

        }

        2 -> {

        }

        3 -> {

        }

        4 -> {
            System.exit(0)
        }
    }
}

fun mostrarMenu(){
    println("\t1. Ver los productos disponibles.")
    println("\t2. Ver mi carrito de compras.")
    println("\t3. Finalizar compra")
    println("\t4. Salir")
}