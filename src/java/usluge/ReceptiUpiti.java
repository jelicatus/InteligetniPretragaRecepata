package usluge;

import java.util.ArrayList;
import java.util.Collection;

import util.Constants;


import domen.Recept;
import java.io.Serializable;
import persistence.DataModelManager;
import usluge.IzvrsilacUpita;

public class ReceptiUpiti implements Serializable{
	
	private IzvrsilacUpita queryExecutor = new IzvrsilacUpita();
	
     public Collection<Recept> getRecepies(String limit,
			String minRatingValue, String maxRatingValue, String minCalories, String maxCalories, 
                        String mincarbohydrateContent, String maxcarbohydrateContent,
			String minFatContent, String maxFatContent,  String minProteinContent,String  maxProteinContent,
			String minFiberContent, String maxFiberContent, String maxCookTime, String ingridients) {
		
         
            System.out.println(maxCookTime);
            String prefix = "PREFIX schema: <" + Constants.SCHEMA + "> "
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
				+ "SELECT ?recipe ";
		String where = " ?recipe a schema:Recipe . ";
		String constraint = "";
                
                	if (maxCookTime!= null && !maxCookTime.equals("")) {
			where += "?recipe schema:totalTime ?totalTime. ";
			
				where += "FILTER ( ";
				if (maxCookTime.equals("less30")) {
					for (int i = 0; i <= 30; i++) {
						if (i < 30)
							where += "regex( ?totalTime, \"PT" + i + "M\" ) || ";
						else if (i == 30)
							where += "regex( ?totalTime, \"PT" + i + "M\" ) ) ";
					}
				}				
				if (maxCookTime.equals("less60")) {
					for (int i = 0; i <= 60; i++) {
						if (i < 59)
							where += "regex( ?totalTime, \"PT" + i + "M\" ) || ";
				
                                                else if (i == 59)
						where += "regex( ?totalTime, \"PT" + i + "M\" ) )";
					}
				}
				if (maxCookTime.equals("less120")) {
					for (int i = 0; i <= 120; i++) {
						if (i < 60)
							where += "regex( ?totalTime, \"PT" + i + "M\" ) || ";
						else if (i == 60)
							where += "regex( ?totalTime, \"PT1H\" ) || ";
						else if (i > 60 && i < 119)
							where += "regex( ?totalTime, \"PT1H" + (i - 60) + "M\" ) || ";
					
                                                else if (i == 119)
							where += "regex( ?totalTime, \"PT1H" + (i - 60) + "M\" ) )";
					}
				}				
			
		}
		if (ingridients!= null && !ingridients.equals("")) {
			String[] ingredients = ingridients.split(",");
			for (String string : ingredients) {
				string = string.trim();
				where += "?recipe schema:ingredients ?" + string + ". "
				+"FILTER (regex(STR(?" + string + "), \"" + string + "\", \"i\" )) ";
			}
		}


		if (minRatingValue!= null && !minRatingValue.equals("")) {
			where += "?recipe schema:aggregateRating ?aggRating. "
					+ "?aggRating schema:ratingValue ?ratingValue. "
					+ "FILTER (?ratingValue > " + minRatingValue + ") ";
		}
		if (maxRatingValue!= null && !maxRatingValue.equals("")) {
			where += "?recipe schema:aggregateRating ?aggRating. "
					+ "?aggRating schema:ratingValue ?ratingValue."
					+ "FILTER (?ratingValue < " + maxRatingValue + ") ";
		}
		if (minCalories!= null && !minCalories.equals("")) {
			where += "?recipe schema:nutrition ?nutrition. "
					+"?nutrition schema:calories ?calories. "
					+ "FILTER (?calories > " + minCalories + ") ";
		}
if (maxCalories!= null && !maxCalories.equals("")) {
			where += "?recipe schema:nutrition ?nutrition. "
					+"?nutrition schema:calories ?calories. "
					+ "FILTER (?calories < " + maxCalories + ") ";
		}
if (mincarbohydrateContent!= null && !mincarbohydrateContent.equals("")) {
			where += "?recipe schema:nutrition ?nutrition. "
					+"?nutrition schema:carbohydrateContent ?carbs. "
					+ "FILTER (?carbs > " + mincarbohydrateContent + ") ";
		}
if (maxcarbohydrateContent!= null && !maxcarbohydrateContent.equals("")) {
			where += "?recipe schema:nutrition ?nutrition. "
					+"?nutrition schema:carbohydrateContent ?carbs. "
					+ "FILTER (?carbs < " + maxcarbohydrateContent + ") ";
		}
if (minFatContent!= null && !minFatContent.equals("")) {
			where += "?recipe schema:nutrition ?nutrition. "
					+"?nutrition schema:fatContent ?fat. "
					+ "FILTER (?fat > " + minFatContent + ") ";
		}
if (maxFatContent!= null && !maxFatContent.equals("")) {
			where += "?recipe schema:nutrition ?nutrition. "
					+"?nutrition schema:fatContent ?fat. "
					+ "FILTER (?fat < " + maxFatContent + ") ";
		}
if (minProteinContent!= null && !minProteinContent.equals("")) {
			where += "?recipe schema:nutrition ?nutrition. "
					+"?nutrition schema:proteinContent ?protein. "
					+ "FILTER (?protein > " + minProteinContent + ") ";
		}
if (maxProteinContent!= null && !maxProteinContent.equals("")) {
			where += "?recipe schema:nutrition ?nutrition. "
					+"?nutrition schema:proteinContent ?protein. "
					+ "FILTER (?protein < " + maxProteinContent + ") ";
		}
if (minFiberContent!= null && !minFiberContent.equals("")) {
			where += "?recipe schema:nutrition ?nutrition. "
					+"?nutrition schema:fiberContent ?fiber. "
					+ "FILTER (?fiber > " + minFiberContent + ") ";
		}
if (maxFiberContent!= null && !maxFiberContent.equals("")) {
			where += "?recipe schema:nutrition ?nutrition. "
					+"?nutrition schema:fiberContent ?fiber. "
					+ "FILTER (?fiber < " + maxFiberContent + ") ";
		}

		if (limit!= null && !limit.equals("")) {
			constraint += "LIMIT " + limit;
		}
		
		String query = prefix + " WHERE {" + where + "} " + constraint;
                System.out.println(query);
		Collection<String> result = queryExecutor
				.executeOneVariableSelectSparqlQuery(query, "recipe",
						DataModelManager.getInstance().getModel());
		Collection<Recept> recepti = new ArrayList<Recept>();

		if (result != null && !result.isEmpty()) {
			for (String s : result) {
				Recept r = (Recept) DataModelManager.getInstance().load(s);
				recepti.add(r);
			}

			return recepti;
		}

		return null;
	}


        
}

