package com.sample.adcampaign;

import static com.sample.adcampaign.PromotionConstants.ONE_MINUTE_IN_MILLIS;
import static com.sample.adcampaign.PromotionConstants.NOTDATAFound;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import com.sample.model.PromotionModel;

public class PromotionHelperTest {

	static List<PromotionModel> promotionModelListPHTest= new ArrayList<PromotionModel>();

	public static void createPromotionModelList(){
		long currentDateInMillisec=new Date().getTime();
		PromotionModel model1= new PromotionModel();
		model1.setPartnerId(1);
		model1.setDuration(10);
		model1.setAdContent("ad with time 10 min");
		model1.setPromotionDate(new Date(currentDateInMillisec+(10 * ONE_MINUTE_IN_MILLIS)));
		promotionModelListPHTest.add(model1);

		PromotionModel model2= new PromotionModel();
		model2.setPartnerId(2);
		model2.setDuration(0);
		model2.setAdContent("ad with time 0 min");
		model2.setPromotionDate(new Date(currentDateInMillisec+(0 * ONE_MINUTE_IN_MILLIS)));
		promotionModelListPHTest.add(model2);

		PromotionModel model3= new PromotionModel();
		model3.setPartnerId(3);
		model3.setDuration(-10);
		model3.setAdContent("ad with time -10 min");
		model3.setPromotionDate(new Date(currentDateInMillisec+(-10 * ONE_MINUTE_IN_MILLIS)));
		promotionModelListPHTest.add(model3);

	}
	
	@Test
	public void promotionHelperTest() {
		PromotionHelper ph= new PromotionHelper();
		PromotionHelperTest.createPromotionModelList();
		List<PromotionModel> promotionmodels=ph.filterAdCampaigns(promotionModelListPHTest);
		assertEquals(promotionModelListPHTest.get(0).getAdContent(),promotionmodels.get(0).getAdContent());
	}
	
	@Test
	public void promotionHelperEmptyTest() {
		PromotionHelper ph= new PromotionHelper();
		PromotionHelperTest.createPromotionModelList();
		List<PromotionModel> promotionmodels= new ArrayList<PromotionModel>();
		promotionmodels.add(promotionModelListPHTest.get(1));
		promotionmodels.add(promotionModelListPHTest.get(2));
		List<PromotionModel> finalPromotionmodels=ph.filterAdCampaigns(promotionmodels);
		assertEquals(NOTDATAFound,finalPromotionmodels.get(0).getStatus());
	}

}
