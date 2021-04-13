# stockChecker
Stock checker for various websites.
Requisites:

Java JDK 11+

Features:

-Has a refresh one by one option (asynchronous) or a synchronous where the websites are refreshed indenpendently.
-Gui is optional but recommended.

Sample usage:

Upload a raw archive to i.e (pastebin,github...) in xml format like this
```
<?xml version="1.0" encoding="ISO-8859-1"?>  
<pages>  
  <page>
	<url>https://www.am....</url>
	<word>sampleword#sampleword2</word>
	<time>60000</time>
  </page>
  <page>
	<url>...</url>
	<word>...</word>
	<time>...</time>
  </page>
</pages>  
```
The tag url is the url itself of the article, word is the keyword that checks 
if the article is available for purchase like "Buy now" or "add to cart".
Time is the refresh time so the store doesn't check if you're a robot via captcha.
Configuration params are in "application.properties".
