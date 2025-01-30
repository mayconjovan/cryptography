package com.mjp.cryptography.controllers;

import com.mjp.cryptography.controllers.dto.CreateTransactionRequest;
import com.mjp.cryptography.controllers.dto.TransactionResponse;
import com.mjp.cryptography.controllers.dto.UpdateTransactionRequest;
import com.mjp.cryptography.service.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

    private final TransactionService service;


    public TransactionController(TransactionService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateTransactionRequest request) {
        service.create(request);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity<Page<TransactionResponse>> listAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                             @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        var body = service.listAll(page, pageSize);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> findByid(@PathVariable("id") Long id) {
        var body = service.findById(id);
        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateById(@PathVariable("id") Long id,
                                           @RequestBody UpdateTransactionRequest  request) {
        service.update(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
