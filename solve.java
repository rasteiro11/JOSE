/**
 * solve
 */
public class solve {
    private int n;
    private int M;
    private ListaDuplamenteLigadaCircular lista;

    solve(int n)
    {
        this.n = n;
        this.M = 0;
        this.lista = new ListaDuplamenteLigadaCircular();
        for (int i = 0; i < n; i++) {
            lista.inserirInicio("Individuo" + Integer.toString(i + 1)); 
        }
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return M;
    }

    public void setM(int m) {
        M = m;
    }

    public ListaDuplamenteLigadaCircular getLista() {
        return lista;
    }

    public void setLista(ListaDuplamenteLigadaCircular lista) {
        this.lista = lista;
    }

    public void kill(int M) 
    {
        this.M += M;
        this.M %= this.n;
        No auxN = lista.getInicio();
        int i = 0;
        while(i < this.M)
        {
            auxN = auxN.getProximo();
            i++;
        }
        long key = auxN.getId();
        lista.remover(key);
        if(this.M == this.n)
            this.M--;
    }
}
