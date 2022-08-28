package dev.duque.nttdata.modules.films.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Film not found")
public class NotFoundEpisodeException extends RuntimeException {
}