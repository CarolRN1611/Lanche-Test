package com.snack.repositories;

import com.snack.applications.ProductApplication;
import com.snack.entities.Product;
import com.snack.facade.ProductFacade;
import com.snack.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ProductFacadeTest {
    private ProductFacade productFacade;
    private ProductRepository productRepository;
    private ProductService productService;
    private Product product1;

    @BeforeEach
    public void setup() {
        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService();
        ProductApplication productApplication = new ProductApplication(productRepository,productService);
        productFacade = new ProductFacade(productApplication);
        product1 = new Product(1, "Hot Dog", 10.4f, "src\\test\\resources\\images\\Hot-dog.jpg");
        productFacade.append(product1);
    }


    //1. Retornar a lista completa de produtos ao chamar o método getAll.
    @Test
    public void retornarListaCompletaProduto(){
        Product product2 = new Product(2, "Bolo", 13.4f, "src\\test\\resources\\images\\Bolo.jpg");
        productFacade.append(product2);
        List<Product> products = productFacade.getAll();
        Assertions.assertTrue(products.containsAll(Arrays.asList(product1,product2)));
    }

    //2. Retornar o produto correto ao fornecer um ID válido no método getById.
    @Test
    public void retornarProdutoPorId(){
        Product product = productFacade.getById(1);
        Assertions.assertEquals(1,product.getId());
        Assertions.assertEquals("Hot Dog",product.getDescription());
        Assertions.assertEquals(10.4f,product.getPrice());
        Assertions.assertEquals("src\\test\\resources\\images\\1.jpg",product.getImage());
    }
    //3. Retornar true para um ID existente e false para um ID inexistente no método exists.
    @Test
    public void retornarTrueParaIdExistenteFalseParaIdInexistente(){
        Assertions.assertTrue(productFacade.exists(1));
        Assertions.assertFalse(productFacade.exists(2));
    }
    //4. Adicionar um novo produto corretamente ao chamar o método append.
    @Test
    public void adicionarNovoProdutoCorretamente(){
        Product product2 = new Product(2, "X-tudo", 12.4f, "src\\test\\resources\\images\\x-tudo.jpg");
        productFacade.append(product2);
        Assertions.assertTrue(productFacade.exists(2));
        Assertions.assertEquals("X-tudo",product2.getDescription());
        Assertions.assertEquals(12.4f,product2.getPrice());
        Assertions.assertEquals("src\\test\\resources\\images\\2.jpg",product2.getImage());
    }
    //5. Remover um produto existente ao fornecer um ID válido no método remove.
    @Test
    public void removerProdutoExistente(){
        Assertions.assertDoesNotThrow(()->{
            productFacade.remove(1);
        });
        Assertions.assertFalse(productFacade.exists(1));
        Assertions.assertThrows(NullPointerException.class,()->{
            productService.getImagePathById(1);
        });
    }
}
