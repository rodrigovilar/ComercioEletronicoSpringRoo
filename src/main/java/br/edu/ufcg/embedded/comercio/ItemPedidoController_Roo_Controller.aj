// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.edu.ufcg.embedded.comercio;

import br.edu.ufcg.embedded.comercio.ItemPedidoController;
import br.edu.ufcg.embedded.comercio.dominio.ItemPedido;
import br.edu.ufcg.embedded.comercio.dominio.Pedido;
import br.edu.ufcg.embedded.comercio.dominio.Produto;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect ItemPedidoController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String ItemPedidoController.create(@Valid ItemPedido itemPedido, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, itemPedido);
            return "itempedidoes/create";
        }
        uiModel.asMap().clear();
        itemPedido.persist();
        return "redirect:/itempedidoes/" + encodeUrlPathSegment(itemPedido.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String ItemPedidoController.createForm(Model uiModel) {
        populateEditForm(uiModel, new ItemPedido());
        List<String[]> dependencies = new ArrayList<String[]>();
        if (Produto.countProdutoes() == 0) {
            dependencies.add(new String[] { "produto", "produtoes" });
        }
        if (Pedido.countPedidoes() == 0) {
            dependencies.add(new String[] { "pedido", "pedidoes" });
        }
        uiModel.addAttribute("dependencies", dependencies);
        return "itempedidoes/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String ItemPedidoController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("itempedido", ItemPedido.findItemPedido(id));
        uiModel.addAttribute("itemId", id);
        return "itempedidoes/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String ItemPedidoController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("itempedidoes", ItemPedido.findItemPedidoEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) ItemPedido.countItemPedidoes() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("itempedidoes", ItemPedido.findAllItemPedidoes(sortFieldName, sortOrder));
        }
        return "itempedidoes/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String ItemPedidoController.update(@Valid ItemPedido itemPedido, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, itemPedido);
            return "itempedidoes/update";
        }
        uiModel.asMap().clear();
        itemPedido.merge();
        return "redirect:/itempedidoes/" + encodeUrlPathSegment(itemPedido.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String ItemPedidoController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, ItemPedido.findItemPedido(id));
        return "itempedidoes/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String ItemPedidoController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        ItemPedido itemPedido = ItemPedido.findItemPedido(id);
        itemPedido.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/itempedidoes";
    }
    
    void ItemPedidoController.populateEditForm(Model uiModel, ItemPedido itemPedido) {
        uiModel.addAttribute("itemPedido", itemPedido);
        uiModel.addAttribute("pedidoes", Pedido.findAllPedidoes());
        uiModel.addAttribute("produtoes", Produto.findAllProdutoes());
    }
    
    String ItemPedidoController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
