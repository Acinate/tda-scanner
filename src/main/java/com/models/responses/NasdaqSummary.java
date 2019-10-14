package com.models.responses;

import kong.unirest.json.JSONObject;

public class NasdaqSummary {

    public NasdaqSummary(JSONObject data) {
        if (data.has("summaryData") && data.getClass() == JSONObject.class) {
            JSONObject summary = data.getJSONObject("summaryData");
            if (summary.has("Sector")) {
                this.sector = summary.getJSONObject("Sector").getString("value");
            }
            if (summary.has("Industry")) {
                this.industry = summary.getJSONObject("Industry").getString("value");
            }
        }
    }

    private String sector;
    private String industry;

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
