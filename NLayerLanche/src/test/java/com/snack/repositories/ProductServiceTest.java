package com.snack.repositories;

import com.snack.entities.Product;
import com.snack.services.ProductService;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceTest {
    private ProductService productService;
    private Product product1;

    @BeforeEach
    public void setup() {
        productService = new ProductService();
        product1 = new Product(1, "Hot Dog", 10.4f, "src\\test\\resources\\images\\Hot-dog.jpg");
        productService.save(product1);
    }

    //1. Salvar um produto com imagem válida
    @Test
    @Order(1)
    public void salvarProdutoComImagemValida(){
        Assertions.assertTrue(productService.save(product1));
    }

    //2. Salvar um produto com imagem inexistente
    @Test
    @Order(2)
    public void salvarProdutoComImagemInvalida(){
        Product product2 = new Product(1,"X-tudo" , 12.4f, "invalido.png");
        Assertions.assertFalse(productService.save(product2));
    }

    //3. Atualizar um produto existente
    @Test
    @Order(3)
    public void atualizarProdutoExistente(){
        Product product2 = new Product(1,"X-tudo" , 12.4f, "src/test/resources/images/x-tudo.jpg");
        productService.update(product2);
        Assertions.assertEquals(1,product2.getId());
        Assertions.assertEquals("X-tudo",product2.getDescription());
        Assertions.assertEquals(12.4f,product2.getPrice());
        Assertions.assertEquals("src\\test\\resources\\images\\1.jpg",product2.getImage());
    }

    //4. Remover um produto existente
    @Test
    @Order(4)
    public void removerProdutoExistente(){
        Product bolo = new Product(2, "Bolo", 13.6f, "src\\test\\resources\\images\\Bolo.jpg");
        productService.save(bolo);
        Assertions.assertDoesNotThrow(()->{
            productService.remove(2);
        });
    }

    //5. Obter caminho da imagem por ID
    @Test
    @Order(5)
    public void obterCaminhoImagemPorId(){
        Assertions.assertEquals("C:\\Users\\aluno.fsa\\Documents\\GitHub\\Lanche-Test\\NLayerLanche\\src\\test\\resources\\images\\1.jpg",productService.getImagePathById(1));
    }


}
