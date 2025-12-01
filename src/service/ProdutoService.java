package service;

import model.Produto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ProdutoService {

    private List<Produto> produtos = new ArrayList<>();
    private final String ARQUIVO;

    public ProdutoService() throws IOException {
        this("produtos.txt", true);
    }

    public ProdutoService(String arquivo, boolean carregar) throws IOException{
        this.ARQUIVO = arquivo;
        if (carregar){
            carregarDoArquivo();
        }
    }


    public void cadastrar(String nome, double preco, int quantidade) throws IOException {
        produtos.add(new Produto(nome, preco, quantidade));
        salvarNoArquivo();
    }

    public List<Produto> listar() {
        return produtos;
    }

    public boolean atualizar(int id, String nome, double preco, int quantidade) throws IOException{
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

    public boolean deletar(int id) throws IOException {
        if (id >= 0 && id < produtos.size()) {
            produtos.remove(id);
            salvarNoArquivo();
            return true;
        }
        return false;
    }

    private void salvarNoArquivo() throws IOException {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Produto p : produtos) {
                escritor.write(p.getNome() + ";" + p.getPreco() + ";" + p.getQuantidade());
                escritor.newLine();
            }
        }
    }

    private void carregarDoArquivo() throws IOException {
        File file = new File(ARQUIVO);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))){

        String linha;
        while ((linha = reader.readLine()) != null){
            linha = linha.trim();
            if (linha.isEmpty()) continue;

            String[] dados = linha.split(";");

            if (dados.length < 3){
                continue;
            }

                String nome = dados[0];
                double preco = Double.parseDouble(dados[1]);
                int quantidade = Integer.parseInt(dados[2]);
                produtos.add(new Produto(nome, preco, quantidade));
        }
        }
    }
}
