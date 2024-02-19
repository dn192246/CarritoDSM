import java.text.DecimalFormat

fun main() {
    var productos:MutableList<Producto> = crearProductos()
    var carrito:MutableList<Item> = mutableListOf()
    var total:Double = 0.0

    println("\n------Bienvenido/a a la tienda digital------")
    println("\tA continuación, escoja una opción:\n")
    var repetir:Boolean = true;

    while(repetir){
        mostrarMenu()
        print("\nDigite la opción que desee: ")
        var opcion:Int = readLine()?.toIntOrNull() ?: 0;

        when (opcion){
            1 -> {
                println("\nListado de productos:")
                for(i in 0 until productos.size){
                    println((i+1).toString() + ". " + productos[i].nombre +
                            " (Marca: " + productos[i].marca + ") - $" + productos[i].precio +
                            " (Unidades Disponibles: " + productos[i].stock + ")")
                }

                println("\n----------")
                println("¿Desea agregar productos al carrito?")
                println("1. Sí")
                println("2. No")
                print("Escoja una opción: ")

                var agregarProductos:Int = readLine()?.toIntOrNull()?:0

                when(agregarProductos) {
                    1 -> {
                        //Agregar productos al carro
                        println("\n--------------------")
                        print("\nDigite el número de producto a agregar: ")
                        var productoEscogido = readLine()?.toIntOrNull()?:0;

                        if(productoEscogido < 1 || productoEscogido>(productos.size+1)){
                            println("El producto no existe. Intente nuev    amente.\n");
                            repetir = true;
                        }
                        else{
                            println("¿Desea agregar " + productos[productoEscogido-1].nombre + " al carrito?")
                            println("1. Sí")
                            println("2. No\n")

                            print("Escoja una opción: ")
                            var agregar:Int = readLine()?.toIntOrNull()?:0

                            if(agregar==1){
                                print("\nDigite la cantidad a agregar: ")
                                var cantidad:Int = readLine()?.toIntOrNull()?:0;

                                if(cantidad > productos[productoEscogido-1].stock){
                                    println("La cantidad solicitada es mayor al stock disponible\n")
                                }
                                else{
                                    productos[productoEscogido-1].stock -= cantidad;
                                    val idProducto:Int = productoEscogido -1
                                    val cantidadSeleccionada:Int = cantidad;

                                    carrito.add(Item(idProducto,cantidadSeleccionada))
                                    println("--El producto se ha agregado satisfactoriamente.\n")
                                }
                                repetir = true
                            }
                            else{
                                println("\nOpción inválida")
                                repetir = true
                            }
                        }
                    }

                    2 -> {
                        //No agregar productos al carrito
                        repetir = true;
                        println("\n")
                    }

                    else -> {
                        println("\nOpción inválida.\n")
                        repetir = true
                    }
                }
            }

            2 -> {
                //Ver carrito
                if(carrito.size>0){

                    total = 0.0
                    println("------------------\nCarrito:\n")
                    for(i in 0 until carrito.size){
                        println((i+1).toString() + ". " + productos[carrito[i].idProducto].nombre +
                                " - Cantidad: " + carrito[i].cantidadProducto)
                        total += productos[carrito[i].idProducto].precio.toDouble() * carrito[i].cantidadProducto
                    }

                    val formato = DecimalFormat("#.##")
                    var totalFinal:String = formato.format(total)
                    println("\nTotal: $" + totalFinal)
                    println("")
                }
                else{
                    println("\nNo hay productos en el carrito\n")
                }
            }

            3 -> {
                repetir = false
            }

            4 -> {
                repetir = false
                println("----------\n¡Hasta pronto!")
                System.exit(0)
            }

            else ->{
                println("\n\nLa opción no es válida. Intente nuevamente.")
            }
        }
    }

}

fun mostrarMenu(){
    println("\t1. Ver los productos disponibles.")
    println("\t2. Ver mi carrito de compras.")
    println("\t3. Finalizar compra")
    println("\t4. Salir")
}

fun crearProductos(): MutableList<Producto>{
    var productos: MutableList<Producto> = mutableListOf()
    productos.add(Producto("Manzana Roja", "Fruits of the Kingdom", 0.35,23));
    productos.add(Producto("Leche Entera", "Lácteos del Campo", 0.99, 50))
    productos.add(Producto("Yogurt Natural", "Sabores de la Granja", 0.50, 40))
    productos.add(Producto("Queso Mozzarella", "Lácteos del Sur", 2.99, 25))
    productos.add(Producto("Huevos Blancos", "Granja Los Andes", 2.50, 100))
    productos.add(Producto("Café Molido", "Aromas del Valle", 5.99, 30))
    productos.add(Producto("Chocolate Negro", "Dulces Montañas", 1.99, 45))
    productos.add(Producto("Pan Integral", "Horno de Pan", 1.50, 60))
    productos.add(Producto("Cereal de Maíz", "Desayunos Felices", 3.99, 40))
    productos.add(Producto("Jugo de Naranja", "Sabor Natural", 2.99, 30))
    productos.add(Producto("Agua Mineral", "Fuentes Claras", 0.99, 150))
    productos.add(Producto("Cerveza Artesanal", "Cervecería del Lago", 1.49, 75))
    productos.add(Producto("Papel Higiénico", "Suavidad Diaria", 4.99, 50))
    productos.add(Producto("Detergente Líquido", "Limpieza Profunda", 5.99, 40))
    productos.add(Producto("Shampoo Hidratante", "Belleza Natural", 3.99, 30))

    return productos;
}