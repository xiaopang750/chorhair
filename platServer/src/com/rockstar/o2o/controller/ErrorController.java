package com.rockstar.o2o.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController
{
  @RequestMapping({"/error/{errorType}"})
  public ModelAndView redirect(@PathVariable("errorType") String errorType)
  {
    ModelAndView view = new ModelAndView("error/" + errorType);
    return view;
  }
}