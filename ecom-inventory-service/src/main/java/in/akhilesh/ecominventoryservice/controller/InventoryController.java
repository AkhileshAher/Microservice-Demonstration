package in.akhilesh.ecominventoryservice.controller;

import in.akhilesh.ecominventoryservice.model.Inventory;
import in.akhilesh.ecominventoryservice.service.InventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{productId}")
    public Inventory checkInventory(@PathVariable Long productId) throws Exception {
        Thread.sleep(10000);
//        throw new Exception("Inventory Service is Down");
        return inventoryService.checkStock(productId);
    }

    @PostMapping
    public String addProduct(@RequestBody Inventory inventory) {
        return inventoryService.addProduct(inventory);
    }

    @PutMapping
    public String updateProduct(@RequestBody Inventory inventory) {
        return "";
    }

}
