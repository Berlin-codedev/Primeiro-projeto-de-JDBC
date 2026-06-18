package model;

public class Pedido {
    private int idPedido;
    private int qtdp;
    private Cliente cliente;
    public void setIdPedido(int idPedido){this.idPedido = idPedido; }
    public int getIdPedido(){return idPedido;}
    public int getQtdp(){return qtdp;}
    public void setQtdp(int qtdp){
        if (qtdp >= 0){
            this.qtdp = qtdp;
        } else {
            System.out.println("Erro: A quantidade não pode ser negativa!");
        }
    }
    public Cliente getCliente(){return cliente;}
    public void setCliente(Cliente cliente){this.cliente = cliente;}

}
