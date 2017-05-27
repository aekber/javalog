package org.ekber.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.richfaces.component.UIPopupPanel;
import org.richfaces.model.UploadedFile;

public class FacesSupport {


    private String bundleBaseName = "example.i18n";
	
    
  //****************Aktivasyon key girildiginde otomatik login ekrani geliyor********************
	
//	public static final void showWhenRendered(FacesContext jsf, String modalPanelId, boolean showWhenRendered) {
//        UIViewRoot viewRoot = jsf.getViewRoot();
//        if (viewRoot != null) {
//            Object modalPanel = viewRoot.findComponent(modalPanelId);
//            if (modalPanel != null) {
//                ((UIPopupPanel)modalPanel).setShowWhenRendered(showWhenRendered);
//            }
//        }
//    }

    
  //***************************Kullanici Resim Yukleme******************************************
    
    public static final byte[] getImageBytes(UploadedFile item) {
        byte[] aby = null;
        if (item != null) {
            aby = item.getData();
        }
        return (aby != null && aby.length > 0) ? aby : null;
    }

    public static final byte[] toBytes(File file) throws IOException {
        byte[] aby = null;
        if (file.isFile()) {
            aby = toBytes(new FileInputStream(file));
        }
        return (aby != null) ? aby : new byte[0];
    }

    public static final byte[] toBytes(InputStream input) throws IOException {
        byte[] aby = new byte[512];
        int r = -1;
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        try {
            while ((r = input.read(aby)) != -1) bs.write(aby, 0, r);
            return bs.toByteArray();
        } finally {
            try {
                input.close();
            } catch (Exception ign) {}
        }
    }

  //*************************************************************************************************
    

    public void addInfo(String controlId, String messageKey, Object... args) {
        addMessage(controlId, FacesMessage.SEVERITY_INFO, messageKey, args);
    }

    public void addError(String controlId, String messageKey, Object... args) {
        addMessage(controlId, FacesMessage.SEVERITY_ERROR, messageKey, args);
    }

    public void addMessage(String controlId, javax.faces.application.FacesMessage.Severity sev, String messageKey, Object... args) {
        FacesContext jsf = FacesContext.getCurrentInstance();
        ExternalContext extCtxt = jsf.getExternalContext();
        jsf.addMessage(controlId, new FacesMessage(sev, formatMessage(messageKey, extCtxt.getRequestLocale(), args), null));
    }

    public String formatMessage(String messageKey, Locale locale, Object... args) {
        return MessageFormat.format(getMessage(messageKey, locale), args);
    }

    public String getMessage(String messageKey, Locale locale) {
        ResourceBundle i18n = getBundle(locale);
        try {
            return i18n.getString(messageKey);
        } catch (java.util.MissingResourceException mre) {
            return "???"+messageKey+"???";
        }
    }

    public ResourceBundle getBundle(Locale locale) {
        return ResourceBundle.getBundle(bundleBaseName, locale);
    }    


}