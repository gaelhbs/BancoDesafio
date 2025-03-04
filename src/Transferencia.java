public interface Transferencia {
    public void transferir(double valor, IConta contaDestino);

    public void pix(double valor, String chavePix);

    public void transferirTed(double valor, IConta contaDestino);
}
