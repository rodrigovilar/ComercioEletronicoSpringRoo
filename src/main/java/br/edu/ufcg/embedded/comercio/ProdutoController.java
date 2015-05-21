package br.edu.ufcg.embedded.comercio;
import br.edu.ufcg.embedded.comercio.dominio.Produto;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;

@RooWebJson(jsonObject = Produto.class)
@Controller
@RequestMapping("/produtoes")
@RooWebScaffold(path = "produtoes", formBackingObject = Produto.class)
public class ProdutoController {
}
