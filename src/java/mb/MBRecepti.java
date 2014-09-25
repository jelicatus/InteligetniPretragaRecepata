/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mb;

import domen.Recept;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Collection;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.json.simple.parser.ParseException;
import persistence.DataModelManager;
import usluge.ReceptiUpiti;

/**
 *
 * @author TFamilija
 */
@ManagedBean
@ApplicationScoped
public class MBRecepti implements Serializable{
    
     Collection<Recept> recepti;
     
     String limit;
     String minRatingValue;
     String maxRatingValue; 
     String minCalories;
     String maxCalories;
     String mincarbohydrateContent;
     String maxcarbohydrateContent; 
     String minFatContent; 
     String maxFatContent;  
     String minProteinContent;
     String maxProteinContent;
     String minFiberContent; 
     String maxFiberContent;
     String maxCookTime; 
     String ingridients;
     
     ReceptiUpiti queryService= new ReceptiUpiti();

    /**
     * Creates a new instance of MBRecepti
     */
    public MBRecepti() {
    }

    public Collection<Recept> getRecepti() {
        return recepti;
    }

    public void setRecepti(Collection<Recept> recepti) {
        this.recepti = recepti;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getMinRatingValue() {
        return minRatingValue;
    }

    public void setMinRatingValue(String minRatingValue) {
        this.minRatingValue = minRatingValue;
    }

    public String getMaxRatingValue() {
        return maxRatingValue;
    }

    public void setMaxRatingValue(String maxRatingValue) {
        this.maxRatingValue = maxRatingValue;
    }

    public String getMinCalories() {
        return minCalories;
    }

    public void setMinCalories(String minCalories) {
        this.minCalories = minCalories;
    }

    public String getMaxCalories() {
        return maxCalories;
    }

    public void setMaxCalories(String maxCalories) {
        this.maxCalories = maxCalories;
    }

    public String getMincarbohydrateContent() {
        return mincarbohydrateContent;
    }

    public void setMincarbohydrateContent(String mincarbohydrateContent) {
        this.mincarbohydrateContent = mincarbohydrateContent;
    }

    public String getMaxcarbohydrateContent() {
        return maxcarbohydrateContent;
    }

    public void setMaxcarbohydrateContent(String maxcarbohydrateContent) {
        this.maxcarbohydrateContent = maxcarbohydrateContent;
    }

    public String getMinFatContent() {
        return minFatContent;
    }

    public void setMinFatContent(String minFatContent) {
        this.minFatContent = minFatContent;
    }

    public String getMaxFatContent() {
        return maxFatContent;
    }

    public void setMaxFatContent(String maxFatContent) {
        this.maxFatContent = maxFatContent;
    }

    public String getMinProteinContent() {
        return minProteinContent;
    }

    public void setMinProteinContent(String minProteinContent) {
        this.minProteinContent = minProteinContent;
    }

    public String getMaxProteinContent() {
        return maxProteinContent;
    }

    public void setMaxProteinContent(String maxProteinContent) {
        this.maxProteinContent = maxProteinContent;
    }

    public String getMinFiberContent() {
        return minFiberContent;
    }

    public void setMinFiberContent(String minFiberContent) {
        this.minFiberContent = minFiberContent;
    }

    public String getMaxFiberContent() {
        return maxFiberContent;
    }

    public void setMaxFiberContent(String maxFiberContent) {
        this.maxFiberContent = maxFiberContent;
    }

    public String getMaxCookTime() {
        return maxCookTime;
    }

    public void setMaxCookTime(String maxCookTime) {
        this.maxCookTime = maxCookTime;
    }

    public String getIngridients() {
        return ingridients;
    }

    public void setIngridients(String ingridients) {
        this.ingridients = ingridients;
    }
    

    
    public String getRecipesAdvancedSearch() throws IOException, MalformedURLException, ParseException, URISyntaxException{
       try{
        DataModelManager m=DataModelManager.getInstance();
        
     
        
        try {
        validujPodatke();
        recepti = queryService.getRecepies(limit, minRatingValue, maxRatingValue, minCalories, maxCalories, mincarbohydrateContent, maxcarbohydrateContent, minFatContent, maxFatContent, minProteinContent, maxProteinContent, minFiberContent, maxFiberContent, maxCookTime, ingridients);
        refreshujPolja();
        return "/prikazRecepata.xhtml?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            refreshujPolja();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "You didn't enter query parametars correctly. Try again.", ""));
            
        } 
       }catch (Exception e) {
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "The data was not retrieved from the local repository", ""));
            
        } 
        return "";
    }

    private void refreshujPolja() {
     limit="";
     minRatingValue="";
     maxRatingValue=""; 
     minCalories="";
     maxCalories="";
     mincarbohydrateContent="";
     maxcarbohydrateContent=""; 
     minFatContent=""; 
     maxFatContent="";  
     minProteinContent="";
     maxProteinContent="";
     minFiberContent=""; 
     maxFiberContent="";
     maxCookTime=""; 
     ingridients="";
    }

    private void validujPodatke() throws Exception{
       
        if(!limit.equals("")) Integer.parseInt(limit);
        if(!minRatingValue.equals("")) Integer.parseInt(minRatingValue);
        if(!maxRatingValue.equals("")) Integer.parseInt(maxRatingValue);
        if(!minCalories.equals("")) Integer.parseInt(minCalories);
        if(!maxCalories.equals("")) Integer.parseInt(maxCalories);
        if(!mincarbohydrateContent.equals("")) Double.parseDouble(mincarbohydrateContent);
        if(!maxcarbohydrateContent.equals("")) Double.parseDouble(maxcarbohydrateContent);
        if(!minFatContent.equals("")) Double.parseDouble(minFatContent);
        if(!maxFatContent.equals("")) Double.parseDouble(maxFatContent);
        if(!minFiberContent.equals("")) Double.parseDouble(minFiberContent);
        if(!maxFiberContent.equals("")) Double.parseDouble(maxFiberContent);
        if(!minProteinContent.equals("")) Double.parseDouble(minProteinContent);
        if(!maxProteinContent.equals("")) Double.parseDouble(maxProteinContent);            
      
    }
    
    
    
    
    
    
    
    
    
}
