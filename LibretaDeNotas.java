import java.util.*;

public class LibretaDeNotas {

    // HashMap para almacenar las calificaciones de los estudiantes
    // La clave es el nombre del estudiante y el valor es una lista de notas (ArrayList<Double>)
    private HashMap<String, ArrayList<Double>> calificaciones;

    // Variable para almacenar el promedio general del curso
    private double promedioCurso;

    // Constructor de la clase
    public LibretaDeNotas() {
        calificaciones = new HashMap<>(); // Inicializa el HashMap
    }

    // Metodo para ingresar los datos de los estudiantes y sus notas
    public void ingresarDatos() {
        Scanner scanner = new Scanner(System.in); // Scanner para leer la entrada del usuario

        // Solicitar la cantidad de alumnos
        System.out.print("Ingrese la cantidad de alumnos: ");
        int cantidadAlumnos = scanner.nextInt();

        // Solicitar la cantidad de notas por alumno
        System.out.print("Ingrese la cantidad de notas por alumno: ");
        int cantidadNotas = scanner.nextInt();

        // Bucle para ingresar los datos de cada alumno
        for (int i = 0; i < cantidadAlumnos; i++) {
            System.out.print("Ingrese el nombre del alumno " + (i + 1) + ": ");
            String nombre = scanner.next(); // Leer el nombre del alumno

            // ArrayList para almacenar las notas del alumno actual
            ArrayList<Double> notas = new ArrayList<>();

            // Bucle para ingresar las notas del alumno
            for (int j = 0; j < cantidadNotas; j++) {
                System.out.print("Ingrese la nota " + (j + 1) + " de " + nombre + ": ");
                double nota = scanner.nextDouble(); // Leer la nota

                // Validar que la nota esté en un rango válido (0 a 10)
                while (nota < 0 || nota > 10) {
                    System.out.print("Nota inválida. Ingrese una nota entre 0 y 10: ");
                    nota = scanner.nextDouble();
                }

                notas.add(nota); // Agregar la nota válida al ArrayList
            }

            // Guardar el nombre del alumno y sus notas en el HashMap
            calificaciones.put(nombre, notas);
        }

        // Calcular el promedio general del curso después de ingresar todos los datos
        calcularPromedioCurso();
    }

    // Metodo para calcular el promedio general del curso
    private void calcularPromedioCurso() {
        double sumaTotal = 0; // Variable para almacenar la suma de todas las notas
        int totalNotas = 0;   // Variable para contar el total de notas

        // Recorrer el HashMap para sumar todas las notas
        for (ArrayList<Double> notas : calificaciones.values()) {
            for (double nota : notas) {
                sumaTotal += nota; // Sumar cada nota
                totalNotas++;       // Incrementar el contador de notas
            }
        }

        // Calcular el promedio general del curso
        promedioCurso = sumaTotal / totalNotas;
    }

    // Metodo para mostrar el menú de opciones
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in); // Scanner para leer la entrada del usuario
        int opcion; // Variable para almacenar la opcion seleccionada por el usuario

        // Bucle para mostrar el menú hasta que el usuario elija salir (opcion 0)
        do {
            System.out.println("\nMenú de Opciones:");
            System.out.println("1. Mostrar el Promedio de Notas por Estudiante.");
            System.out.println("2. Mostrar si la Nota es Aprobatoria o Reprobatoria por Estudiante.");
            System.out.println("3. Mostrar si la Nota está por Sobre o por Debajo del Promedio del Curso por Estudiante.");
            System.out.println("0. Salir del Menu.");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt(); // Leer la opción seleccionada

            // Switch para manejar las opciones del menú
            switch (opcion) {
                case 1:
                    mostrarPromedioPorEstudiante(); // Llamar al metodo para mostrar promedios
                    break;
                case 2:
                    mostrarAprobacion(); // Llamar al metodo para verificar aprobación
                    break;
                case 3:
                    mostrarSobrePromedio(); // Llamar al metodo para comparar con el promedio del curso
                    break;
                case 0:
                    System.out.println("Saliendo del menu..."); // Salir del menu
                    break;
                default:
                    System.out.println("Opcion inválida. Intente de nuevo."); // Opción no válida
            }
        } while (opcion != 0); // Repetir hasta que el usuario elija salir
    }

    // Metodo para mostrar el promedio de notas por estudiante
    private void mostrarPromedioPorEstudiante() {
        // Recorrer el HashMap para calcular y mostrar el promedio de cada estudiante
        for (Map.Entry<String, ArrayList<Double>> entry : calificaciones.entrySet()) {
            String nombre = entry.getKey(); // Obtener el nombre del estudiante
            ArrayList<Double> notas = entry.getValue(); // Obtener la lista de notas

            double suma = 0; // Variable para almacenar la suma de las notas
            for (double nota : notas) {
                suma += nota; // Sumar todas las notas
            }
            double promedio = suma / notas.size(); // Calcular el promedio

            // Mostrar el promedio del estudiante
            System.out.println("El promedio de " + nombre + " es: " + promedio);
        }
    }

    // Metodo para verificar si una nota es aprobatoria o reprobatoria
    private void mostrarAprobacion() {
        Scanner scanner = new Scanner(System.in); // Scanner para leer la entrada del usuario

        // Solicitar el nombre del estudiante
        System.out.print("Ingrese el nombre del estudiante: ");
        String nombre = scanner.next();

        // Verificar si el estudiante existe en el HashMap
        if (calificaciones.containsKey(nombre)) {
            // Solicitar la nota a verificar
            System.out.print("Ingrese la nota a verificar: ");
            double nota = scanner.nextDouble();

            // Verificar si la nota es aprobatoria (nota >= 6) o reprobatoria
            if (nota >= 6) {
                System.out.println("La nota es aprobatoria.");
            } else {
                System.out.println("La nota es reprobatoria.");
            }
        } else {
            System.out.println("Estudiante no encontrado."); // Si el estudiante no existe
        }
    }

    // Metodo para verificar si una nota está por encima o por debajo del promedio del curso
    private void mostrarSobrePromedio() {
        Scanner scanner = new Scanner(System.in); // Scanner para leer la entrada del usuario

        // Solicitar el nombre del estudiante
        System.out.print("Ingrese el nombre del estudiante: ");
        String nombre = scanner.next();

        // Verificar si el estudiante existe en el HashMap
        if (calificaciones.containsKey(nombre)) {
            // Solicitar la nota a verificar
            System.out.print("Ingrese la nota a verificar: ");
            double nota = scanner.nextDouble();

            // Comparar la nota con el promedio del curso
            if (nota > promedioCurso) {
                System.out.println("La nota está por sobre el promedio del curso.");
            } else if (nota < promedioCurso) {
                System.out.println("La nota está por debajo del promedio del curso.");
            } else {
                System.out.println("La nota es igual al promedio del curso.");
            }
        } else {
            System.out.println("Estudiante no encontrado."); // Si el estudiante no existe
        }
    }

    // Metodo principal (punto de entrada del programa)
    public static void main(String[] args) {
        LibretaDeNotas libreta = new LibretaDeNotas(); // Crear una instancia de la clase
        libreta.ingresarDatos(); // Llamar al metodo para ingresar datos
        libreta.mostrarMenu();   // Llamar al metodo para mostrar el menú
    }
}
