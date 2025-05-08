package com.snack.repositories;

import com.snack.entities.Product;
import com.snack.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductServiceTest {
    private ProductService productService;
    private Product product1;

    @BeforeEach
    public void setup() {
        productService = new ProductService();
        product1 = new Product(1, "Hot Dog", 10.4f, "");
    }

    //1. Salvar um produto com imagem válida
    @Test
    public void salvarProdutoComImagemValida(){
        Assertions.assertTrue(productService.save(product1));
    }

    //2. Salvar um produto com imagem inexistente
    @Test
    public void salvarProdutoComImagemInValida(){
        product1.setImage("invalid.png");
        Assertions.assertFalse(productService.save(product1));
    }

    //3. Atualizar um produto existente
    @Test
    public void atualizarProdutoExistente(){
        Product product2 = new Product(1,"X-tudo" , 12.4f, "");
        productService.update(product2);
        Assertions.assertEquals(1,product2.getId());
        Assertions.assertEquals("X-tudo",product2.getDescription());
        Assertions.assertEquals(12.4f,product2.getPrice());
        Assertions.assertEquals("",product2.getImage());
    }

    //4. Remover um produto existente

    @Test
    public void removerProdutoExistente(){
        Assertions.assertThrows(RuntimeException.class,()->{
            productService.remove(4);
        });
    }

    //5. Obter caminho da imagem por ID

    @Test
    public void obterCaminhoImagemPorId(){
        Assertions.assertEquals("",productService.getImagePathById(1));
    }


}
