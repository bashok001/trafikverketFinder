### Configure the app

#### If you want to be notified by email
* Create App password on your Gmail account.
  * See how: https://support.google.com/accounts/answer/185833?hl=en 
  
* Open `src/main/resources/application.properties` file
  * Replace `YOUR_EMAIL_ADDRESS` with your gmail email address
  * Replate `YOUR_GMAIL_APP_PASSWORD` with the app password you created in Step 1


#### If you want to be notified by WhatsApp
* Follow the guide here to create a free Twilio account
  * https://www.twilio.com/docs/usage/tutorials/how-to-use-your-free-trial-account
* Verify your whatsapp number by following the link: https://support.twilio.com/hc/en-us/articles/360007721954-Getting-Started-with-Twilio-for-WhatsApp
* Open `src/main/java/com/trafikverket/finder/Constants.java` file
  * Replace `WHATSAPP_NUMBER` with your whatsapp number
  * Replace `WHATSAPP_FROM_NUMBER` with whatsapp number from your twilio sandbox
  * Replace `TWILIO_ACCOUNT_SID` with SID from your twilio account from your console
  * Replace `TWILIO_AUTH_TOKEN` with AUTH_TOKEN from your twilio console

#### Must fill out
* Open `src/main/java/com/trafikverket/finder/Constants.java` file
  * Replace `YOUR_PERSONAL_NUMBER` with your personal number
  * Fill in your `SEARCH_START_DATE`
  * Fill in your `SEARCH_END_DATE`

#### Optional Configuration
* Currently, app is scheduled to poll every 30 min. 
  * Change `frequency.of.poller` cron expression in `src/main/resources/application.properties`

### Run the app
* After the configuration: In the console, `./gradlew bootRun` and press enter

