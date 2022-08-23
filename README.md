# recommendation-service

to import CSV to the database you need to use endpoint localhost:8080/upload and pass the CSV file as a 'file' parameter.
if successfull, this message will be shown - "Uploaded the file successfully: ETH_values.csv".

after importing CSV, there are several endpoints:

recommendation-service/normal-range : get normal range for each crypto

recommendation-service/monthly/{month} : get max/min/newest/oldest prices for specific month (pass month number as a parameter)

recommendation-service/crypto/{name} : get the same data as above, pass specific crypto as a parameter (BTC, ETH, DOGE, LTC, XRP)