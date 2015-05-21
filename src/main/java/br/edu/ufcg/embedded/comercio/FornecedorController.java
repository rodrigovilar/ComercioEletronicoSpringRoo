package br.edu.ufcg.embedded.comercio;
import br.edu.ufcg.embedded.comercio.dominio.Fornecedor;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;

@RooWebJson(jsonObject = Fornecedor.class)
@Controller
@RequestMapping("/fornecedors")
@RooWebScaffold(path = "fornecedors", formBackingObject = Fornecedor.class)
public class FornecedorController {
}
