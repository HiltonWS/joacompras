package com.rhcloud.joacompras.view.backbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.apache.myfaces.custom.fileupload.UploadedFile;

import com.rhcloud.joacompras.core.bean.ItemBean;
import com.rhcloud.joacompras.core.dao.ItemDAO;

@ManagedBean
@RequestScoped
public class ItemBack implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ItemBean bean;
	private List<ItemBean> lbean;
	private UploadedFile uploaded;
	private boolean selecionar;

	public ItemBack() {
		limpar();
	}

	public void salvar() {
		if (selecionar) {
			new ItemDAO().update(getBean());
		} else {
			new ItemDAO().insert(getBean());
		}
		limpar();
	}

	public boolean isSelecionar() {
		return selecionar;
	}

	public void setSelecionar(boolean selecionar) {
		this.selecionar = selecionar;
	}

	public void excluir(ItemBean b) {
		lbean.remove(lbean.indexOf(b));
		new ItemDAO().delete(b);
		limpar();
		// Forcar renderizacao da view atualizacao dos valores
		FacesContext context = FacesContext.getCurrentInstance();
		Application application = context.getApplication();
		ViewHandler viewHandler = application.getViewHandler();
		UIViewRoot viewRoot = viewHandler.createView(context, context
				.getViewRoot().getViewId());
		context.setViewRoot(viewRoot);
		context.renderResponse();

	}

	public void selecionar(ItemBean b) {
		selecionar = true;
		bean = b;
		// Forcar renderizacao da view atualizacao dos valores
		FacesContext context = FacesContext.getCurrentInstance();
		Application application = context.getApplication();
		ViewHandler viewHandler = application.getViewHandler();
		UIViewRoot viewRoot = viewHandler.createView(context, context
				.getViewRoot().getViewId());
		context.setViewRoot(viewRoot);
		context.renderResponse();
	}

	public void limpar() {
		setLbean(new ArrayList<ItemBean>());
		lbean = new ItemDAO().listaTodos(ItemBean.class);
		setBean(new ItemBean());
		selecionar = false;
	}

	public ItemBean getBean() {
		return bean;
	}

	public void setBean(ItemBean bean) {
		this.bean = bean;
	}

	public List<ItemBean> getLbean() {
		lbean = new ItemDAO().listaTodos(ItemBean.class);
		return lbean;
	}

	public void setLbean(List<ItemBean> lbean) {
		this.lbean = lbean;
	}

	public UploadedFile getUploaded() {
		return uploaded;
	}

	public void setUploaded(UploadedFile uploaded) {
		if (uploaded != null) {
			try {
				bean.setImagem(uploaded.getBytes());

			} catch (IOException e) {

			}

			this.uploaded = uploaded;
		}
	}

}
