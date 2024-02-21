import java.text.DecimalFormat

var productos:MutableList<Producto> = crearProductos()
var carrito:MutableList<Item> = mutableListOf()
var total:Double = 0.0

fun main() {
    println("\n------Bienvenido/a a la tienda digital------")
    println("\tA continuación, escoja una opción:\n")
    var repetir:Boolean = true;

    while(repetir){
        mostrarMenu()
        print("\nDigite la opción que desee: ")
        var opcion:Int = readLine()?.toIntOrNull() ?: 0;

        when (opcion){
            1 -> {
                //Ver y Agregar Productos al Carrito
                mostrarProductos()

                println("\n----------")
                println("¿Desea agregar productos al carrito?")
                println("1. Sí")
                println("2. No")
                print("Escoja una opción: ")

                var agregarProductos:Int = readLine()?.toIntOrNull()?:0

                when(agregarProductos) {
                    1 -> {
                       //El usuario busca agregar productos al carrito
                        agregarAlCarrito()
                    }

                    2 -> {
                        //No agregar productos al carrito
                        println("\n")
                    }

                    else -> {
                        println("\nOpción inválida.\n")
                    }
                }
            }

            2 -> {
                //Se muestran los productos en el carrito
                mostrarCarrito()
            }

            3 -> {
                //Se finaliza la compra
                generarFactura()
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

fun mostrarCarrito(){
    //Ver carrito
    if(carrito.size>0){
        //Se valida que hay productos en el carrito
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
        println("\n¿Desea eliminar productos del carrito?")
        println("1. Sí")
        println("2. No")
        print("Escoja una opción: ")
        var opcion:Int = readLine()?.toIntOrNull()?:0

        when(opcion){
            1->{
                eliminarDelCarrito()
            }
            2->{
                println("\n")
            }
            else->{
                println("\n")
            }
        }
    }
    else{
        //No hay productos agregados al carrito aún
        println("\nNo hay productos en el carrito\n")
    }
}

fun mostrarProductos(){
    println("\nListado de productos:")
    for(i in 0 until productos.size){
        println((i+1).toString() + ". " + productos[i].nombre +
                " (Marca: " + productos[i].marca + ") - $" + productos[i].precio +
                " (Unidades Disponibles: " + productos[i].stock + ")")
    }
}

fun eliminarDelCarrito(){
    print("\nIndique el número de producto a eliminar del carrito (0 para cancelar): ")
    var numProducto:Int = readLine()?.toIntOrNull()?:-1
    if(numProducto <0 || numProducto>(carrito.size+1)){
        //El producto no existe en el carrito
        println("El número de producto del carrito no es correcto. Intente nuevamente.\n");
    }
    else if(numProducto==0){
        //El usuario decide no eliminar un producto del carrito
        println("\nOperación cancelada\n\n")
    }
    else{
        //Se escoge un producto correctamente para eliminar del carrito
        println("\n¿Desea eliminar " + productos[carrito[numProducto-1].idProducto].nombre +
                " del carrito de compras? Esta acción no puede deshacerse.\n" +
                "Ingrese 1 para confirmar. Ingrese cualquier otro valor para cancelar.")
        var opcion:Int = readLine()?.toIntOrNull()?:0

        if(opcion == 1){
            //Se vuelve a agregar el producto al stock
            productos[carrito[numProducto-1].idProducto].stock += carrito[numProducto-1].cantidadProducto
            carrito.removeAt(numProducto-1)
            println("\n--El producto fue eliminado del carrito\n")
        }
        else{
            println("\n--El producto no fue eliminado del carrito\n")
        }
    }
}

fun agregarAlCarrito(){
    //Intentar gregar productos al carro
    println("\n--------------------")
    print("\nDigite el número de producto a agregar: ")
    var productoEscogido = readLine()?.toIntOrNull()?:0;

    if(productoEscogido < 1 || productoEscogido>(productos.size+1)){
        //El producto no existe
        println("El producto no existe. Intente nuevamente.\n");
    }
    else{
        //Confirmación para agregar el producto
        println("¿Desea agregar " + productos[productoEscogido-1].nombre + " al carrito?")
        println("1. Sí")
        println("2. No\n")

        print("Escoja una opción: ")
        var agregar:Int = readLine()?.toIntOrNull()?:0

        when(agregar){
            1->{
                //Se confirma agregar el producto y se solicita la cantidad
                print("\nDigite la cantidad a agregar: ")
                var cantidad:Int = readLine()?.toIntOrNull()?:0;

                if(cantidad>0){
                    //Se ha validado que el usuario escogió una cantidad válida (positva)
                    if(cantidad > productos[productoEscogido-1].stock) {
                        //El usuario desea agregar más productos de los disponibles
                        println("La cantidad solicitada es mayor al stock disponible\n")
                    }
                    else{
                        //Se verifica, inicialmente, si el producto ya estaba en el carrito
                        var existe:Boolean = false
                        var index:Int=0;
                        for(i in 0 until carrito.size){
                            //Se revisan todos los productos en el carrito para ver si hay una coincidencia
                            if(carrito[i].idProducto == productoEscogido-1){
                                existe= true
                                index = i
                                break
                            }
                        }

                        //Se resta la cantidad dentro del stock de productos
                        productos[productoEscogido-1].stock -= cantidad

                        if(existe){
                            //El producto ya había sido escogido antes en el carrito
                            carrito[index].cantidadProducto+=cantidad
                            println("--El producto se ha modificado satisfactoriamente.\n")
                        }
                        else{
                            //El producto no existía en el carrito
                            val idProducto:Int = productoEscogido -1
                            val cantidadSeleccionada:Int = cantidad;

                            carrito.add(Item(idProducto,cantidadSeleccionada))
                            println("--El producto se ha agregado satisfactoriamente.\n")
                        }
                    }
                }
                else{
                    println("\nDebe agregar una cantidad válida.\n\n")
                }

            }
            2->{
                //El usuario elige no agregar el producto al carro
                println("\n")
            }
            else->{
                //Se ha escogido una opción inválida
                println("\nOpción inválida\n")
            }
        }
    }
}

fun generarFactura(){

    if(carrito.size>0){
        //Se valida que hay productos en el carrito
        total = 0.0
        println("\n\n---------------------------")
        println("\n\t----Factura----")
        println("Listado de productos:")
        for(i in 0 until carrito.size){
            println((i+1).toString() + ". " + productos[carrito[i].idProducto].nombre +
                    " - Cantidad: " + carrito[i].cantidadProducto)
            total += productos[carrito[i].idProducto].precio.toDouble() * carrito[i].cantidadProducto
        }

        val formato = DecimalFormat("#.##")
        var totalFinal:String = formato.format(total)
        println("\nTotal: $" + totalFinal)

        println("\n\tGracias por su compra")
        println("----------------------------\n\n")

        carrito.clear();

        println("¿Desea realizar otra compra?")
        println("1. Sí")
        println("2. No")
        print("Escoja una opción: ")
        var opcion:Int = readLine()?.toIntOrNull()?:0

        when(opcion){
            2->{
                println("\nGracias por preferirnos\n")
                System.exit(0)
            }
            else ->{
                println("\n")
            }
        }
    }
    else{
        //No hay productos agregados al carrito aún
        println("\nNo hay productos en el carrito para finalizar la compra\n")
    }

}