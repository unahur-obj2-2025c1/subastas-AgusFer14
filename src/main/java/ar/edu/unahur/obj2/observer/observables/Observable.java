package ar.edu.unahur.obj2.observer.observables;

import ar.edu.unahur.obj2.observer.observadores.Observer;

public interface Observable {

    void agregarObservador(Observer observer);

    void sacarObservador(Observer observer);

    void notificar();

}
