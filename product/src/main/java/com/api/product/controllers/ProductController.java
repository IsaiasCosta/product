package com.api.product.controllers;

import com.api.product.dtos.ProductRecordDto;
import com.api.product.models.ProductModel;
import com.api.product.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin("*")
public class ProductController {
    // ponto de Injeção
    @Autowired

    ProductRepository productRepository;

    // Criando o s Metodos CRUD = Create - Read - Update - Delete
   /* @RequestBody: recebe o corpo do DTO: ProductRecordDto, tipo de retorno ResposeEntity, @Valid: para receber as valodaçoe do DTO,
      criando variavel do model e iniciando mesma usando o var
      convertendo o DTO para Model,usando o BeanUtils, pcopio o Dto para o Model, após a conversão teremos
      o retorno do status, criando o recurso, retornando no corpo  do recurso os dados do cliente no banco de dados, salvando esses recurso,
      que já foi convertido de DTO paar Model,
    */

    @PostMapping("/products")// passo a URI
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

    @GetMapping("/products")// recuperando a lista dos produtos
    public ResponseEntity<List<ProductModel>> getAllProduct() { // listando todos os produtos
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
    }

    @GetMapping("/{Id}") // recuperando produto pelo ID
    public ResponseEntity<Object> getIdProduct(@PathVariable(value = "Id") UUID id) {
        Optional<ProductModel> productModelOptional = productRepository.findById(id);
        if (!productModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Product nor found ");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productModelOptional.get());
    }

    @PutMapping("{Id}")//Editar e Atualizar
    public ResponseEntity<Object> UpdateProduct(@PathVariable(value = "Id") UUID id,
                                                @RequestBody @Valid ProductRecordDto productRecordDto) {
        Optional<ProductModel> productModelOptional = productRepository.findById(id);
        if (!productModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Product nor found ");
        }
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        productModel.setIdProduct(productModelOptional.get().getIdProduct());
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
    }

    @DeleteMapping("{Id}") //Delete product
    public ResponseEntity<Object> deleteIdProduct(@PathVariable(value = "Id") UUID id) {
        Optional<ProductModel> productModelOptional = productRepository.findById(id);
        if (!productModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Product nor found ");
        }
        productRepository.delete(productModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(productModelOptional.get());
    }
    @GetMapping("/product")
     public String nome(){
        return "API de teste conectada!";
    }
}
