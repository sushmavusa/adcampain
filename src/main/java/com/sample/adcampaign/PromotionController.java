package com.sample.adcampaign;

import static com.sample.adcampaign.PromotionConstants.ADEXISTS;
import static com.sample.adcampaign.PromotionConstants.INVALIDDURATION;
import static com.sample.adcampaign.PromotionConstants.INVALIDREQUEST;
import static com.sample.adcampaign.PromotionConstants.ONE_MINUTE_IN_MILLIS;
import static com.sample.adcampaign.PromotionConstants.VALIDRESPONSE;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sample.model.PromotionModel;

@Controller
public class PromotionController {

	public static Map<Integer,List<PromotionModel>> promotionModels = new HashMap<Integer,List<PromotionModel>>();

	/**
	 * Method to create ad by checking the duration and mulitple ads for a partner
	 * @param promotionModel
	 * @return String
	 */

	@RequestMapping(method = RequestMethod.POST, value = "/ad")
	@ResponseBody
	public String createAd(@RequestBody PromotionModel promotionModel) {

		List<PromotionModel> promotionModelList = new ArrayList<PromotionModel>();
		if(promotionModel!=null){
			int partnerId=promotionModel.getPartnerId();

			//activate multiple add check
			if(promotionModels.containsKey(partnerId)){
				return new PromotionException().getMessage(ADEXISTS);
			}

			//deactivate multiple add check
			/*if(promotionModels.containsKey(partnerId)){
				promotionModelList=promotionModels.get(partnerId);
		     }*/
			long duration=promotionModel.getDuration();
			if(duration<=0){
				return new PromotionException().getMessage(INVALIDDURATION);
			}else{
				long currentDateInMillisec=new Date().getTime();
				promotionModel.setPromotionDate(new Date(currentDateInMillisec+(duration * ONE_MINUTE_IN_MILLIS)));
			}
			promotionModelList.add(promotionModel);
			promotionModels.put(promotionModel.getPartnerId(), promotionModelList);
			return VALIDRESPONSE;
		}else{
			return new PromotionException().getMessage(INVALIDREQUEST);
		}

	}

	@RequestMapping(method = RequestMethod.GET, value ="/ad/{partner_id}")
	@ResponseBody
	public List<PromotionModel> getAd(@PathVariable("partner_id") int partnerId){
		List<PromotionModel> finalPromotionModelList= null;
		if(!promotionModels.isEmpty()){
			List<PromotionModel> promotionModelList=promotionModels.get(partnerId);	
			PromotionHelper ph= new PromotionHelper();
			if(promotionModelList!=null){
				finalPromotionModelList=ph.filterAdCampaigns(promotionModelList);
			}
		}

		return finalPromotionModelList;
	}

	@RequestMapping(method = RequestMethod.GET, value ="/ads")
	@ResponseBody
	public Map<Integer,List<PromotionModel>> getAds(){
		List<PromotionModel> finalPromotionModelList= null;
		PromotionHelper ph= new PromotionHelper();
		Map<Integer,List<PromotionModel>> finalPromotionModelMap= new HashMap<Integer,List<PromotionModel>>();
		if(!promotionModels.isEmpty()){
			for(Map.Entry<Integer, List<PromotionModel>> entry: promotionModels.entrySet()){
				List<PromotionModel> promotionModelList=entry.getValue();	
				if(promotionModelList!=null){
					finalPromotionModelList=ph.filterAdCampaigns(promotionModelList);
				}
				finalPromotionModelMap.put(entry.getKey(), finalPromotionModelList);
			}
		}
		return finalPromotionModelMap;
	}
}
