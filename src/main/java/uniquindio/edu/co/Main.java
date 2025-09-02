package uniquindio.edu.co;

import uniquindio.edu.co.DAO.*;
import uniquindio.edu.co.Model.Book;
import uniquindio.edu.co.Model.Library;
import uniquindio.edu.co.Model.Rating;
import uniquindio.edu.co.Model.User;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    //Search the book by title or author (Literally the title´s or author´s book)

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryDAO libraryDAO = new LibraryDAO();

        System.out.println("Ingrese título o autor a buscar");
        String criterio = scanner.nextLine();

        List<Book> resultados = libraryDAO.searchBook(criterio);

        if(resultados.isEmpty()){
            System.out.println("No se encontraron libros con ese criterio");
        }else{
            System.out.println("Resultado encontrados: ");
            for (Book b : resultados) {
                System.out.println(b);
            }
        }
        scanner.close();
    }

    //Method for rate a book, searching by author or title
    /*
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            // DAOs
            LibraryDAO libraryDAO = new LibraryDAO();      // tu clase ya existente para búsquedas
            RatingDAO ratingDAO   = new RatingDAOImpl();   // tiene: save(..), findByBookId(..), getAverageRating(..)
            BookDAO bookDAO       = new BookDAOImpl();     // tiene: update(..), etc.

            // 1) Buscar libro por criterio (título o autor)
            System.out.println("Ingrese título o autor a buscar:");
            String criterio = scanner.nextLine();

            List<Book> resultados = libraryDAO.searchBook(criterio);

            if (resultados.isEmpty()) {
                System.out.println("No se encontraron libros con ese criterio");
                return;
            }

            System.out.println("\nResultados encontrados:");
            for (Book b : resultados) {
                // Muchos DAOs no cargan el ID en searchBook; por eso mostramos datos útiles
                System.out.println("Id: " + b.getId() + " - Título: " + b.getTitle()
                        + " | Autor: " + b.getAuthor()
                        + " | ISBN: " + b.getIsbn());
            }

            // 2) Pedir ID real del libro (tal como está en la tabla 'books')
            System.out.print("\nIngrese el ID del libro que desea calificar (según BD): ");
            int bookId = Integer.parseInt(scanner.nextLine().trim());

            // Tomamos el primer libro de los resultados como "plantilla" de metadatos
            // (solo para mostrar; lo importante para guardar es el ID)
            Book selectedBook = resultados.get(0);
            selectedBook.setId(bookId);

            // 3) Pedir ID del usuario (según tabla 'users')
            System.out.print("Ingrese su ID de usuario: ");
            int userId = Integer.parseInt(scanner.nextLine().trim());
            User currentUser = new User(userId, "", "", "");

            // 4) Pedir calificación (1-5)
            System.out.print("Ingrese su calificación (1-5): ");
            int rate = Integer.parseInt(scanner.nextLine().trim());
            if (rate < 1 || rate > 5) {
                System.out.println("⚠️ La calificación debe estar entre 1 y 5.");
                return;
            }

            // 5) Guardar rating en BD
            Rating rating = new Rating(rate, currentUser, selectedBook);
            ratingDAO.save(rating);

            // 6) Obtener nuevo promedio desde la BD y actualizar el libro
            double avg = ratingDAO.getAverageRating(bookId);
            selectedBook.setAverageRate((int) Math.round(avg));
            bookDAO.update(selectedBook); // persiste el nuevo average_rate en 'books'

            System.out.printf("✅ Calificación guardada con éxito. Nuevo promedio: %.2f%n", avg);
        }
    }*/
}