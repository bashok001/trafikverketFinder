package com.trafikverket.finder;

public class Constants {

  public static String TRAFIKVERKET_BOOKING_URL = "https://fp.trafikverket.se";
  public static String EMAIL_SUBJECT = "New message from Trafikverket Poller";
  public static int LICENSE_ID = 5;
  public static int LOCATION_ID_SOLLENTUNA = 1000134;
  public static int LOCATION_ID_UPPSALA = 1000041;
  public static int LOCATION_ID_ESKILSTUNA = 1000005;
  public static int LOCATION_ID_ENKOPING = 1000072;
  public static int AUTOMATIC_VEHICLE_TYPE_ID = 4;
  public static int MANUAL_VEHICLE_TYPE_ID = 2;

  // =================================================================================================
  // MUST FILL IN THESE FOLLOWING
  public static String PNO = "YOUR_PERSONAL_NUMBER";

  // PLEASE MIND THE FORMATS OF DATES.
  // TODO: Make this format independent
  public static String SEARCH_START_DATE = "2022-07-26T22:00:00.000Z";
  public static String SEARCH_END_DATE = "2022-09-09";

  // =================================================================================================



  // =================================================================================================
  // EMAIL INTEGRATION. IGNORE IF NOT USING EMAIL NOTIFICATION METHOD
  public static String FROM_EMAIL_ADDRESS = "YOUR_FROM_EMAIL_ADDRESS";
  // =================================================================================================

  // =================================================================================================
  // WHATSAPP INTEGRATION WITH TWILIO. IGNORE IF NOT USING THIS NOTIFICATION METHOD
  public static String WHATSAPP_NUMBER = "YOUR_WHATSAPP_PHONE_NUMBER";
  public static String WHATSAPP_FROM_NUMBER = "YOUR_TWILIO_CLOUD_PHONE_NUMBER";
  public static String TWILIO_ACCOUNT_SID = "YOUR_TWILIO_ACCOUNT_SID";
  public static String TWILIO_AUTH_TOKEN = "YOUR_TWILIO_AUTH_TOKEN";
  // =================================================================================================

  private Constants() {
  }
}
