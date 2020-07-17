package d2g.vetclinicwebproject.web.views.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class HandleAllException {

    @ExceptionHandler(Throwable.class)
    public ModelAndView handleException(Throwable exception) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", exception.getMessage());
        return modelAndView;
    }
}
