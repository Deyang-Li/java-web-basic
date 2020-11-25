package com.gl8bl.basic.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/index")
public class IndexController {

  @GetMapping("")
  public ModelAndView index(HttpServletRequest request) {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("index");
    return mv;
  }
}
