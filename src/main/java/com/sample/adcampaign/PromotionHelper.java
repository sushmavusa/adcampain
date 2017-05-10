package com.sample.adcampaign;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sample.model.PromotionModel;
import static com.sample.adcampaign.PromotionConstants.NOTDATAFound;
import static com.sample.adcampaign.PromotionConstants.ACTIVE;

public class PromotionHelper {

	public List<PromotionModel> filterAdCampaigns(List<PromotionModel> promotionModelList) {
		List<PromotionModel> finalPromotionModelList = new ArrayList<PromotionModel>();
		Date currentDate = new Date();
		for(PromotionModel pm: promotionModelList){
			Date promotionDate = pm.getPromotionDate();
		  if(currentDate.compareTo(promotionDate)<0){
			  pm.setStatus(ACTIVE);
			  finalPromotionModelList.add(pm);
		  }
		}
		
		if(finalPromotionModelList.isEmpty()){
			PromotionModel pm= new PromotionModel();
			pm.setPartnerId(promotionModelList.get(0).getPartnerId());
			pm.setStatus(NOTDATAFound);
			finalPromotionModelList.add(pm);
		}
		return finalPromotionModelList;
	}

}
