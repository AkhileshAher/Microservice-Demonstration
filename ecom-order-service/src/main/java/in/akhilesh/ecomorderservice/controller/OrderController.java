package in.akhilesh.ecomorderservice.controller;

import in.akhilesh.ecomorderservice.dto.Inventory;
import in.akhilesh.ecomorderservice.service.InventoryService;
import in.akhilesh.ecomorderservice.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final InventoryService inventoryService;
    public OrderController(OrderService orderService, InventoryService inventoryService) {
        this.orderService = orderService;
        this.inventoryService = inventoryService;
    }

    @PostMapping("/{productId}")
    public String placeOrder(@PathVariable Long productId) {
        return orderService.placeOrder(productId);
    }

    @GetMapping("/{productId}")
    public Inventory getInventory(@PathVariable Long productId) {
        return inventoryService.getInventory(productId).join();
    }

}
