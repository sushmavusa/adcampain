This is a spring boot application. Just run the application as a java application. Run App.java.
Use advanced rest client or post man to test the following urls.

1.  To add the ad campain
post method: 
http://localhost:8080/ad/

headers:
Content-Type:application/json

request body
{
 "partnerId": <enter a unique integer value>,
 "duration": <enter number of minutes>,
 "adContent": <place any text>
}

2. To get the ad campain using partnerId
get method:
http://localhost:8080/ad/{partnerId}

headers:
Content-Type:application/json

3. To get the list of ad campain 
get method:
http://localhost:8080/ads

headers:
Content-Type:application/json



Note: To activate and deactivate multiple ads for same partner, please check the comments on the src/main/java/com/sample/adcampaign/PromotionController.java
