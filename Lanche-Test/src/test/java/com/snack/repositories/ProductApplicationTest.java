package com.snack.repositories;

import com.snack.applications.ProductApplication;
import com.snack.entities.Product;
import com.snack.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ProductApplicationTest {
    private ProductApplication productApplication;
    private ProductRepository productRepository;
    private ProductService productService;
    private Product product1;

    @BeforeEach
    public void setup() {
        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService();
        productApplication = new ProductApplication(productRepository,productService);
        product1 = new Product(1, "Hot Dog", 10.4f, "src\\test\\resources\\images\\Hot-dog.jpg");
        productApplication.append(product1);
    }

    //1. Listar todos os produtos do repositório
    @Test
    public void listarTodosProdutosRepositorio(){
        Product product2 = new Product(2, "Bolo", 13.4f, "src\\test\\resources\\images\\Bolo.jpg");
        productApplication.append(product2);
        List<Product> products = productApplication.getAll();
        Assertions.assertTrue(products.containsAll(Arrays.asList(product1,product2)));
    }


    //2. Obter um produto por ID válido
    @Test
    public void obterProdutoIDValido(){
       Product product =productApplication.getById(1);
       Assertions.assertEquals(1,product.getId());
       Assertions.assertEquals("Hot Dog",product.getDescription());
       Assertions.assertEquals(10.4f,product.getPrice());
        Assertions.assertEquals("src\\test\\resources\\images\\1.jpg",product.getImage());
    }

    //3. Retornar nulo ou erro ao tentar obter produto por ID inválido
    @Test
    public void obterProdutoIDInvalido(){
        Assertions.assertThrows(Exception.class,()->{
            productApplication.getById(2);
        });
    }
    //4. Verificar se um produto existe por ID válido
    @Test
    public void verificarProdutoExistePorIDValido(){
    }
    //5. Retornar falso ao verificar a existência de um produto com ID inválido
    @Test
    public void verificarProdutoExistePorIDInvalido(){
    }

    //6. Adicionar um novo produto e salvar sua imagem corretamente
    @Test
    public void adicionarNovoProdutoSalvarImagem(){
    }
    //7. Remover um produto existente e deletar sua imagem
    @Test
    public void removerProdutoExistenteDeletarImagem(){
    }
    //8. Não alterar o sistema ao tentar remover um produto com ID inexistente
    @Test
    public void naoAlterarSistemaAoRemoverProdutoIdInexistente(){
    }
    //9. Atualizar um produto existente e substituir sua imagem
    @Test
    public void atualizarProdutoExistenteSubistituirImagem() {
    }
}