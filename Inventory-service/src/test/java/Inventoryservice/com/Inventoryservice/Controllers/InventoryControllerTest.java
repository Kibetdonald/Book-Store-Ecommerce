package Inventoryservice.com.Inventoryservice.Controllers;

import Inventoryservice.com.Inventoryservice.Models.Inventory;
import Inventoryservice.com.Inventoryservice.Services.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class InventoryControllerTest {
    @Mock
    private InventoryService inventoryService;

    @InjectMocks
    private InventoryController inventoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBooks() {
        Inventory inventory1 = new Inventory(1L, "Book1", "Author1", "ISBN1", 20.0, 100);
        Inventory inventory2 = new Inventory(2L, "Book2", "Author2", "ISBN2", 25.0, 150);

        List<Inventory> inventoryList = Arrays.asList(inventory1, inventory2);

        when(inventoryService.getAllBooks()).thenReturn(inventoryList);

        ResponseEntity<List<Inventory>> response = inventoryController.getAllBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetBookById() {
        Long bookId = 1L;
        Inventory inventory = new Inventory(bookId, "Book1", "Author1", "ISBN1", 20.0, 100);

        when(inventoryService.getBookById(bookId)).thenReturn(Optional.of(inventory));

        ResponseEntity<Inventory> response = inventoryController.getBookById(bookId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookId, response.getBody().getId());
    }

    @Test
    void testGetBookByIdNotFound() {
        Long bookId = 100L;

        when(inventoryService.getBookById(bookId)).thenReturn(Optional.empty());

        ResponseEntity<Inventory> response = inventoryController.getBookById(bookId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testCreateBook() {
        Inventory inventory = new Inventory(null, "Book3", "Author3", "ISBN3", 30.0, 200);

        when(inventoryService.createBook(inventory)).thenReturn(inventory);

        ResponseEntity<Inventory> response = inventoryController.createBook(inventory);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Book3", response.getBody().getTitle());
    }

    @Test
    void testDeleteBook() {
        Long bookId = 1L;

        ResponseEntity<Void> response = inventoryController.deleteBook(bookId);

        verify(inventoryService, times(1)).deleteBook(bookId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
