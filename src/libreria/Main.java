
package libreria;

import libreria.entidades.Autor;
import libreria.entidades.Libro;
import libreria.servicios.ServicioAutor;
import libreria.servicios.ServicioEditorial;
import libreria.servicios.ServicioLibro;

public class Main {

    public static void main(String[] args) {
        ServicioAutor sa = new ServicioAutor();
        ServicioEditorial se = new ServicioEditorial();
        ServicioLibro sl = new ServicioLibro();

        try {
            Autor a = sa.crearAutor("Laucha");
            Libro l1 = sl.crearLibro(123456L, "Hola mundo", 2020, 200, a, se.crearEditorial("PepitoEdit"));
            Libro l2 = sl.crearLibro(987654L, "Hola Melo", 2021, 200, a, se.crearEditorial("MarcoEdit"));
            sl.mostrarLibro(l2);
            System.out.println("-----------------------------");
            sl.cambiarTitulo(l2);
            sl.mostrarLibro(l2);
            System.out.println("-----------------------------");
            sl.mostrarLibrosAutor("Laucha");
            System.out.println("-----------------------------");
            sl.mostrarLibrosEditorial("PepitoEdit");
            System.out.println("-----------------------------");
            sl.mostrarLibrosEditorial("MarcoEdit");
            System.out.println("-----------------------------");
            sl.mostrarLibros();
            System.out.println("-----------------------------");
            sl.darBaja(l1);
            System.out.println("-----------------------------");
            sl.mostrarLibro(l1);
            System.out.println("-----------------------------");
            sl.mostrarLibros();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
