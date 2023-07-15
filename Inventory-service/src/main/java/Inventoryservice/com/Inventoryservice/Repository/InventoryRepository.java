package Inventoryservice.com.Inventoryservice.Repository;

import Inventoryservice.com.Inventoryservice.Models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
