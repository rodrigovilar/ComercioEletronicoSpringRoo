package br.edu.ufcg.embedded.comercio;

import br.edu.ufcg.embedded.comercio.dominio.Cliente;
import br.edu.ufcg.embedded.comercio.dominio.Fornecedor;
import br.edu.ufcg.embedded.comercio.dominio.ItemPedido;
import br.edu.ufcg.embedded.comercio.dominio.Pedido;
import br.edu.ufcg.embedded.comercio.dominio.Produto;
import br.edu.ufcg.embedded.comercio.dominio.Vendedor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.roo.addon.web.mvc.controller.converter.RooConversionService;

@Configurable
/**
 * A central place to register application converters and formatters. 
 */
@RooConversionService
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		// Register application converters and formatters
	}


	public Converter<Cliente, String> getClienteToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<br.edu.ufcg.embedded.comercio.dominio.Cliente, java.lang.String>() {
            public String convert(Cliente cliente) {
                return new StringBuilder().append(cliente.getNome()).append(' ').append(cliente.getCpf()).append(' ').append(cliente.getNascimento()).append(' ').append(cliente.getCredito()).toString();
            }
        };
    }

	public Converter<Long, Cliente> getIdToClienteConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, br.edu.ufcg.embedded.comercio.dominio.Cliente>() {
            public br.edu.ufcg.embedded.comercio.dominio.Cliente convert(java.lang.Long id) {
                return Cliente.findCliente(id);
            }
        };
    }

	public Converter<String, Cliente> getStringToClienteConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, br.edu.ufcg.embedded.comercio.dominio.Cliente>() {
            public br.edu.ufcg.embedded.comercio.dominio.Cliente convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Cliente.class);
            }
        };
    }

	public Converter<Fornecedor, String> getFornecedorToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<br.edu.ufcg.embedded.comercio.dominio.Fornecedor, java.lang.String>() {
            public String convert(Fornecedor fornecedor) {
                return new StringBuilder().append(fornecedor.getNomeFantasia()).append(' ').append(fornecedor.getRazaoSocial()).append(' ').append(fornecedor.getCnpj()).append(' ').append(fornecedor.getEndereco()).toString();
            }
        };
    }

	public Converter<Long, Fornecedor> getIdToFornecedorConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, br.edu.ufcg.embedded.comercio.dominio.Fornecedor>() {
            public br.edu.ufcg.embedded.comercio.dominio.Fornecedor convert(java.lang.Long id) {
                return Fornecedor.findFornecedor(id);
            }
        };
    }

	public Converter<String, Fornecedor> getStringToFornecedorConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, br.edu.ufcg.embedded.comercio.dominio.Fornecedor>() {
            public br.edu.ufcg.embedded.comercio.dominio.Fornecedor convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Fornecedor.class);
            }
        };
    }

	public Converter<ItemPedido, String> getItemPedidoToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<br.edu.ufcg.embedded.comercio.dominio.ItemPedido, java.lang.String>() {
            public String convert(ItemPedido itemPedido) {
                return new StringBuilder().append(itemPedido.getQuantidade()).append(' ').append(itemPedido.getQuantidade()).toString();
            }
        };
    }

	public Converter<Long, ItemPedido> getIdToItemPedidoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, br.edu.ufcg.embedded.comercio.dominio.ItemPedido>() {
            public br.edu.ufcg.embedded.comercio.dominio.ItemPedido convert(java.lang.Long id) {
                return ItemPedido.findItemPedido(id);
            }
        };
    }

	public Converter<String, ItemPedido> getStringToItemPedidoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, br.edu.ufcg.embedded.comercio.dominio.ItemPedido>() {
            public br.edu.ufcg.embedded.comercio.dominio.ItemPedido convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), ItemPedido.class);
            }
        };
    }

	public Converter<Pedido, String> getPedidoToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<br.edu.ufcg.embedded.comercio.dominio.Pedido, java.lang.String>() {
            public String convert(Pedido pedido) {
                return new StringBuilder().append(pedido.getLancamento()).toString();
            }
        };
    }

	public Converter<Long, Pedido> getIdToPedidoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, br.edu.ufcg.embedded.comercio.dominio.Pedido>() {
            public br.edu.ufcg.embedded.comercio.dominio.Pedido convert(java.lang.Long id) {
                return Pedido.findPedido(id);
            }
        };
    }

	public Converter<String, Pedido> getStringToPedidoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, br.edu.ufcg.embedded.comercio.dominio.Pedido>() {
            public br.edu.ufcg.embedded.comercio.dominio.Pedido convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Pedido.class);
            }
        };
    }

	public Converter<Produto, String> getProdutoToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<br.edu.ufcg.embedded.comercio.dominio.Produto, java.lang.String>() {
            public String convert(Produto produto) {
                return new StringBuilder().append(produto.getNome()).append(' ').append(produto.getCodBarras()).append(' ').append(produto.getPreco()).toString();
            }
        };
    }

	public Converter<Long, Produto> getIdToProdutoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, br.edu.ufcg.embedded.comercio.dominio.Produto>() {
            public br.edu.ufcg.embedded.comercio.dominio.Produto convert(java.lang.Long id) {
                return Produto.findProduto(id);
            }
        };
    }

	public Converter<String, Produto> getStringToProdutoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, br.edu.ufcg.embedded.comercio.dominio.Produto>() {
            public br.edu.ufcg.embedded.comercio.dominio.Produto convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Produto.class);
            }
        };
    }

	public Converter<Vendedor, String> getVendedorToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<br.edu.ufcg.embedded.comercio.dominio.Vendedor, java.lang.String>() {
            public String convert(Vendedor vendedor) {
                return new StringBuilder().append(vendedor.getNome()).append(' ').append(vendedor.getCpf()).append(' ').append(vendedor.getNascimento()).append(' ').append(vendedor.getEmail()).toString();
            }
        };
    }

	public Converter<Long, Vendedor> getIdToVendedorConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, br.edu.ufcg.embedded.comercio.dominio.Vendedor>() {
            public br.edu.ufcg.embedded.comercio.dominio.Vendedor convert(java.lang.Long id) {
                return Vendedor.findVendedor(id);
            }
        };
    }

	public Converter<String, Vendedor> getStringToVendedorConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, br.edu.ufcg.embedded.comercio.dominio.Vendedor>() {
            public br.edu.ufcg.embedded.comercio.dominio.Vendedor convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Vendedor.class);
            }
        };
    }

	public void installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getClienteToStringConverter());
        registry.addConverter(getIdToClienteConverter());
        registry.addConverter(getStringToClienteConverter());
        registry.addConverter(getFornecedorToStringConverter());
        registry.addConverter(getIdToFornecedorConverter());
        registry.addConverter(getStringToFornecedorConverter());
        registry.addConverter(getItemPedidoToStringConverter());
        registry.addConverter(getIdToItemPedidoConverter());
        registry.addConverter(getStringToItemPedidoConverter());
        registry.addConverter(getPedidoToStringConverter());
        registry.addConverter(getIdToPedidoConverter());
        registry.addConverter(getStringToPedidoConverter());
        registry.addConverter(getProdutoToStringConverter());
        registry.addConverter(getIdToProdutoConverter());
        registry.addConverter(getStringToProdutoConverter());
        registry.addConverter(getVendedorToStringConverter());
        registry.addConverter(getIdToVendedorConverter());
        registry.addConverter(getStringToVendedorConverter());
    }

	public void afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
}
