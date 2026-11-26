package br.unicentro.appconsultas;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import br.unicentro.appconsultas.service.VerificadorString;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class VerificadorStringTest{
    @ParameterizedTest
    @CsvSource({
        "'123', true",
        "'002', true",
        "'1vs2', false",
        "'batata', false",
        "'*(123)', false",
        "'ma12Msp1-', false"
    })

    void testarNumerico(String s, boolean deveSerValido){
        VerificadorString verificador = new VerificadorString();

        if(deveSerValido){
            assertTrue(verificador.numerico(s), "Deveria ser um valor numerico valido");
        }
        else{
            assertFalse(verificador.numerico(s), "Nao deveria ser um valor numerico valido");
        }
    }


    @ParameterizedTest
    @CsvSource({
        "'batata', true",
        "'umdoistres', true",
        "'123', false",
        "'1vs2', false",
        "'*(123)', false",
        "'ma12Msp1-', false"
    })
    void testarNaoNumerico(String s, boolean deveSerValido){
        VerificadorString verificador = new VerificadorString();

        if(deveSerValido){
            assertTrue(verificador.naoNumerico(s), "Deveria ser um valor nao numerico valido");
        }
        else{
            assertFalse(verificador.naoNumerico(s), "Nao deveria ser um valor nao numerico valido");
        }
    }
}