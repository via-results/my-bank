package br.com.bank.controller.api;

import br.com.bank.controller.request.CustomerRequest;
import br.com.bank.model.Customer;
import br.com.bank.service.CustomerUseCase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerUseCase customerUseCase;

    public CustomerController(CustomerUseCase customerUseCase) {
        this.customerUseCase = customerUseCase;
    }

    @PostMapping
    public void create(@RequestBody CustomerRequest customerRequest) {
        createCustomer(customerRequest);
    }

    @GetMapping("/requests-card")
    public String requestCard(@RequestParam(name = "document") String document) throws InterruptedException {
            return this.customerUseCase.sendRequest(document);
    }

    private void createCustomer(CustomerRequest customerRequest) {
        this.customerUseCase.createCustomer(Customer.builder()
                        .email(customerRequest.getEmail())
                        .document(customerRequest.getDocument())
                .build());
    }
}
