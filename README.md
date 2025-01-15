

# Stocks Checker #

For this project we use a Stocks feed API to provide incoming data.
- It uses an api service called polygon.io
- We call the endpoint [Daily Open/Close](https://polygon.io/docs/stocks/get_v1_open-close__stocksticker___date) passing the stock symbol and date
- This returns json like so : 

`{
"afterHours": 322.1,
"close": 325.12,
"from": "2023-01-09",
"high": 326.2,
"low": 322.3,
"open": 324.66,
"preMarket": 324.5,
"status": "OK",
"symbol": "AAPL",
"volume": 26122646
}`


#### Companies and Symbols

```csv
AAPL,Apple Inc
MSFT,Microsoft Corp
GOOG,Alphabet Inc Class C
AMZN,Amazon.Com Inc.
NVDA,Nvidia Corp
TSLA,Tesla Inc
META,Meta Platforms, Inc.
NFLX,Netflix Inc
INTC,Intel Corporation Corp
SBUX,Starbucks Corp
ABNB,Airbnb, Inc.
PYPL,Paypal Holdings Inc
ATVI,Activision Blizzard Inc
MDLZ,Mondelez International Inc
ADP,Automatic Data Processing Inc
REGN,Regeneron Pharmaceuticals Inc
ISRG,Intuitive Surgical Inc
VRTX,Vertex Pharmaceuticals Inc
MU,Micron Technology Inc
MELI,Mercadolibre Inc
```
