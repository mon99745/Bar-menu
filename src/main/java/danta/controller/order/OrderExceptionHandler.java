package danta.controller.order;

import danta.exception.NotEnoughStockQuantityException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import springfox.documentation.annotations.ApiIgnore;

@ControllerAdvice
public class OrderExceptionHandler {
    @ApiIgnore
    @ExceptionHandler(NotEnoughStockQuantityException.class)
    public String notEnoughStockQuantityExceptionHandler(NotEnoughStockQuantityException e,
                                                         Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error/error";
    }
}
