package com.example;


/*
* Example of Google's Geocoder API usage.
*
* Add the following in pom.xml:
*
*	<dependency>
*		<groupId>com.google.code.geocoder-java</groupId>
*		<artifactId>geocoder-java</artifactId>
*		<version>0.16</version>
*	</dependency>
*
*/



import java.io.IOException;
import java.util.List;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.LatLng;
public class LatLongConverterToAddress {

	public static void main(String[] args) throws IOException {
		Geocoder geocoder = new Geocoder();
		LatLng location = new LatLng("37.521439","-121.99403");
		/*
		* GeocoderRequestBuilder Also has setAddress() method , if you
		* want to convent Address to coordinates.
		*/

		GeocoderRequest geoRequest = new GeocoderRequestBuilder().setLocation(location).getGeocoderRequest();
		
		GeocodeResponse geoResponse = geocoder.geocode(geoRequest);
		List<GeocoderResult> result = geoResponse.getResults();
		String address = result.get(0).getFormattedAddress();
		
		System.out.println(address);
	}
}
