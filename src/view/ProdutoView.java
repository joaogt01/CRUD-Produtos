package view;

import model.Produto;
import service.ProdutoService;
import java.util.List;
import java.util.Scanner;

public class ProdutoView {

    private ProdutoService service = new ProdutoService();
    private Scanner scanner = new Scanner(System.in);

    public void exibirMenu() {
        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("0 - Sair");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Atualizar produto");
            System.out.println("4 - Deletar produto");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "0":
                    System.out.println("Encerrando...");
                    return;

                case "1":
                    cadastrar();
                    break;

                case "2":
                    listar();
                    break;

                case "3":
                    atualizar();
                    break;

                case "4":
                    deletar();
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrar() {
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Preço: ");
        double preco = Double.parseDouble(scanner.nextLine());

        System.out.print("Quantidade: ");
        int quantidade = Integer.parseInt(scanner.nextLine());

        service.cadastrar(nome, preco, quantidade);
        System.out.println("Produto cadastrado!");
    }

    private void listar() {
        List<Produto> produtos = service.listar();

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.println("\nLista de produtos:");
        for (int i = 0; i < produtos.size(); i++) {
            System.out.println("ID " + i + " - " + produtos.get(i));
        }
    }

    private void atualizar() {
        listar();

        System.out.print("Digite o ID do produto a ser atualizado: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();

        System.out.print("Novo preço: ");
        double preco = Double.parseDouble(scanner.nextLine());

        System.out.print("Nova quantidade: ");
        int quantidade = Integer.parseInt(scanner.nextLine());

        if (service.atualizar(id, nome, preco, quantidade)) {
            System.out.println("Produto atualizado!");
        } else {
            System.out.println("ID inválido!");
        }
    }

    private void deletar() {
        listar();

        System.out.print("Digite o ID do produto a ser deletado: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (service.deletar(id)) {
            System.out.println("Produto removido!");
        } else {
            System.out.println("ID inválido!");
        }
    }
}
