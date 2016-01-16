#!/usr/bin/env python

from geopy.geocoders import Nominatim

geolocator = Nominatim()
#for address to coordinates translation: geolocator.geocode("Address")
location = geolocator.reverse("52.509669, 13.376294")
# For coordinates: print location.latitude, location.longitude
print location.address

