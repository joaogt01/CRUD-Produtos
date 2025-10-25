package service;

import model.Produto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoService {

    private List<Produto> produtos = new ArrayList<>();
    private final String ARQUIVO = "produtos.txt";

    public ProdutoService(){
        carregarDoArquivo();
    }

    public void cadastrar(String nome, double preco, int quantidade) {
        produtos.add(new Produto(nome, preco, quantidade));
        salvarNoArquivo();
    }

    public List<Produto> listar() {
        return produtos;
    }

    public boolean atualizar(int id, String nome, double preco, int quantidade) {
        if (id >= 0 && id < produtos.size()) {
            Produto p = produtos.get(id);
            p.setNome(nome);
            p.setPreco(preco);
            p.setQuantidade(quantidade);
            salvarNoArquivo();
            return true;
        }
        return false;
    }

    public boolean deletar(int id) {
        if (id >= 0 && id < produtos.size()) {
            produtos.remove(id);
            salvarNoArquivo();
            return true;
        }
        return false;
    }

    private void salvarNoArquivo(){
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ARQUIVO))){
            for (Produto p : produtos){
                escritor.write(p.getNome() + ";" + p.getPreco() + ";" + p.getQuantidade());
                escritor.newLine();
            }
        }catch (IOException e){
            System.out.println("Erro ao salvar no arquivo: " + e.getMessage());
        }
    }

    private void carregarDoArquivo(){
        File file = new File(ARQUIVO);
        if (!file.exists()) return;
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = reader.readLine()) != null){
                String[] dados = linha.split(";");
                String nome = dados[0];
                double preco = Double.parseDouble(dados[1]);
                int quantidade = Integer.parseInt(dados[2]);
                produtos.add(new Produto(nome, preco, quantidade));
            }
        }catch (IOException e){
            System.out.println("Falha ao ler o arquivo: " + e.getMessage());
        }
    }
}
