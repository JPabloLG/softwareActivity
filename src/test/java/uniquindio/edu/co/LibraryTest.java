package uniquindio.edu.co;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uniquindio.edu.co.DAO.LibraryDAO;
import uniquindio.edu.co.Model.Book;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas para verificar el funcionamiento de la clase LibraryDAO.
 * Se enfoca en probar el método de búsqueda de libros bajo diferentes escenarios.
 */
public class LibraryTest {

    private LibraryDAO myLibraryDAO;

    @BeforeEach
    void setUp() {
        myLibraryDAO = new LibraryDAO();
    }

    /**
     * Prueba la búsqueda de libros filtrando únicamente por el autor.
     * Se espera que encuentre 1 libro cuyo autor es "Gabo".
     */
    @Test
    @DisplayName("Prueba de búsqueda solo por autor 'Gabo'")
    void searchOnlyForAuthor() {
        // Se llama al método con 3 parámetros, usando null para los que no se usan.
        ArrayList<Book> resultados = myLibraryDAO.searchBookAll("Gabo",null,null);
        assertEquals(1, resultados.size());
        assertEquals("Cien años de soledad", resultados.get(0).getTitle());
    }

    /**
     * Prueba la búsqueda de libros del autor que contiene "Mario".
     * Se espera que encuentre 2 libros.
     */
    @Test
    @DisplayName("Prueba de búsqueda por autor 'Mario'")
    void searchAuthorMario() {
        // Se llama al método con 3 parámetros.
        ArrayList<Book> resultados = myLibraryDAO.searchBookAll("Mario", null, null);
        assertEquals(2, resultados.size());
    }

    /**
     * Prueba la búsqueda de un libro por su ISBN exacto.
     * Se espera que encuentre un único libro que coincida.
     */
    @Test
    @DisplayName("Prueba de búsqueda solo por ISBN existente")
    void searchByExistingISBN() {
        // Se llama al método con 3 parámetros.
        ArrayList<Book> resultados = myLibraryDAO.searchBookAll(null,null,"esedfv2345");
        assertEquals(1, resultados.size());
        assertEquals("Cien años de soledad", resultados.get(0).getTitle());
    }

    /**
     * ¡NUEVA PRUEBA! Verifica la búsqueda con dos filtros a la vez.
     * Se espera encontrar el libro que cumpla ambas condiciones.
     */
    @Test
    @DisplayName("Prueba de búsqueda combinada por autor y título")
    void searchByAuthorAndTitle() {
        // Buscamos un autor y un libro que sí corresponden
        ArrayList<Book> resultados = myLibraryDAO.searchBookAll("Mario", "Scorpio", null);
        assertEquals(1, resultados.size());
        assertEquals("Scorpio City", resultados.get(0).getTitle());
    }

    /**
     * Prueba la búsqueda sin proporcionar ningún criterio de filtro.
     * Se espera que el método devuelva todos los libros de la colección.
     */
    @Test
    @DisplayName("Prueba de búsqueda sin criterios")
    void searchWithNoCriteria() {
        // Se llama al método con 3 parámetros, todos nulos.
        ArrayList<Book> resultados = myLibraryDAO.searchBookAll(null, null, null);
        assertEquals(5, resultados.size());
    }
}