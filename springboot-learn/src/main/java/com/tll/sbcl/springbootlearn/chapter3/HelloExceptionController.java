package com.tll.sbcl.springbootlearn.chapter3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tll on 2017/11/2 13:18
 **/
@Controller
public class HelloExceptionController {

    @RequestMapping("/json")
    public String json() throws MyException{
        throw new MyException("发生错误");
    }
}
