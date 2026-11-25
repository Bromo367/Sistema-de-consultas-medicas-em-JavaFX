package br.unicentro.appconsultas;

import java.time.format.DateTimeParseException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import br.unicentro.appconsultas.controller.ConsultaController;
import br.unicentro.appconsultas.model.Consulta;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class DataTest{
    @ParameterizedTest
    @CsvSource({
        "'2026-11-11', true",
        "'2027-02-01', true",
        "'2026-02-29', false",
        "'batata-11-11', false",
        "'2026-1-10', false",
        "'26-10-03', false",
        "'2025-07-40', false"
    })
    void testarData(String data, boolean deveSerValido){
        ConsultaController consulta = new ConsultaController();

        if(deveSerValido){
            assertDoesNotThrow(() -> consulta.validarData(data), "Deveria ser uma data valida");
        }
        else{
                assertThrows(DateTimeParseException.class, 
                    () -> consulta.validarData(data), "Nao deveria ser uma data valida");
        }
    }
}