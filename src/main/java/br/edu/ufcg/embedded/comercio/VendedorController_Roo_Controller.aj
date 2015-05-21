// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.edu.ufcg.embedded.comercio;

import br.edu.ufcg.embedded.comercio.VendedorController;
import br.edu.ufcg.embedded.comercio.dominio.Pedido;
import br.edu.ufcg.embedded.comercio.dominio.Vendedor;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect VendedorController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String VendedorController.create(@Valid Vendedor vendedor, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, vendedor);
            return "vendedors/create";
        }
        uiModel.asMap().clear();
        vendedor.persist();
        return "redirect:/vendedors/" + encodeUrlPathSegment(vendedor.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String VendedorController.createForm(Model uiModel) {
        populateEditForm(uiModel, new Vendedor());
        return "vendedors/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String VendedorController.show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("vendedor", Vendedor.findVendedor(id));
        uiModel.addAttribute("itemId", id);
        return "vendedors/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String VendedorController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("vendedors", Vendedor.findVendedorEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Vendedor.countVendedors() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("vendedors", Vendedor.findAllVendedors(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "vendedors/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String VendedorController.update(@Valid Vendedor vendedor, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, vendedor);
            return "vendedors/update";
        }
        uiModel.asMap().clear();
        vendedor.merge();
        return "redirect:/vendedors/" + encodeUrlPathSegment(vendedor.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String VendedorController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Vendedor.findVendedor(id));
        return "vendedors/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String VendedorController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Vendedor vendedor = Vendedor.findVendedor(id);
        vendedor.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/vendedors";
    }
    
    void VendedorController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("vendedor_nascimento_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    void VendedorController.populateEditForm(Model uiModel, Vendedor vendedor) {
        uiModel.addAttribute("vendedor", vendedor);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("pedidoes", Pedido.findAllPedidoes());
    }
    
    String VendedorController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
