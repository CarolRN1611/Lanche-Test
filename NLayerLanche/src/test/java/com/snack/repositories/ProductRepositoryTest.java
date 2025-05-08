package com.snack.repositories;

import com.snack.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ProductRepositoryTest {
    private ProductRepository productRepository;
    private Product product1;

    @BeforeEach
    public void setup() {
        productRepository = new ProductRepository();
        product1 = new Product(1, "Hot Dog", 10.4f, "");
        productRepository.append(product1);
    }

    //1. Verificar se um produto é adicionado corretamente ao repositório (List)
    @Test
    public void verificarSeOProdutoEstaNoArray() {
        Product productId1 = productRepository.getById(1);
        Assertions.assertEquals("Hot Dog",productId1.getDescription());
        Assertions.assertEquals(10.4f,productId1.getPrice());
        Assertions.assertEquals("",productId1.getImage());
    }

    //2. Verificar se é possível recuperar um produto usando seu ID
    @Test
    public void verificarSePossivelRecuperarProdutoUsandoId(){
        Product product2 = new Product(2, "X-tudo", 12.4f, "");
        productRepository.append(product2);
        Product product = productRepository.getById(2);
        Assertions.assertEquals("X-tudo",product.getDescription());
        Assertions.assertEquals(12.4f,product.getPrice());
        Assertions.assertEquals("",product.getImage());

    }
    
    //3. Confirmar se um produto existe no repositório (List)
    @Test
    public void verificarSeProdutoExisteNoRepositorio(){
        Assertions.assertTrue(productRepository.exists(1)); ;
    }
    
    //4. Testar se um produto é removido corretamente do repositório (List)
    @Test
    public void testarProdutoRemovidoCorretamente(){
        Product product2 = new Product(2, "X-tudo", 12.4f, "");
        productRepository.append(product2);
        productRepository.remove(2);
        Assertions.assertFalse(productRepository.exists(2));
    }

    //5. Verificar se um produto é atualizado corretamente
    @Test
    public void verificarSeProdutoAtualizadoCorretamente(){
        Product product2 = new Product(1, "X-tudo", 12.4f, "");
        productRepository.update(1,product2);
        Product product = productRepository.getById(1);
        Assertions.assertEquals("X-tudo",product.getDescription());
        Assertions.assertEquals(12.4f,product.getPrice());
        Assertions.assertEquals("",product.getImage());
    }

    //6. Testar se todos os produtos armazenados são recuperados corretamente
    @Test
    public void testarTodosProdutosArmazenadosRecuperados(){
        Product product2 = new Product(2, "X-tudo", 12.4f, "");
        productRepository.append(product2);
        List<Product> products = productRepository.getAll();
        Assertions.assertEquals(2,products.size());
        Assertions.assertTrue(products.containsAll(Arrays.asList(product1,product2)));
    }

    //7. Verificar o comportamento ao tentar remover um produto que não existe
    @Test
    public void tentarRemoverProdutoInexistente(){
        Assertions.assertThrows(NullPointerException.class,()->{
            productRepository.remove(10);
        });
    }

    //8. Testar o que acontece ao tentar atualizar um produto que não está no repositório (List)
    @Test
    public void tentarRemoverAtualizarInexistente(){
        Assertions.assertThrows(Exception.class,()->{
            productRepository.update(3,product1);
        });
    }
    
    //9. Verificar se o repositório aceita a adição de produtos com IDs duplicados (espera-se que não)
    @Test
    public void verificarRepositorioAdicionarIdDuplicado(){
        Product product2 = new Product(1, "X-tudo", 12.4f, "");
        productRepository.append(product2);

    }

    //10. Confirmar que o repositório retorna uma lista vazia ao ser inicializado (List)
    @Test
    public void verificarListaVaziaAoInicializar(){
        ProductRepository productRepository = new ProductRepository();
        List<Product> products = productRepository.getAll();
        Assertions.assertTrue(products.isEmpty());
    }	
}
