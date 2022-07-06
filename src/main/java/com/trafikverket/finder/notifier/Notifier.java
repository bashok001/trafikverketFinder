package com.trafikverket.finder.notifier;

public interface Notifier {

  void send(String destination, String message);
}
