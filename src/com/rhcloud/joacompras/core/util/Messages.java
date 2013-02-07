/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rhcloud.joacompras.core.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author HWS
 */


public class Messages {
	
	public static final String	NUMBER_FORMAT_EXCEPTION = "Não foi possível converter (\0) para \1: não é um número inteiro.";
	public static final String EXCEPTION = "Não foi possível converter (\0) para \1: identificador desconhecido.";
	public static final String INSTANCE ="(\0) não é uma instância.";

	public void addInfo(String title, String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, title, msg));
	}

	public void addWarn(String title, String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, title, msg));
	}

	public void addError(String title, String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg));
	}

	public void addFatal(String title, String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, title, msg));
	}
	
	public void addInfo(String title) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, title, null));
	}

	public void addWarn(String title) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, title, null));
	}

	public void addError(String title) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, title, null));
	}

	public void addFatal(String title) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, title, null));
	}
}
			
