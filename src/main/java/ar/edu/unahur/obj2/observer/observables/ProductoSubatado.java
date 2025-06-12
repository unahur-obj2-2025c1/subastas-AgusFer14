package ar.edu.unahur.obj2.observer.observables;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.unahur.obj2.observer.Oferta;
import ar.edu.unahur.obj2.observer.excepciones.OfertaSubastadorException;
import ar.edu.unahur.obj2.observer.observadores.Observer;

public class ProductoSubatado implements Observable{

    private List<Oferta> ofertas = new ArrayList<>();
    private Set<Observer> subastadores = new HashSet<>();

    @Override
    public void agregarObservador(Observer observer) {
        subastadores.add(observer);
    }

    @Override
    public void sacarObservador(Observer observer) {
        subastadores.remove(observer);
    }

    @Override
    public void notificar() {
        subastadores.stream().forEach(observer -> observer.actualizar(getUltimaOferta()));
    }

    public Oferta getUltimaOferta() {
        return ofertas.get(ofertas.size() - 1);
    }

    public void agregarOferta(Oferta oferta) {
        if(!subastadores.contains(oferta.getSubastador()))
            throw new OfertaSubastadorException(
                "El subastador " + oferta.getSubastador().getUserName() + " no participa en la subasta");
        ofertas.add(oferta);
        notificar();
    }

    public List<Oferta> getOfertas() {
        return ofertas;
    }

    public Set<Observer> getSubastadores() {
        return subastadores;
    }

    public void reiniciarSubasta() {
        ofertas.clear();
        subastadores.clear();
    }

}
