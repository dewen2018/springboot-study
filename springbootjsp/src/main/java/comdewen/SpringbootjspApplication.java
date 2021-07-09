package comdewen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@Controller
public class SpringbootjspApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootjspApplication.class, args);
    }

    @RequestMapping(value = "/say")
    public ModelAndView say() {
        ModelAndView mv=new ModelAndView();
        mv.addObject("msg","Hello , SpringBoot!!!");
        mv.setViewName("hello");
        return mv;
    }

//    @RequestMapping(value = "/speak")
//    public String speak(Model model) {
//        model.addAttribute("msg","Hello , World!!!");
//        return "result";
//    }
}
