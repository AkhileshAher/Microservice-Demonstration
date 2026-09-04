package in.akhilesh.ecominventoryservice.service;

import in.akhilesh.ecominventoryservice.model.Inventory;
import in.akhilesh.ecominventoryservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    public Inventory checkStock(Long productId) {
        Optional<Inventory> inv = repository.findById(productId);
        return inv.get();
    }

    public String addProduct(Inventory inventory) {
        repository.save(inventory);
        return "Product Added";
    }




}
