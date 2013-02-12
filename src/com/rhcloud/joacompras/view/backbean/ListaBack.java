package com.rhcloud.joacompras.view.backbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;

import com.rhcloud.joacompras.core.bean.ItemBean;
import com.rhcloud.joacompras.core.bean.ListaBean;
import com.rhcloud.joacompras.core.bean.ListaItemBean;
import com.rhcloud.joacompras.core.dao.ListaDAO;
import com.rhcloud.joacompras.core.dao.ListaItemDAO;

@ManagedBean
@ViewScoped
public class ListaBack implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean render;
	private ListaBean bean;
	private List<ListaBean> lbean;
	private List<ItemBean> litem;
	private List<ItemBean> litemAd;
	private List<ItemBean> remover;
	private boolean selecionar;
	private transient HtmlDataTable dataTable;
	private transient HtmlDataTable dataTableAd;

	public ListaBack() {
		limpar();
	}

	/**
	 * Salva o bean
	 */
	public void salvar() {
		ListaDAO dao = new ListaDAO();
		if (selecionar)
			dao.update(getBean());
		else
			dao.insert(getBean());
		for (ItemBean b : remover) {
			ListaItemBean lib = new ListaItemBean();
			lib.setItem(b);
			lib.setLista(getBean());
			lib.setQuantidade(b.getTempQtd());
			new ListaItemDAO().delete(lib);
		}
		for (ItemBean b : litemAd) {
			ListaItemBean lib = new ListaItemBean();
			lib.setItem(b);
			lib.setLista(getBean());
			lib.setQuantidade(b.getTempQtd());
			if (selecionar && new ListaItemDAO().buscarItens(getBean()).size() >0)
				new ListaItemDAO().update(lib);
			else
				new ListaItemDAO().insert(false, lib);

		}
		limpar();

	}

	/**
	 * Excluir a lista
	 * @param b lista recebida pela pagina
	 */
	public void excluir(ListaBean b) {
		
		for(ItemBean bi: new ListaItemDAO().buscarItens(b)){
			ListaItemBean be = new ListaItemBean();
			be.setItem(bi);
			be.setLista(b);
			new ListaItemDAO().delete(be);
		}
		new ListaDAO().delete(b);
        
		limpar();
	}

	/**
	 * Limpa os valores
	 */
	public void limpar() {
		setSelecionar(false);
		remover = new ArrayList<ItemBean>();
		setRender(true);
		setBean(new ListaBean());
		setLbean(new ArrayList<ListaBean>());
		lbean = new ListaDAO().listaTodos(ListaBean.class);
		if(lbean.isEmpty())
			setRender(false);
		litem = new ItemBack().getLbean();
		setLitemAd(new ArrayList<ItemBean>());
		
	}

	/**
	 * Adiciona Quantidade/item a lista ltemAD
	 * @param b Item a ser adicionado da pagina
	 */
	public void adicionar(ItemBean b) {
		if (!litemAd.contains(b)) {
			b.setTempQtd(1);
			litemAd.add(b);
		} else {
			b = litemAd.get(litemAd.indexOf(b));
			b.setTempQtd(1 + b.getTempQtd());
			litemAd.remove(b);
			litemAd.add(b);
		}

	}

	/**
	 * Remove a quantidade do item ate ser 0
	 * @param b Item a ser removido da pagina
	 */
	public void remover(ItemBean b) {
		b.setTempQtd(b.getTempQtd() - 1);
		if (b.getTempQtd() <= 0) {
			litemAd.remove(b);
			remover.add(b);
		}
	}

	/**
	 * Lista os itens buscando pela lista selecionada
	 */
	public void listaItens() {
		setRender(false);
		litemAd = new ListaItemDAO().buscarItens((ListaBean) dataTable
				.getRowData());
	}

	/**
	 * Selecionar/Alterar lista
	 * @param b Lista selecionada da pagina
	 */
	public void selecionar(ListaBean b) {
		setSelecionar(true);
		bean = b;
		litemAd = new ListaItemDAO().buscarItens(bean);
	}

	/**
	 * @return
	 */
	public ListaBean getBean() {
		return bean;
	}

	/**
	 * @param bean
	 */
	public void setBean(ListaBean bean) {
		this.bean = bean;
	}

	/**
	 * @return
	 */
	public List<ListaBean> getLbean() {
		return lbean;
	}

	/**
	 * @param lbean
	 */
	public void setLbean(List<ListaBean> lbean) {
		this.lbean = lbean;
	}

	/**
	 * @return
	 */
	public List<ItemBean> getLitem() {
		return litem;
	}

	/**
	 * @param litem
	 */
	public void setLitem(List<ItemBean> litem) {
		this.litem = litem;
	}

	/**
	 * Se comprou exclui o item da lista
	 */
	public void comprou() {
		ItemBean b = (ItemBean) dataTableAd.getRowData();
		if (litemAd.contains(b)) {
//			b.setTempQtd(b.getTempQtd() - 1);
//			if (b.getTempQtd() == 0) {
				litemAd.remove(b);
//			}
		}
	}

	/**
	 * @return o valor total de cada item na lista
	 */
	public double getTotalAd() {
		double d = 0;
		for (ItemBean b : litemAd) {
			d += (b.getTempQtd() * b.getValor());

		}
		return d;
	}

	
	/**
	 * @return
	 */
	public HtmlDataTable getDataTable() {
		return dataTable;
	}

	/**
	 * @param dataTable
	 */
	public void setDataTable(HtmlDataTable dataTable) {
		this.dataTable = dataTable;
	}

	/**
	 * @return
	 */
	public List<ItemBean> getLitemAd() {
		return litemAd;
	}

	/**
	 * @param litemAd
	 */
	public void setLitemAd(List<ItemBean> litemAd) {
		this.litemAd = litemAd;
	}

	/**
	 * @return
	 */
	public HtmlDataTable getDataTableAd() {
		return dataTableAd;
	}

	/**
	 * @param dataTableAd
	 */
	public void setDataTableAd(HtmlDataTable dataTableAd) {
		this.dataTableAd = dataTableAd;
	}

	/**
	 * @return
	 */
	public boolean isRender() {
		return render;
	}

	/**
	 * Renderizar lista ou item
	 * @param render
	 */
	public void setRender(boolean render) {
		this.render = render;
	}

	/**
	 * @return
	 */
	public boolean isSelecionar() {
		return selecionar;
	}

	/**
	 * @param selecionar
	 */
	public void setSelecionar(boolean selecionar) {
		this.selecionar = selecionar;
	}


}
