package Inventoryservice.com.Inventoryservice.Services;


import Inventoryservice.com.Inventoryservice.Models.Inventory;
import Inventoryservice.com.Inventoryservice.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> getAllBooks() {
        return inventoryRepository.findAll();
    }

    public Optional<Inventory> getBookById(Long id) {
        return inventoryRepository.findById(id);
    }

    public Inventory createBook(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public void deleteBook(Long id) {
        inventoryRepository.deleteById(id);
    }
}
