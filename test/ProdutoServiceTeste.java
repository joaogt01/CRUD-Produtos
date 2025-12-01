import org.junit.jupiter.api.*;
import service.ProdutoService;
import model.Produto;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class ProdutoServiceTeste {

    private static final String ARQUIVO_TESTE = "produtos_test.txt";
    private ProdutoService service;

    @BeforeEach
    void setup() throws IOException{
        File file = new File(ARQUIVO_TESTE);
        if (file.exists()) {
            file.delete();
        }
        service = new ProdutoService(ARQUIVO_TESTE, false);
    }

    @Test
    void testeCadastrar() throws IOException {
        service.cadastrar("Festim", 2.5, 10);

        List<Produto> lista = service.listar();

        assertEquals(1, lista.size());
        assertEquals("Festim", lista.get(0).getNome());
        assertEquals(2.5, lista.get(0).getPreco());
        assertEquals(10, lista.get(0).getQuantidade());
    }

    @Test
    void testeListar() throws IOException {
        service.cadastrar("Jujuba", 1, 1);
        service.cadastrar("chapeu", 2, 2);

        List<Produto> lista = service.listar();

        assertEquals(2, lista.size());
    }

    @Test
    void testeAtualizarValido() throws IOException {
        service.cadastrar("goiabada", 1, 1);

        boolean ok = service.atualizar(0, "Novo", 15.0, 50);

        assertTrue(ok);
        assertEquals("Novo", service.listar().get(0).getNome());
        assertEquals(15.0, service.listar().get(0).getPreco());
        assertEquals(50, service.listar().get(0).getQuantidade());
    }

    @Test
    void testeAtualizarInvalido() throws IOException {
        boolean ok = service.atualizar(5, "X", 1, 1);
        assertFalse(ok);
    }

    @Test
    void testeDeletarValido() throws IOException {
        service.cadastrar("teste1", 1, 1);
        service.cadastrar("teste", 2, 2);

        boolean deletar = service.deletar(0);

        assertTrue(deletar);
        assertEquals(1, service.listar().size());
        assertEquals("teste", service.listar().get(0).getNome());
    }

    @Test
    void testeDeletarInvalido() throws IOException {
        boolean deletar = service.deletar(10);
        assertFalse(deletar);
    }

    @Test
    void testeCarregarDoArquivo() throws IOException {
        service.cadastrar("teste", 1, 1);

        ProdutoService novoService = new ProdutoService(ARQUIVO_TESTE, true);
        List<Produto> lista = novoService.listar();
        assertEquals(1, lista.size());
        assertEquals("teste", lista.get(0).getNome());
        assertEquals(1.0, lista.get(0).getPreco());
        assertEquals(1, lista.get(0).getQuantidade());
    }
}
