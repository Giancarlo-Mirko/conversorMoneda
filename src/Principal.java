import java.io.IOException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) throws IOException {
        int opcion =0;
        double cantidad=0;
        String monedaOrigen="";
        String monedaDestino="";
        ConvertidorDeMoneda convertidor = new ConvertidorDeMoneda();



        String menu = """
                ***Escriba el numero de la opcion deseada***
                1 - Dólar          =>> Sol
                2 - Sol            =>> Dólar 
                3 - Dólar          =>> Real Brasileño
                4 - Real Brasileño => Dólar  
                5 - Dólar          =>> Peso Colombiano
                6 - Peso Colombiano=>> Sol
                7 - Otras monedas a convertir
                8 - Salir
                """;
        Scanner teclado = new Scanner(System.in);
        boolean salir= true;
        while (opcion!=8){
            System.out.println(menu);
            opcion = teclado.nextInt();

            switch (opcion){
                case 1:
                    monedaOrigen="USD";
                    monedaDestino="PEN";
                    break;
                case 2:
                    monedaOrigen="PEN";
                    monedaDestino="USD";
                    break;
                case 3:
                    monedaOrigen="USD";
                    monedaDestino="BRL";
                    break;
                case 4:
                    monedaOrigen="BRL";
                    monedaDestino="USD";
                    break;
                case 5:
                    monedaOrigen="USD";
                    monedaDestino="peso colombiano";
                    break;
                case 6:
                    monedaOrigen="PEN";
                    monedaDestino="COP";
                    break;
                case 7:
                    System.out.println("""
                    Tenga en cuenta los nombres de la moneda y ponga la abreviatura:
                    ANG: Florín antillano neerlandés (Antillas Neerlandesas)
                    AOA: Kwanza (Angola)
                    ARS: Peso argentino (Argentina)
                    AUD: Dólar australiano (Australia)
                    BND: Dólar de Brunéi (Brunéi)
                    BOB: Boliviano (Bolivia)
                    BRL: Real brasileño (Brasil)
                    BSD: Dólar bahameño (Bahamas)
                    CAD: Dólar canadiense (Canadá)
                    CHF: Franco suizo (Suiza)
                    CLP: Peso chileno (Chile)
                    CNY: Yuan chino (China)
                    COP: Peso colombiano (Colombia)
                    CRC: Colón costarricense (Costa Rica)
                    CUP: Peso cubano (Cuba)
                    CZK: Corona checa (República Checa)
                    DKK: Corona danesa (Dinamarca)
                    DOP: Peso dominicano (República Dominicana)
                    EGP: Libra egipcia (Egipto)
                    GBP: Libra esterlina (Reino Unido)
                    HKD: Dólar de Hong Kong (Hong Kong)
                    INR: Rupia india (India)
                    KRW: Won surcoreano (Corea del Sur)
                    LYD: Dinar libio (Libia)
                    MAD: Dirham marroquí (Marruecos)
                    MXN: Peso mexicano (México)
                    PAB: Balboa (Panamá)
                    PEN: Sol (Perú)
                    PHP: Peso filipino (Filipinas)
                    PKR: Rupia pakistaní (Pakistán)
                    PLN: Złoty (Polonia)
                    """);

                    // Solicitar la moneda al usuario hasta que proporcione una válida
                    boolean entradaValida = false;
                    boolean entradaSalida = false;
                    while (!entradaValida) {
                        System.out.println("Ingresa nombre de moneda Origen:");
                        monedaOrigen = teclado.nextLine();

                        // Validar la moneda proporcionada por el usuario
                        if (convertidor.validarMoneda(monedaOrigen)) { // Llamar al método desde la instancia
                            entradaValida = true;
                        } else {
                            System.out.println("Moneda inválida. Ingresa un nombre de moneda válida.");
                        }
                    }

                    while (!entradaSalida) {
                        System.out.println("Ingrese nombre de moneda destino:");
                        monedaDestino = teclado.nextLine();

                        // Validar la moneda proporcionada por el usuario
                        if (convertidor.validarMoneda(monedaDestino)) { // Llamar al método desde la instancia
                            entradaSalida = true;
                        } else {
                            System.out.println("Moneda inválida. Ingresa un nombre de moneda válida.");
                        }
                    }

                    break;
                case 8:
                    System.out.println("Gracias por usar la aplicación");
                    salir=false;
                    break;
            }
            if (salir==true){
                try {
                    System.out.println("Escriba el monto a convertir:");
                    Scanner monto = new Scanner(System.in);
                    cantidad = monto.nextDouble();

                    // Ejemplo de uso
                    double cantidadConvertida = convertidor.convertirMoneda(monedaOrigen, monedaDestino, cantidad);
                    System.out.println(cantidad+" "+monedaOrigen+" equivale a " + cantidadConvertida + " "+monedaDestino);
                } catch (IOException e) {
                    e.printStackTrace();
                    // Manejo de errores en caso de que falle la solicitud HTTP
                }
            }

        }

    }
}
