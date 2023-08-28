

# Test instructions #

The following are the instructions for completing the test.  ***Please read through them all before starting.***

The idea of this test is to see how much of the following you can achieve within a small timeframe – I am not expecting all points below to be covered (if you do – great!), but to achieve as much as is possible, concentrating to start with on adding the most **business value** before thinking of fixing / tidying any existing issues. 

For this project you will be using a Stocks feed API to provide incoming data. The API has already been implemented to fetch the data.  
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

I would like you to spend a **maximum** of 3 hours on this Java Spring Boot project which accomplishes the following:

 - Loop through each of the 20 companies in the list provided in the code (also referenced below) and get the stock data for the last 3 days for each company
 - Store the data from each stock symbol for each day into a structure that can be queried. 
 - The console/log window should output at regular intervals (say every 10 seconds) the following stats: 
	 - **Stat 1** : The top 10 highest value stocks by `close` value (in order – largest first, then order by company code in the case of a tie in value)
		 - This can only be achieved when there are 10 or more data points, so until there is enough data to show - an appropriate message should be displayed instead.
	 - **Stat 2** : The top 5 companies that have the greatest overall ***positive*** % change percent in stock value over `any 3 day` period.  
		 - These should be listed in % change order, with the highest % change at the top.  Then ordered by stock symbol in the case of a tie.
           - The change should be considered from the earliest `open` value to the latest `close` value.		 
		 - This can only be achieved when there are 2 or more data points for at least 5 companies in order to determine a delta - so until there is enough data, show an appropriate message instead.
 - Once the process has completed i.e. looped through all companies for 3 days the application should start to download the next previous 3 days (i.e. -3 days then -6 then -9 etc)
 

### Stretch goals 
If you finish the above and wish to continue, then the following are stretch goals:

- Use Lombok to remove as much boilerplate code as possible
- Replace any code you consider bad practice (with comments)
- Add one or more Rest APIs to retrieve the same data sets that are being printed to the console.

### Important!
Please to make sure you commit your work into the repo at the end of the task, within the 3 hrs!  It is a good idea to commit several times, at various milestones during the task, so the assessor can see the progress as a series of logical stages.

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