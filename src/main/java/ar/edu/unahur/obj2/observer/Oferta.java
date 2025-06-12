package ar.edu.unahur.obj2.observer;

import ar.edu.unahur.obj2.observer.observadores.Subastador;

public class Oferta {

    private final Subastador subastador;
    private final Integer valor;

    public Oferta(Subastador subastador, Integer valor) {
        this.subastador = subastador;
        this.valor = valor;
    }

    public Subastador getSubastador() {
        return subastador;
    }

    public Integer getValor() {
         return valor;
    }
}
