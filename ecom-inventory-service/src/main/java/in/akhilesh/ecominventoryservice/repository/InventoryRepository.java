package in.akhilesh.ecominventoryservice.repository;

import in.akhilesh.ecominventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
}
