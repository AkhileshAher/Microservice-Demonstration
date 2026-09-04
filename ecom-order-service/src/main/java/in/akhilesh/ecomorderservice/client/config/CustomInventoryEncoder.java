package in.akhilesh.ecomorderservice.client.config;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import in.akhilesh.ecomorderservice.dto.Inventory;
import tools.jackson.databind.ObjectMapper;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class CustomInventoryEncoder implements Encoder {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void encode(Object object, Type type, RequestTemplate requestTemplate) throws EncodeException {
        try {
            // Cast to Inventory
            if (object instanceof Inventory inventory) {
                // Trnasform into required Structure

                Map<String, Object> data =  new HashMap<>();
                data.put("inventory", inventory.getProductId());
                data.put("quantity", inventory.getQuantity());

                String json = objectMapper.writeValueAsString(data);

                requestTemplate.body(json);
                requestTemplate.header("Content-Type", "application/json");
            }
        } catch (Exception e) {}
    }
}
