package com.sample.adcampaign;

import static com.sample.adcampaign.PromotionConstants.ADEXISTS;
import static com.sample.adcampaign.PromotionConstants.INVALIDDURATION;
import static com.sample.adcampaign.PromotionConstants.INVALIDREQUEST;
import static com.sample.adcampaign.PromotionConstants.VALIDRESPONSE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.sample.model.PromotionModel;

public class PromotionControllerTest {

	@Test
	public void testNullRequest() {
		PromotionController pc= new PromotionController();
		assertEquals(INVALIDREQUEST,pc.createAd(null));
	}

	@Test
	public void testValidRequest() {
		PromotionModel model= new PromotionModel();
		PromotionController pc1= new PromotionController();
		model.setPartnerId(1);
		model.setDuration(10);
		model.setAdContent("ad with time 10 min");
		assertEquals(VALIDRESPONSE,pc1.createAd(model));
		model.setPartnerId(1);
		model.setDuration(20);
		model.setAdContent("ad with time 20 min for same partner");
		assertEquals(ADEXISTS,pc1.createAd(model));
	}
	
	@Test
	public void testValidDurationRequest() {
		PromotionModel model= new PromotionModel();
		PromotionController pc2= new PromotionController();
		model.setPartnerId(1);
		model.setDuration(0);
		model.setAdContent("ad with time 0 min");
		assertEquals(INVALIDDURATION,pc2.createAd(model));
		model.setDuration(-10);
		model.setAdContent("ad with time -10 min");
		assertEquals(INVALIDDURATION,pc2.createAd(model));
	}
	
			
	@Test
	public void testRetrieveAd() {
		PromotionController pc3= new PromotionController();
		List<PromotionModel> promotionModels=pc3.getAd(1);
		assertEquals(20,promotionModels.get(0).getDuration());

	}
	
	@Test
	public void testRetrieveEmpty() {
		PromotionController pc4= new PromotionController();
		assertNull(pc4.getAd(2));
	}
	
	@Test
	public void testPatrnersAds() {
		PromotionController pc5= new PromotionController();
		Map<Integer,List<PromotionModel>> promotionModelsMap=pc5.getAds();
		assertEquals(20,promotionModelsMap.get(1).get(0).getDuration());
	}
}
