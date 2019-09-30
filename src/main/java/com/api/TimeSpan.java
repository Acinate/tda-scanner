package com.api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeSpan {

    private void setPeriodFrequency(String period, String frequency) {
        Pattern p1 = Pattern.compile("\\d+");
        Pattern p2 = Pattern.compile("[a-z]");
        Matcher m1 = p1.matcher(period.toLowerCase());
        Matcher m2 = p2.matcher(period.toLowerCase());
        Matcher m3 = p1.matcher(frequency.toLowerCase());
        Matcher m4 = p2.matcher(frequency.toLowerCase());
        String period1 = "";
        String period2 = "";
        String frequency1 = "";
        String frequency2 = "";
        if (m1.find() && m3.find()) {
            period1 = m1.group();
            frequency1 = m3.group();
        }
        if (m2.find() && m4.find()) {
            period2 = m2.group();
            frequency2 = m4.group();
            if (period2.equals("d")) {
                if (period1.equals("1") || period1.equals("2") || period1.equals("3") || period1.equals("4")
                        || period1.equals("5") || period1.equals("10")) {
                    this.period = period1;
                    this.periodType = "day";
                }
                if (frequency2.equals("m")) {
                    if (frequency1.equals("1") || frequency1.equals("5") || frequency1.equals("10")
                            || frequency1.equals("15") || frequency1.equals("30")) {
                        this.frequency = frequency1;
                        this.frequencyType = "minute";
                    }
                }
            } else if (period2.equals("m")) {
                if (period1.equals("1") || period1.equals("2") || period1.equals("3") || period1.equals("6")) {
                    this.period = period1;
                    this.periodType = "month";
                }
                if (frequency2.equals("d") || frequency2.equals("w")) {
                    if (frequency1.equals("1")) {
                        this.frequency = frequency1;
                        if (frequency2.equals("d")) {
                            this.frequencyType = "daily";
                        } else {
                            this.frequencyType = "weekly";
                        }
                    }
                }
            } else if (period2.equals("y")) {
                if (period1.equals("1") || period1.equals("2") || period1.equals("3") || period1.equals("5")
                        || period1.equals("10") || period1.equals("15") || period1.equals("20")) {
                    this.period = period1;
                    this.periodType = "year";
                }
                if (frequency2.equals("d") || frequency2.equals("w") || frequency2.equals("m")) {
                    if (frequency1.equals("1")) {
                        this.frequency = frequency1;
                        if (frequency2.equals("d")) {
                            this.frequencyType = "daily";
                        } else if (frequency2.equals("w")) {
                            this.frequencyType = "weekly";
                        } else {
                            this.frequencyType = "monthly";
                        }
                    }
                }
            }
        }
        if (this.period == null || this.periodType == null || this.frequency == null || this.frequencyType == null) {
            this.period = null;
            this.periodType = null;
            this.frequency = null;
            this.frequencyType = null;
        }
    }

    public TimeSpan(String period, String frequency) {
        this.setPeriodFrequency(period,frequency);
    }

    public String periodType;
    public String period;
    public String frequencyType;
    public String frequency;
}