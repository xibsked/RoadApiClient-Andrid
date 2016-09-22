# RoadApiClient-Andrid
##Client library implementation of google's Road API
   ![screenshots](https://raw.githubusercontent.com/xibsked/RoadApiClient-Andrid/master/device-2016-09-17-163711.png) ![screenshots](https://raw.githubusercontent.com/xibsked/RoadApiClient-Andrid/master/device-2016-09-17-163745.png) ![screenshots](https://raw.githubusercontent.com/xibsked/RoadApiClient-Andrid/master/device-2016-09-17-163845.png) ![screenshots](https://raw.githubusercontent.com/xibsked/RoadApiClient-Andrid/master/device-2016-09-17-163920.png)

##How to add library to project
   1. Clone or Download project
   2. Import 'roadapiclient' module to your project in Android Studio
*Note*
       You might have to enable dex supports.

##How to use RoadApiClient
Its pretty simple -

     RoadApiClient.with(<Context>)
                  .interpolate(true)
                  .spots(getDummySpots())
                  .key(<YOUR_API_KEY>)
                  .roadSnapCallback(roadSnapCallback)
                  .execute();
                
Where roadSnapCallback can be-

     RoadSnapCallback roadSnapCallback = new RoadSnapCallback() {
        @Override
        public void onSnappedPointSuccess(SnappedPoints snappedPoints) {
            //You can use snappedPoints to draw marker or polylines on map
        }

        @Override
        public void onSnappedPointError(Error error) {
           //When error occurs
        }
    };
