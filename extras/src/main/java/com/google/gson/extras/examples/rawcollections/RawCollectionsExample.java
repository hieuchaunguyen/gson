package com.google.gson.extras.examples.rawcollections;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

@SuppressWarnings({"PrivateConstructorForUtilityClass", "SystemOut"})
public class RawCollectionsExample {

  private static final Logger LOGGER =
      Logger.getLogger(RawCollectionsExample.class.getName());

  static class Event {
    private String name;
    private String source;

    private Event(String name, String source) {
      this.name = name;
      this.source = source;
    }

    @Override
    public String toString() {
      return String.format("(name=%s, source=%s)", name, source);
    }
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public static void main(String[] args) {
    Gson gson = new Gson();
    Collection collection = new ArrayList();
    collection.add("hello");
    collection.add(5);
    collection.add(new Event("GREETINGS", "guest"));

    String json = gson.toJson(collection);
    LOGGER.info("Using Gson.toJson() on a raw collection: " + json);

    JsonArray array = JsonParser.parseString(json).getAsJsonArray();
    String message = gson.fromJson(array.get(0), String.class);
    int number = gson.fromJson(array.get(1), int.class);
    Event event = gson.fromJson(array.get(2), Event.class);

    LOGGER.info(String.format("Using Gson.fromJson() to get: %s, %d, %s", message, number, event));
  }
}
