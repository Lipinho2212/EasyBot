package semantic;

public class Simbolo {

    public String nome;

    public String tipo;

    public Simbolo(
        String nome,
        String tipo
    ) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }
}