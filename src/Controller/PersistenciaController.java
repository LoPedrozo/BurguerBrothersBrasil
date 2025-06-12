
package Controller;

import Model.Cliente;
import Model.Pagamento;
import Model.Pedido;
import Model.Produto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaController {

    private static final String ARQUIVO_CLIENTES = "clientes.dat";
    private static final String ARQUIVO_PRODUTOS = "produtos.dat";
    private static final String ARQUIVO_PEDIDOS = "pedidos.dat";
    private static final String ARQUIVO_PAGAMENTOS = "pagamentos.dat";

    public static void salvarDados() {
        salvarObjeto(ClienteController.listarClientes(), ARQUIVO_CLIENTES);
        salvarObjeto(ProdutoController.listarProdutos(), ARQUIVO_PRODUTOS);
        salvarObjeto(PedidoController.listarPedidos(), ARQUIVO_PEDIDOS);
        salvarObjeto(PagamentoController.listarPagamentos(), ARQUIVO_PAGAMENTOS);
    }


    public static void carregarDados() {
    List<Cliente> clientes = (List<Cliente>) carregarObjeto(ARQUIVO_CLIENTES);
    if (clientes != null) {
        ClienteController.clientes.clear();
        ClienteController.clientes.addAll(clientes);
    }

    List<Produto> produtos = (List<Produto>) carregarObjeto(ARQUIVO_PRODUTOS);
    if (produtos != null) {
        ProdutoController.listarProdutos().clear();
        ProdutoController.listarProdutos().addAll(produtos);
    }

    List<Pedido> pedidos = (List<Pedido>) carregarObjeto(ARQUIVO_PEDIDOS);
    if (pedidos != null) {
        PedidoController.listarPedidos().clear();
        PedidoController.listarPedidos().addAll(pedidos);
    }
    
    List<Pagamento> pagamentos = (List<Pagamento>) carregarObjeto(ARQUIVO_PAGAMENTOS);
    if (pagamentos != null) {
        PagamentoController.listarPagamentos().clear();
        PagamentoController.listarPagamentos().addAll(pagamentos);
    }
}



    private static void salvarObjeto(Object obj, String nomeArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(obj);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados em " + nomeArquivo + ": " + e.getMessage());
        }
    }

    private static Object carregarObjeto(String nomeArquivo) {
        File arquivo = new File(nomeArquivo);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar dados de " + nomeArquivo + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }
}