import java.util.Scanner;

class Cuenta {
    // Atributos privados para encapsular los datos sensibles
    private String nombre;
    private double saldo;
    private int pin;

    // Constructor para inicializar los atributos de la cuenta
    public Cuenta(String nombre, double saldo, int pin) {
        this.nombre = nombre;
        this.saldo = saldo;
        this.pin = pin;
    }

    // Getter para obtener el saldo
    public double getSaldo(int pin) {
        if (this.pin == pin) {
            return saldo;
        } else {
            System.out.println("PIN incorrecto.");
            return -1; // Indica que el PIN es incorrecto
        }
    }

    // Método para depositar dinero en la cuenta
    public void depositar(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
            System.out.println("Depósito realizado con éxito. Nuevo saldo: " + saldo);
        } else {
            System.out.println("La cantidad debe ser positiva.");
        }
    }

    // Método para retirar dinero de la cuenta
    public void retirar(double cantidad, int pin) {
        if (this.pin == pin) {
            if (cantidad > 0 && cantidad <= saldo) {
                saldo -= cantidad;
                System.out.println("Retiro realizado con éxito. Nuevo saldo: " + saldo);
            } else {
                System.out.println("Cantidad inválida o fondos insuficientes.");
            }
        } else {
            System.out.println("PIN incorrecto.");
        }
    }
}

class Banco {
    private Cuenta[] cuentas;
    private int totalCuentas;

    public Banco(int maxCuentas) {
        cuentas = new Cuenta[maxCuentas];
        totalCuentas = 0;
    }

    public void crearCuenta(String nombre, double saldo, int pin) {
        if (totalCuentas < cuentas.length) {
            cuentas[totalCuentas] = new Cuenta(nombre, saldo, pin);
            totalCuentas++;
            System.out.println("Cuenta creada con éxito.");
        } else {
            System.out.println("No se pueden crear más cuentas.");
        }
    }

    public Cuenta accederCuenta(int pin) {
        for (int i = 0; i < totalCuentas; i++) {
            if (cuentas[i].getSaldo(pin) != -1) {
                return cuentas[i];
            }
        }
        System.out.println("Cuenta no encontrada o PIN incorrecto.");
        return null;
    }
}

public class SimulacionBanco {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Banco banco = new Banco(10); // Banco con capacidad para 10 cuentas

        while (true) {
            System.out.println("Bienvenido al Banco. Elija una opción:");
            System.out.println("1. Crear una nueva cuenta");
            System.out.println("2. Acceder a una cuenta existente");
            System.out.println("3. Salir");
            int opcion = scanner.nextInt();

            if (opcion == 1) {
                System.out.print("Ingrese su nombre: ");
                scanner.nextLine(); // Limpiar el buffer
                String nombre = scanner.nextLine();
                System.out.print("Ingrese el monto inicial de depósito: ");
                double saldo = scanner.nextDouble();
                System.out.print("Ingrese un PIN de 4 dígitos: ");
                int pin = scanner.nextInt();
                banco.crearCuenta(nombre, saldo, pin);
            } else if (opcion == 2) {
                System.out.print("Ingrese su PIN: ");
                int pin = scanner.nextInt();
                Cuenta cuenta = banco.accederCuenta(pin);
                if (cuenta != null) {
                    System.out.println("1. Ver saldo");
                    System.out.println("2. Depositar dinero");
                    System.out.println("3. Retirar dinero");
                    int subOpcion = scanner.nextInt();
                    if (subOpcion == 1) {
                        System.out.println("Saldo actual: " + cuenta.getSaldo(pin));
                    } else if ( == 2) {
                        System.out.print("Ingrese la cantidad a depositar: ");
                        double cantidad = scanner.nextDouble();
                        cuenta.depositar(cantidad);
                    } else if (subOpcion == 3) {
                        System.out.print("Ingrese la cantidad a retirar: ");
                        double cantidad = scanner.nextDouble();
                        cuenta.retirar(cantidad, pin);
                    }
                }
            } else if (opcion == 3) {
                System.out.println("Gracias por usar el Banco. ¡Hasta luego!");
                break;
            } else {
                System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }
}
