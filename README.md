# Incomex Test!

This is a sample API destined to be used by an hotel to manage reservation.

These were some requirements 
- For the purpose of the test, we assume the hotel has only one room available
- To give a chance to everyone to book the room, the stay can’t be longer than 3 days and
  can’t be reserved more than 30 days in advance.
- All reservations start at least the next day of booking,
- To simplify the use case, a “DAY’ in the hotel room starts from 00:00 to 23:59:59.
- Every end-user can check the room availability, place a reservation, cancel it or modify it.
- To simplify the API is insecure.

To run the API you need to import the project to your favorite IDE and compile it. After that it will download all 
the required dependencies. 

After, that, start the project by clicking the button run on the project.

If you want to see the API documentation, 
after starting the project go to [http://localhost:8060/api/msbooking/swagger-ui/index.html#/](http://localhost:8060/api/msbooking/swagger-ui/index.html#/)

There is an in-memory database with h2. Just to keep records everytime you run the app. Bear in mind these values will be lost everytime you restart the app.

To access the database console go to [http://localhost:8060/api/msbooking/h2-console/](http://localhost:8060/api/msbooking/h2-console/)