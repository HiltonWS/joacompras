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
	private String style;
	private transient HtmlDataTable dataTable;
	private transient HtmlDataTable dataTableAd;

	public ListaBack() {
		limpar();
	}

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
			if (selecionar)
				new ListaItemDAO().update(lib);
			else
				new ListaItemDAO().insert(lib);

		}
		limpar();

	}

	public void excluir(ListaBean b) {
		new ListaDAO().delete(b);

		limpar();
	}

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

	public void remover(ItemBean b) {
		b.setTempQtd(b.getTempQtd() - 1);
		if (b.getTempQtd() <= 0) {
			litemAd.remove(b);
			remover.add(b);
		}
	}

	public void listaItens() {
		setRender(false);
		litemAd = new ListaItemDAO().buscarItens((ListaBean) dataTable
				.getRowData());
	}

	public void selecionar(ListaBean b) {
		setSelecionar(true);
		bean = b;
		litemAd = new ListaItemDAO().buscarItens(bean);
	}

	public ListaBean getBean() {
		return bean;
	}

	public void setBean(ListaBean bean) {
		this.bean = bean;
	}

	public List<ListaBean> getLbean() {

		return lbean;
	}

	public void setLbean(List<ListaBean> lbean) {
		this.lbean = lbean;
	}

	public List<ItemBean> getLitem() {
		return litem;
	}

	public void setLitem(List<ItemBean> litem) {

		this.litem = litem;

	}

	public void comprou() {
		ItemBean b = (ItemBean) dataTableAd.getRowData();
		if (litemAd.contains(b)) {
//			b.setTempQtd(b.getTempQtd() - 1);
//			if (b.getTempQtd() == 0) {
				litemAd.remove(b);
//			}
		}
	}

	public double getTotalAd() {
		double d = 0;
		for (ItemBean b : litemAd) {
			d += (b.getTempQtd() * b.getValor());

		}
		return d;
	}

	
	public HtmlDataTable getDataTable() {
		return dataTable;
	}

	public void setDataTable(HtmlDataTable dataTable) {
		this.dataTable = dataTable;
	}

	public List<ItemBean> getLitemAd() {
		return litemAd;
	}

	public void setLitemAd(List<ItemBean> litemAd) {
		this.litemAd = litemAd;
	}

	public HtmlDataTable getDataTableAd() {
		return dataTableAd;
	}

	public void setDataTableAd(HtmlDataTable dataTableAd) {
		this.dataTableAd = dataTableAd;
	}

	public boolean isRender() {
		return render;
	}

	public void setRender(boolean render) {
		this.render = render;
	}

	public boolean isSelecionar() {
		return selecionar;
	}

	public void setSelecionar(boolean selecionar) {
		this.selecionar = selecionar;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

}
