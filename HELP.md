
Creamos otra instancia del currencyExchange con la misma configuración que la
original, pero añadiendo un VMoption -Dserver.port=8001 para cambiar el puerto, ya que la VMoption tiene
preferencia sobre el application.properties de la instancia.
http://localhost:8001/currency-conversion/from/USD/to/INR/quantity/10