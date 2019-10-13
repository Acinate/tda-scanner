package com.models;

import kong.unirest.json.JSONObject;

/**
 * This Object maps the response received from Nasdaq's Stock Screener Endpoint.
 * NOTE: This object is only used for parsing the response.
 * URL: https://www.nasdaq.com/api/v1/screener?page=1&pageSize=50
 */
public class NasdaqSymbol {
    public NasdaqSymbol(JSONObject symbol) {
        /* Parse General Stock Information */
        this.ticker = symbol.getString("ticker");
        this.company = symbol.getString("company");
        this.marketCap = symbol.getLong("marketCap");
        this.marketCapGroup = symbol.getString("marketCapGroup");
        this.sectorName = symbol.getString("sectorName");
        this.sector = symbol.getString("sector");
        /* Parse Dividend Data Object */
        if (symbol.get("dividendData") != null) {
            JSONObject dividendData = symbol.getJSONObject("dividendData");
            if (dividendData.get("dividendYield") != null) {
                this.dividendYield = dividendData.getDouble("dividendYield");
            }
            if (dividendData.get("dividend") != null) {
                this.dividend = dividendData.getDouble("dividend");
            }
        }
        /* Parse Analyst Consensus */
        if (symbol.get("analystConsensusLabel") != null) {
            this.analystConsensusLabel = symbol.getString("analystConsensusLabel");
        }
        if (symbol.get("analystConsensus") != null) {
            this.analystConsensus = symbol.getString("analystConsensus");
        }
        /* Parse Price Target Data Object */
        if (symbol.get("priceTargetData") != null) {
            JSONObject priceTargetData = symbol.getJSONObject("priceTargetData");
            if (priceTargetData.get("analystPriceTarget") != null) {
                this.analystPriceTarget = priceTargetData.getDouble("analystPriceTarget");
            }
            if (priceTargetData.get("upside") != null) {
                this.analystUpside = priceTargetData.getDouble("upside");
            }
            if (priceTargetData.get("upsideGroup") != null) {
                this.analystUpsideGroup = priceTargetData.getDouble("upsideGroup");
            }
        }
        /* Parse Best Analyst Consensus */
        if (symbol.get("bestAnalystConsensusLabel") != null) {
            this.bestAnalystConsensusLabel = symbol.getString("bestAnalystConsensusLabel");
        }
        if (symbol.get("bestAnalystConsensus") != null) {
            this.bestAnalystConsensus = symbol.getString("bestAnalystConsensus");
        }
        /* Parse Best Price Target Data Object */
        if (symbol.get("bestPriceTargetData") != null) {
            JSONObject bestPriceTargetData = symbol.getJSONObject("bestPriceTargetData");
            if (bestPriceTargetData.get("analystPriceTarget") != null) {
                this.bestAnalystPriceTarget = bestPriceTargetData.getDouble("analystPriceTarget");
            }
            if (bestPriceTargetData.get("upside") != null) {
                this.bestAnalystUpside = bestPriceTargetData.getDouble("upside");
            }
            if (bestPriceTargetData.get("upsideGroup") != null) {
                this.bestAnalystUpsideGroup = bestPriceTargetData.getDouble("upsideGroup");
            }
        }
        /* Parse News Sentiment Data */
        if (symbol.get("newsSentimentData") != null) {
            JSONObject newsSentimentData = symbol.getJSONObject("newsSentimentData");
            if (newsSentimentData.get("signal") != null) {
                this.newsSentimentSignal = newsSentimentData.getString("signal");
            }
            if (newsSentimentData.get("label") != null) {
                this.newsSentimentLabel = newsSentimentData.getString("label");
            }
            if (newsSentimentData.get("score") != null) {
                this.newsSentimentScore = newsSentimentData.getDouble("score");
            }
        }
        /* Parse Insider Sentiment Data */
        if (symbol.get("insiderSentimentData") != null) {
            JSONObject insiderSentimentData = symbol.getJSONObject("insiderSentimentData");
            if (insiderSentimentData.get("signal") != null) {
                this.insiderSentimentSignal = insiderSentimentData.getString("signal");
            }
            if (insiderSentimentData.get("label") != null) {
                this.insiderSentimentLabel = insiderSentimentData.getString("label");
            }
            if (insiderSentimentData.get("score") != null) {
                this.insiderSentimentScore = insiderSentimentData.getDouble("score");
            }
        }
        /* Parse Media Buzz Data */
        if (symbol.get("mediaBuzzData") != null) {
            JSONObject mediaBuzzData = symbol.getJSONObject("mediaBuzzData");
            if (mediaBuzzData.get("signal") != null) {
                this.mediaBuzzSignal = mediaBuzzData.getString("signal");
            }
            if (mediaBuzzData.get("label") != null) {
                this.mediaBuzzLabel = mediaBuzzData.getString("label");
            }
            if (mediaBuzzData.get("score") != null) {
                this.mediaBuzzScore = mediaBuzzData.getDouble("score");
            }
        }
        /* Parse Hedge Fund Sentiment Data */
        if (symbol.get("hedgeFundSentimentData") != null) {
            JSONObject hedgeFundSentimentData = symbol.getJSONObject("hedgeFundSentimentData");
            if (hedgeFundSentimentData.get("signal") != null) {
                this.hedgeFundSentimentSignal = hedgeFundSentimentData.getString("signal");
            }
            if (hedgeFundSentimentData.get("label") != null) {
                this.hedgeFundSentimentLabel = hedgeFundSentimentData.getString("label");
            }
            if (hedgeFundSentimentData.get("score") != null) {
                this.hedgeFundSentimentScore = hedgeFundSentimentData.getDouble("score");
            }
        }
        /* Parse Investor Sentiment Data */
        if (symbol.get("investorSentimentData") != null) {
            JSONObject investorSentimentData = symbol.getJSONObject("investorSentimentData");
            if (investorSentimentData.get("signal") != null) {
                this.investorSentimentSignal = investorSentimentData.getString("signal");
            }
            if (investorSentimentData.get("label") != null) {
                this.investorSentimentLabel = investorSentimentData.getString("label");
            }
            if (investorSentimentData.get("score") != null) {
                this.investorSentimentScore = investorSentimentData.getDouble("score");
            }
        }
        /* Parse Blogger Sentiment Data */
        if (symbol.get("bloggerSentimentData") != null) {
            JSONObject bloggerSentimentData = symbol.getJSONObject("bloggerSentimentData");
            if (bloggerSentimentData.get("signal") != null) {
                this.bloggerSentimentSignal = bloggerSentimentData.getString("signal");
            }
            if (bloggerSentimentData.get("label") != null) {
                this.bloggerSentimentLabel = bloggerSentimentData.getString("label");
            }
            if (bloggerSentimentData.get("bearishCount") != null) {
                this.bloggerSentimentBearishCount = bloggerSentimentData.getInt("bearishCount");
            }
            if (bloggerSentimentData.get("bullishCount") != null) {
                this.bloggerSentimentBullishCount = bloggerSentimentData.getInt("bullishCount");
            }
        }
    }

