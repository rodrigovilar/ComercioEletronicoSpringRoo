package br.edu.ufcg.embedded.comercio;
import br.edu.ufcg.embedded.comercio.dominio.Vendedor;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;

@RooWebJson(jsonObject = Vendedor.class)
@Controller
@RequestMapping("/vendedors")
@RooWebScaffold(path = "vendedors", formBackingObject = Vendedor.class)
public class VendedorController {
}
