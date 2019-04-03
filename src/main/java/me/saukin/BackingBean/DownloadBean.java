/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.saukin.BackingBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;


import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import me.saukin.entities.Clients;
import me.saukin.jpaControllers.ClientsJpaController;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author admin
 */
@Named
@RequestScoped
public class DownloadBean {
     
    @Inject
    private ClientsJpaController clientsJpaController;
    
    @Inject
    private LoginBean loginBean;
    
    
    
    public StreamedContent getDownloadValue() throws Exception {
        
        String userEmail = loginBean.getEmail();
        Clients client = clientsJpaController.findClientByEmail(userEmail);
        String book = client.getBook();
        
        StreamedContent download;
        File file = new File("c:\\Users\\admin\\Documents\\NetBeansProjects\\FinalLab\\src\\main\\webapp\\resources\\files\\" + book + ".zip");
        InputStream input = new FileInputStream(file);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        download = new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName());
        System.out.println("PREP = " + download.getName());
        return download;
    }
    
    
//    StreamedContent file;
//     
//    public DownloadBean() {        
//        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/images/sherlock.jpg");
//        file = new DefaultStreamedContent(stream, "image/jpg", "downloaded_boromir.jpg");
//    }
// 
//    public StreamedContent getFile() {
//        return file;
//    }
}