package Controller;

import Model.Acompanhamento;
import Model.Bebida;
import Model.Hamburguer;
import Model.Produto;
import Util.LoggerService;

import java.util.ArrayList;
import java.util.List;

public class ProdutoController {

    private static List<Produto> produtos = new ArrayList<>();

    public static void cadastrarProduto(Produto produto) {
        produtos.add(produto);
        LoggerService.log("CREATE: Produto cadastrado - Nome: " + produto.getNome() + ", Preço: " + produto.getPreco());
    }

    public static void editarProduto(int indice, Produto produtoAtualizado) {
        if (indice >= 0 && indice < produtos.size()) {
            produtos.set(indice, produtoAtualizado);
            LoggerService.log("UPDATE: Produto editado - Índice: " + indice + ", Novo Nome: " + produtoAtualizado.getNome());
        }
    }

    public static List<Produto> listarProdutos() {
        return produtos;
    }

    public static void cadastrarHamburguer(String nome, float preco) {
        Produto hamburguer = new Hamburguer(nome, preco);
        cadastrarProduto(hamburguer);
    }

    public static void cadastrarBebida(String nome, float preco) {
        Produto bebida = new Bebida(nome, preco);
        cadastrarProduto(bebida);
    }

    public static void cadastrarAcompanhamento(String nome, float preco) {
        Produto acompanhamento = new Acompanhamento(nome, preco);
        cadastrarProduto(acompanhamento);
    }


    public static void deletarProduto(Produto produto) {
        LoggerService.log("DELETE: Produto deletado - Nome: " + produto.getNome());
        produtos.remove(produto);
    }
}