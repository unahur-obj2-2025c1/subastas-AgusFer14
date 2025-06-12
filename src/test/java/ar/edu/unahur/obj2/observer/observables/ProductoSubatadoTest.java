package ar.edu.unahur.obj2.observer.observables;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.observer.excepciones.OfertaSubastadorException;
import ar.edu.unahur.obj2.observer.observadores.Subastador;

class ProductoSubatadoTest {

    Subastador s1 = new Subastador("gonzager");
    Subastador s2 = new Subastador("diazdan");
    Subastador s3 = new Subastador("martomau");

    ProductoSubatado productoSubastado = new ProductoSubatado();

    @BeforeEach
    void setUp() {

        productoSubastado.reiniciarSubasta();
        s1.reiniciarSubastador();
        s2.reiniciarSubastador();
        s3.reiniciarSubastador();

        productoSubastado.agregarObservador(s1);
        productoSubastado.agregarObservador(s3);

    }

    void escenario1() {
        productoSubastado.agregarOferta(s3.crearOferta());
        productoSubastado.agregarOferta(s1.crearOferta());
        productoSubastado.agregarOferta(s3.crearOferta());
    }

    @Test
    void dadoElEscenario1_seVerifiqueLaCorrectaNofificacionDeLaUltimaOfertaALosSubastadores() {

        escenario1();

        assertEquals(s3, s1.getUltimaOferta().getSubastador());
        assertEquals(s3, s3.getUltimaOferta().getSubastador());
    }

    @Test
    void dadoElEscenario1_seVverificarQueLaUltimaOfertaRegistradaPerteneceAlSubastadorCorrecto() {

        escenario1();

        assertTrue(productoSubastado.getUltimaOferta().equals(s3.getUltimaOferta()));
    }

    @Test
    void dadoElEscenario1_seVerificaQueElValorDeLaUltimaOfertaSeaLaCorrecta() {

        escenario1();

        assertEquals(30, productoSubastado.getUltimaOferta().getValor());
    }

    @Test
    void dadoElEscenario1_seVerificaQueLaCantidadDeOfertasRecibidasSeaLaCorrecta() {

        escenario1();

        assertEquals(3, productoSubastado.getOfertas().size());
    }

    @Test
    void adoElEscenario1_alAgregarUnOfertaDeUnUsuarioQueNoParticipaEnLaSubsta_seProduceUnaExcepcion() {

        escenario1();

        assertThrowsExactly(OfertaSubastadorException.class,
                            () -> { productoSubastado.agregarOferta(s2.crearOferta());
                            }, "El subastador diazdan no participa en la subasta");
    }

}
