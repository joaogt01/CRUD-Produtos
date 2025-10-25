package test;

import model.Produto;
import service.ProdutoService;
import java.io.File;
import java.util.List;

public class testeProdutoService {
    public static void main(String[] args) {
        limparArquivo();
        testarCadastro();
        testarAtualizacao();
        testarRemocao();
        testarPersistencia();
    }

    private static void limparArquivo(){
        File file = new File("produtos.txt");
        if (file.exists()) file.delete();
    }


    private static void testarCadastro(){
        limparArquivo();
        ProdutoService service = new ProdutoService();
        service.cadastrar("mascara", 5.5, 10);
        List<Produto> produtos = service.listar();

        if (produtos.size() == 1 &&
        produtos.get(0).getNome().equals("mascara") &&
        produtos.get(0).getPreco() == 5.5 &&
        produtos.get(0).getQuantidade() == 10){
            System.out.println("TESTE CADASTRO: Passou");
        }else {
            System.out.println("TESTE CADASTRO: falhou");
        }
    }


    private static void testarAtualizacao(){
        limparArquivo();
        ProdutoService service = new ProdutoService();
        service.cadastrar("Produto Teste", 1.1, 1);

        boolean atualizou = service.atualizar(0, "Produto Teste Atualizado", 1.2, 2);
        Produto produto = service.listar().get(0);

        if (atualizou &&
        produto.getNome().equals("Produto Teste Atualizado")&&
        produto.getPreco() == 1.2 &&
        produto.getQuantidade() == 2){
            System.out.println("TESTE ATUALIZACAO: Passou");
        }else {
            System.out.println("TESTE ATUALIZACAO: Falhou");
        }
    }


    private static void testarRemocao(){
        limparArquivo();
        ProdutoService service = new ProdutoService();
        service.cadastrar("Produto1", 10.0, 1);
        boolean deletou = service.deletar(0);

        if (deletou && service.listar().isEmpty()){
            System.out.println("TESTE REMOCAO: Passou");
        }else {
            System.out.println("TESTE REMOCAO: Falhou");
        }
    }


    private static void testarPersistencia(){
        limparArquivo();
        ProdutoService service1 = new ProdutoService();
        service1.cadastrar("ProdutoTeste", 7.0, 5);

        ProdutoService service2 = new ProdutoService();
        List<Produto> produtos = service2.listar();

        if (!produtos.isEmpty() && produtos.get(0).getNome().equals("ProdutoTeste")){
            System.out.println("TESTE PERSISTENCIA: Passou");
        }else {
            System.out.println("TESTE PERSISTENCIA: Falhou");
        }
    }
}

