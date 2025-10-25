## 🛍️ CRUD de Produtos em Java

💡 Um sistema simples de cadastro de produtos em Java puro, com persistência em arquivo .txt, desenvolvido para fins educacionais e acadêmicos.
Projeto organizado em camadas Model - Service - View, aplicando os princípios básicos de POO e boas práticas de arquitetura.
---
## 🚀 Funcionalidades
| Função | Descrição |
|---------|-----------|
| 🆕 Cadastrar	 | Adiciona um novo produto com nome, preço e quantidade |
| 📋 Listar	| Exibe todos os produtos cadastrados |
| ✏️ Atualizar |	Permite editar um produto existente | 
| 🗑️ Remover	 | Exclui um produto da lista e do arquivo |
| 💾 Persistir	| Salva automaticamente os dados no arquivo produtos.txt |
---
## 🧠 Tecnologias Utilizadas

☕ Java 17+

🗂️ Arquitetura MVC (Model-View-Service)

💾 Persistência via arquivo .txt

🧪 Testes manuais sem JUnit


---
## 🏗️ Estrutura do Projeto
```bash
src/
 ├── model/
 │    └── Produto.java
 ├── service/
 │    └── ProdutoService.java
 ├── view/
 │    └── ProdutoView.java
 ├── teste/
 │    └── TesteProdutoService.java
 └── App.java
```
---
## 🧩 Explicação das Camadas


| Camada	| Responsabilidade |	Arquivo |
|---------|-----------|-----------|
|🧱 Model	 | Representa o produto com atributos e métodos |	Produto.java |
|⚙️ Service	 | Gerencia a lista de produtos e salva no arquivo |	ProdutoService.java |
|🖥️ View | 	Exibe o menu e interage com o usuário |	ProdutoView.java |
|🎬 App	| Inicia o sistema	| App.java |
|🧪 Testes	| Valida as funcionalidades |	TesteProdutoService.java |

---

## 💾 Persistência dos Dados:

Os produtos são armazenados no arquivo produtos.txt, no formato:

nome;preco;quantidade


## 📁 Exemplo:

Arroz;5.50;10


Feijão;8.00;5


Café;7.00;3


📌 O arquivo é criado automaticamente na pasta do projeto, e os dados são:

Gravados após cada operação (cadastro, edição, exclusão);

Carregados automaticamente na inicialização do programa.

---

## 🧪 Testes (Sem JUnit)

Os testes são realizados no arquivo TesteProdutoService.java, simulando os principais casos do CRUD.
Eles exibem o resultado diretamente no terminal.

## 🔍 Casos de Teste
|Teste |	Objetivo	| Resultado esperado|
|-----------|-----------|-----------|
| ✅ testarCadastro()	| Verifica se o produto é adicionado corretamente |	Passou |
| ✅ testarAtualizacao()	| Confirma a alteração dos dados	| Passou |
| ✅ testarRemocao()	| Garante a exclusão correta	| Passou |
| ✅ testarPersistencia() | Testa leitura e gravação no arquivo |	Passou | 

📋 Saída esperada no console:
```bash
TESTE CADASTRO: Passou
TESTE ATUALIZACAO: Passou
TESTE REMOCAO: Passou
TESTE PERSISTENCIA: Passou
```
🖥️ Como Executar
🔧 Pré-requisitos

Java JDK 17+ instalado

Um editor de código ou terminal (VS Code, IntelliJ, Eclipse etc.)

▶️ Rodando o Programa

1️⃣ Compile os arquivos
```bash
javac src/**/*.java -d out
```

2️⃣ Execute o aplicativo
```bash
java -cp out App
```

3️⃣ Rode os testes
```bash
java -cp out TesteProdutoService
```

📸 Exemplo de Execução
```bash
=== MENU ===
0 - Sair
1 - Cadastrar produto
2 - Listar produtos
3 - Atualizar produto
4 - Deletar produto

Escolha uma opção: 1
Digite o nome do produto: Café
Digite o preço: 17.0
Digite a quantidade: 3
✅ Produto cadastrado!
```
🎓 Conceitos Envolvidos

Programação Orientada a Objetos (POO)

Encapsulamento e métodos getters/setters

Manipulação de arquivos (BufferedWriter / BufferedReader)

Estrutura de dados (List, ArrayList)

Estrutura de controle (switch, loops)

Modularização e reuso de código

---

## 👨‍💻 Autor

João Victor
📘 Estudante de Sistemas de Informação — UPE


💬 Projeto acadêmico para estudo de Java, POO e persistência de dados.

🌐 Desenvolvido com foco em aprendizado e boas práticas.


## 🏁 Licença

Este projeto é de uso livre para fins educacionais.
Sinta-se à vontade para copiar, modificar e aprimorar o código.
