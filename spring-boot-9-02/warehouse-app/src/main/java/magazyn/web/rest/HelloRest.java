package magazyn.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloRest {
    @RequestMapping(value="/hello",method= RequestMethod.GET)
    String sayHello() {
        return "Hello World!";
    }
}
