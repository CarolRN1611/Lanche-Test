package com.snack.repositories;

import com.snack.applications.ProductApplication;
import com.snack.entities.Product;
import com.snack.services.ProductService;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductApplicationTest {
    private ProductApplication productApplication;
    private ProductRepository productRepository;
    private ProductService productService;
    private Product product1;

    @BeforeEach
    public void setup() {
        productRepository = new ProductRepository();
        productService = new ProductService();
        productApplication = new ProductApplication(productRepository,productService);
        product1 = new Product(1, "Hot Dog", 10.4f, "src\\test\\resources\\images\\Hot-dog.jpg");
        productApplication.append(product1);
    }

    //1. Listar todos os produtos do repositório
    @Test
    @Order(1)
    public void listarTodosProdutosRepositorio(){
        Product product2 = new Product(2, "Bolo", 13.4f, "src\\test\\resources\\images\\Bolo.jpg");
        productApplication.append(product2);
        List<Product> products = productApplication.getAll();
        Assertions.assertTrue(products.containsAll(Arrays.asList(product1,product2)));
    }


    //2. Obter um produto por ID válido
    @Test
    @Order(2)
    public void obterProdutoIDValido(){
       Product product =productApplication.getById(1);
       Assertions.assertEquals(1,product.getId());
       Assertions.assertEquals("Hot Dog",product.getDescription());
       Assertions.assertEquals(10.4f,product.getPrice());
        Assertions.assertEquals("src\\test\\resources\\images\\1.jpg",product.getImage());
    }

    //3. Retornar nulo ou erro ao tentar obter produto por ID inválido
    @Test
    @Order(3)
    public void obterProdutoIDInvalido(){
        Assertions.assertThrows(Exception.class,()->{
            productApplication.getById(2);
        });
    }

    //4. Verificar se um produto existe por ID válido
    @Test
    @Order(4)
    public void verificarProdutoExistePorIDValido(){
        Assertions.assertTrue(productApplication.exists(1));
    }

    //5. Retornar falso ao verificar a existência de um produto com ID inválido
    @Test
    @Order(5)
    public void verificarProdutoExistePorIDInvalido(){
        Assertions.assertFalse(productApplication.exists(2));
    }

    //6. Adicionar um novo produto e salvar sua imagem corretamente
    @Test
    @Order(6)
    public void adicionarNovoProdutoSalvarImagem(){
        Product product2 = new Product(2, "X-tudo", 12.4f, "src\\test\\resources\\images\\x-tudo.jpg");
        productApplication.append(product2);
        Assertions.assertTrue(productApplication.exists(2));
        Assertions.assertEquals("X-tudo",product2.getDescription());
        Assertions.assertEquals(12.4f,product2.getPrice());
        Assertions.assertEquals("src\\test\\resources\\images\\2.jpg",product2.getImage());
    }
    //7. Remover um produto existente e deletar sua imagem
    @Test
    @Order(7)
    public void removerProdutoExistenteDeletarImagem(){
        Assertions.assertDoesNotThrow(()->{
            productApplication.remove(1);
        });
        Assertions.assertFalse(productApplication.exists(1));
        Assertions.assertThrows(Exception.class,()->{
            productService.getImagePathById(1);
        });
    }

    //8. Não alterar o sistema ao tentar remover um produto com ID inexistente
    @Test
    @Order(8)
    public void naoAlterarSistemaAoRemoverProdutoIdInexistente(){
        Assertions.assertThrows(Exception.class, () -> {
            productApplication.remove(10);
        });
        Assertions.assertTrue(productApplication.exists(1));
        String expectedPath = "src\\test\\resources\\images\\1.jpg";
        Assertions.assertEquals(expectedPath,productApplication.getById(1).getImage());
    }

    //9. Atualizar um produto existente e substituir sua imagem
    @Test
    @Order(9)
    public void atualizarProdutoExistenteSubstituirImagem() {
        Product productAtualizado = new Product(1, "X-tudo", 12.4f, "src\\test\\resources\\images\\x-tudo.jpg");
        Assertions.assertDoesNotThrow(() -> {
            productApplication.update(1, productAtualizado);
        });
        Product produtoSalvo = productApplication.getById(1);
        Assertions.assertEquals("X-tudo", produtoSalvo.getDescription());
        Assertions.assertEquals(12.4f, produtoSalvo.getPrice());
        Assertions.assertEquals("src\\test\\resources\\images\\x-tudo.jpg", produtoSalvo.getImage());
    }
}