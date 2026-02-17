package com.example.bibliotecaAPI.configs;

import com.example.bibliotecaAPI.exceptions.ClienteBloqueadoException;
import com.example.bibliotecaAPI.exceptions.ExemplarNaoDisponivelException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerMapper {

    private String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    // ------------------ VALIDAÇÃO DE CAMPOS DO SPRING ------------------
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidation(MethodArgumentNotValidException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(400);
        problemDetail.setTitle("Erro de validação de campos");
        problemDetail.setDetail("Um ou mais campos estão inválidos");
        problemDetail.setProperty("dataHoraErro", now());

        Map<String, String> campos = new HashMap<>();
        for (FieldError erro : ex.getBindingResult().getFieldErrors()) {
            campos.put(erro.getField(), erro.getDefaultMessage());
        }
        problemDetail.setProperty("camposInvalidos", campos);

        return ResponseEntity.of(problemDetail).build();
    }

    // ------------------ VALIDAÇÃO DO HIBERNATE / JPA ------------------
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ProblemDetail> handleConstraintViolation(ConstraintViolationException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(400);
        problemDetail.setTitle("Erro de validação do banco de dados");
        problemDetail.setDetail("Um ou mais campos estão inválidos");
        problemDetail.setProperty("dataHoraErro", now());

        Map<String, String> camposInvalidos = new HashMap<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            String field = violation.getPropertyPath().toString();
            camposInvalidos.put(field, violation.getMessage());
        }
        problemDetail.setProperty("camposInvalidos", camposInvalidos);

        return ResponseEntity.of(problemDetail).build();
    }

    // ------------------ REGRAS DE NEGÓCIO ------------------
    @ExceptionHandler(ClienteBloqueadoException.class)
    public ResponseEntity<ProblemDetail> handleClienteBloqueado(ClienteBloqueadoException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(403);
        problemDetail.setTitle("Cliente não apto para empréstimo");
        problemDetail.setDetail(ex.getMessage());
        problemDetail.setProperty("dataHoraErro", now());
        problemDetail.setProperty("numeroRegraNegocio", ex.getNumeroRegraNegocio());
        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler(ExemplarNaoDisponivelException.class)
    public ResponseEntity<ProblemDetail> handleExemplarIndisponivel(ExemplarNaoDisponivelException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(409);
        problemDetail.setTitle("Exemplar indisponível para empréstimo");
        problemDetail.setDetail(ex.getMessage());
        problemDetail.setProperty("dataHoraErro", now());
        problemDetail.setProperty("numeroRegraNegocio", ex.getNumeroRegraNegocio());
        return ResponseEntity.of(problemDetail).build();
    }

    // ------------------ RECURSOS NÃO ENCONTRADOS ------------------
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleEntityNotFound(EntityNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(404);
        problemDetail.setTitle("Recurso não encontrado");
        problemDetail.setDetail(ex.getMessage());
        problemDetail.setProperty("dataHoraErro", now());
        return ResponseEntity.of(problemDetail).build();
    }

    // ------------------ ERROS DE INTEGRIDADE / UNIQUE ------------------
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ProblemDetail> handleDataIntegrity(DataIntegrityViolationException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(409);
        problemDetail.setTitle("Violação de integridade de dados");
        problemDetail.setDetail("Já existe um registro com os dados informados");
        problemDetail.setProperty("dataHoraErro", now());
        return ResponseEntity.of(problemDetail).build();
    }

    // ------------------ ERRO GENÉRICO ------------------
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleGenericException(Exception ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(500);
        problemDetail.setTitle("Erro interno do servidor");
        problemDetail.setDetail(ex.getMessage());
        problemDetail.setProperty("dataHoraErro", now());
        return ResponseEntity.of(problemDetail).build();
    }

}