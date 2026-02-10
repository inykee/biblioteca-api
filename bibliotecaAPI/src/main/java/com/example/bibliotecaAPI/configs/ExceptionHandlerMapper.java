package com.example.bibliotecaAPI.configs;

import com.example.bibliotecaAPI.exceptions.ClienteBloqueadoException;
import com.example.bibliotecaAPI.exceptions.ExemplarNaoDisponivelException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class ExceptionHandlerMapper {

    // Retorna a data e hora atual formatada
    private String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

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

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleEntityNotFound(EntityNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(404);
        problemDetail.setTitle("Recurso não encontrado");
        problemDetail.setDetail(ex.getMessage());
        problemDetail.setProperty("dataHoraErro", now());

        return ResponseEntity.of(problemDetail).build();
    }
}