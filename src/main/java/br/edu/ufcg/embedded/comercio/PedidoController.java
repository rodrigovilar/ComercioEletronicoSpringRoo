package br.edu.ufcg.embedded.comercio;
import br.edu.ufcg.embedded.comercio.dominio.Pedido;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;

@RooWebJson(jsonObject = Pedido.class)
@Controller
@RequestMapping("/pedidoes")
@RooWebScaffold(path = "pedidoes", formBackingObject = Pedido.class)
public class PedidoController {
}