    private String ticker;
    private String company;
    private long marketCap;
    private String marketCapGroup;
    private String sectorName;
    private String sector;
    private double dividendYield;
    private double dividend;
    private String dividendYieldGroup;
    private String analystConsensusLabel;
    private String analystConsensus;
    private double analystPriceTarget;
    private double analystUpside;
    private double analystUpsideGroup;
    private String bestAnalystConsensusLabel;
    private String bestAnalystConsensus;
    private double bestAnalystPriceTarget;
    private double bestAnalystUpside;
    private double bestAnalystUpsideGroup;
    private String newsSentimentSignal;
    private String newsSentimentLabel;
    private double newsSentimentScore;
    private String insiderSentimentSignal;
    private String insiderSentimentLabel;
    private double insiderSentimentScore;
    private String mediaBuzzSignal;
    private String mediaBuzzLabel;
    private double mediaBuzzScore;
    private String hedgeFundSentimentSignal;
    private String hedgeFundSentimentLabel;
    private double hedgeFundSentimentScore;
    private String investorSentimentSignal;
    private String investorSentimentLabel;
    private double investorSentimentScore;
    private String bloggerSentimentSignal;
    private String bloggerSentimentLabel;
    private int bloggerSentimentBearishCount;
    private int bloggerSentimentBullishCount;

    public String getTicker() {
        return ticker;
    }

    public String getCompany() {
        return company;
    }

    public long getMarketCap() {
        return marketCap;
    }

    public String getMarketCapGroup() {
        return marketCapGroup;
    }

    public String getSectorName() {
        return sectorName;
    }

    public String getSector() {
        return sector;
    }

    public double getDividendYield() {
        return dividendYield;
    }

    public double getDividend() {
        return dividend;
    }

    public String getDividendYieldGroup() {
        return dividendYieldGroup;
    }

    public String getAnalystConsensusLabel() {
        return analystConsensusLabel;
    }

    public String getAnalystConsensus() {
        return analystConsensus;
    }

    public double getAnalystPriceTarget() {
        return analystPriceTarget;
    }

    public double getAnalystUpside() {
        return analystUpside;
    }

    public double getAnalystUpsideGroup() {
        return analystUpsideGroup;
    }

    public String getBestAnalystConsensusLabel() {
        return bestAnalystConsensusLabel;
    }

    public String getBestAnalystConsensus() {
        return bestAnalystConsensus;
    }

    public double getBestAnalystPriceTarget() {
        return bestAnalystPriceTarget;
    }

    public double getBestAnalystUpside() {
        return bestAnalystUpside;
    }

    public double getBestAnalystUpsideGroup() {
        return bestAnalystUpsideGroup;
    }

    public String getNewsSentimentSignal() {
        return newsSentimentSignal;
    }

    public String getNewsSentimentLabel() {
        return newsSentimentLabel;
    }

    public double getNewsSentimentScore() {
        return newsSentimentScore;
    }

    public String getInsiderSentimentSignal() {
        return insiderSentimentSignal;
    }

    public String getInsiderSentimentLabel() {
        return insiderSentimentLabel;
    }

    public double getInsiderSentimentScore() {
        return insiderSentimentScore;
    }

    public String getMediaBuzzSignal() {
        return mediaBuzzSignal;
    }

    public String getMediaBuzzLabel() {
        return mediaBuzzLabel;
    }

    public double getMediaBuzzScore() {
        return mediaBuzzScore;
    }

    public String getHedgeFundSentimentSignal() {
        return hedgeFundSentimentSignal;
    }

    public String getHedgeFundSentimentLabel() {
        return hedgeFundSentimentLabel;
    }

    public double getHedgeFundSentimentScore() {
        return hedgeFundSentimentScore;
    }

    public String getInvestorSentimentSignal() {
        return investorSentimentSignal;
    }

    public String getInvestorSentimentLabel() {
        return investorSentimentLabel;
    }

    public double getInvestorSentimentScore() {
        return investorSentimentScore;
    }

    public String getBloggerSentimentSignal() {
        return bloggerSentimentSignal;
    }

    public String getBloggerSentimentLabel() {
        return bloggerSentimentLabel;
    }

    public int getBloggerSentimentBearishCount() {
        return bloggerSentimentBearishCount;
    }

    public int getBloggerSentimentBullishCount() {
        return bloggerSentimentBullishCount;
    }
}
