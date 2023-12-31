package ma.nemo.assignment.domain.util;

import lombok.Getter;

public enum EventType {

  SUPPLY("Supply"),
  RETURN("Return"),
  SALE("sale");

  @Getter
  private final String type;

  EventType(String type) {
    this.type = type;
  }

}
