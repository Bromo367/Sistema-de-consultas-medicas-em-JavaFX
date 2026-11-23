package br.unicentro.appconsultas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import br.unicentro.appconsultas.build.PacienteBuilder;
import br.unicentro.appconsultas.build.MedicoBuilder;
import br.unicentro.appconsultas.model.Medicamento;
import br.unicentro.appconsultas.service.MedicamentoService;
import br.unicentro.appconsultas.model.Paciente;
import br.unicentro.appconsultas.service.PacienteService;
import br.unicentro.appconsultas.model.Medico;
import br.unicentro.appconsultas.service.MedicoService;
import br.unicentro.appconsultas.service.ServiceException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ServiceTest {
    @ParameterizedTest
    @CsvSource({
    "'Ômega 3', 1, true",
    "'Dipirona', 1, true",
    "'Benegrip', 0, false",
    "'Medicamento', -1, false"
    })
    void testarMedicamentos(String nome, int medicamentoId, boolean deveSerValido){
        MedicamentoService medicamentoService = new MedicamentoService();
        Medicamento medicamento = new Medicamento(medicamentoId, nome);
        if(deveSerValido){
            assertDoesNotThrow(() -> medicamentoService.validar(medicamento), "Deveria ser um medicamento valido");
        }
        else{
            assertThrows(ServiceException.class, 
                    () -> medicamentoService.validar(medicamento), "Nao deveria ser um medicamento valido");
        }
        
    }

    @ParameterizedTest
    @CsvSource({
    "'11111111111', 'Bruno', 19, 56.7, 1.8, '999999999', true",
    "'22222222222', 'Heron', 19, 60, 1.8, '99999', true",
    "'1111111111', 'Fulano', 19, 56.7, 1.8, '999999999', false",
    "'11111111111', 'Fulano2', 19, 56.7, 1.8, '999999999', false",
    "'11111111111', 'Ronaldo', 0, 56.7, 1.8, '999999999', false",
    "'11111111111', 'Ciclano', 19, -8, 1.8, '999999999', false",
    "'11111111111', 'Beltrano', 19, -8, 1.8, 'telefone', false",
    "'11111111111', 'Alberta', 19, 56.7, -29, '999999999', false"
    })
    void testarPacientes(String cpfPaciente, String nome, int idade, double peso, double altura, String telefone, boolean deveSerValido){
        PacienteService pacienteService = new PacienteService();
        Paciente paciente = new Paciente(cpfPaciente, nome, idade, peso, altura, telefone);
        if(deveSerValido){
            assertDoesNotThrow(() -> pacienteService.validar(paciente), "Deveria ser um paciente valido");
        }
        else{
            assertThrows(ServiceException.class, 
                    () -> pacienteService.validar(paciente), "Nao deveria ser um paciente valido");
        }
        
    }

    @ParameterizedTest
    @CsvSource({
    "'11111-PR', 'Bruno', 'Especializado em codigo', '999999999', true",
    "'2222', 'Heron', 'Amador profissional','99999', true",
    "'333-UF', 'Erro1', 'Cardiologia', '999999999', false",
    "'444-AB', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', 'fonoaudiologia', '999999999', false",
    "'555555555', 'Ronaldo', 'oncologia', 'telefone', false"
    })
    void testarMedicos(String crmMedico, String nome, String especializacao, String telefone, boolean deveSerValido){
        MedicoService medicoService = new MedicoService();
        Medico medico = new Medico(crmMedico, nome, especializacao, telefone);
        if(deveSerValido){
            assertDoesNotThrow(() -> medicoService.validar(medico), "Deveria ser um medico valido");
        }
        else{
            assertThrows(ServiceException.class, 
                    () -> medicoService.validar(medico), "Nao deveria ser um medico valido");
        }
        
    }
}
