package com.emrekp.haciko.dto;

public enum ExpireInterval {
  ONE_HOUR("1 saat", 3600),
  THREE_HOURS("3 saat", 3600 * 3),
  SIX_HOURS("6 saat", 3600 * 6),
  ONE_DAY("1 gün", 3600 * 24),
  NEVER("Bitirme", -1);

  public final String text;
  public final int seconds;

  ExpireInterval(String text, int seconds) {
    this.text = text;
    this.seconds = seconds;
  }
}
