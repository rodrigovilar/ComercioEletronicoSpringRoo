package br.edu.ufcg.embedded.comercio;
import br.edu.ufcg.embedded.comercio.dominio.Cliente;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;

@RooWebJson(jsonObject = Cliente.class)
@Controller
@RequestMapping("/clientes")
@RooWebScaffold(path = "clientes", formBackingObject = Cliente.class)
public class ClienteController {
}
