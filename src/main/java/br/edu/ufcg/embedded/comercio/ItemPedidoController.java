package br.edu.ufcg.embedded.comercio;
import br.edu.ufcg.embedded.comercio.dominio.ItemPedido;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;

@RooWebJson(jsonObject = ItemPedido.class)
@Controller
@RequestMapping("/itempedidoes")
@RooWebScaffold(path = "itempedidoes", formBackingObject = ItemPedido.class)
public class ItemPedidoController {
}
