# Parcel Management System

The **Parcel Management System** is a backend API built with Spring Boot to manage parcels associated with hotel guests. This application allows users to register parcels, check unpicked parcels, and more, without the need for a frontend interface. It uses RESTful endpoints to provide efficient parcel management.

---

## Features

- **Parcel Acceptance**: Accept parcels with details such as guest name, room number, parcel name, and the received timestamp.
- **Retrieve Unpicked Parcels**: Get a list of unpicked parcels for a specific room number.
- **Scalable Backend**: Designed to be lightweight and easily deployable in various environments.

---

## Technologies Used

- **Java**: Core language for backend logic.
- **Spring Boot**: Framework used to build RESTful APIs.
- **PostgreSQL**: Relational database management system for storing hotel and parcel data.
- **Docker**: Containerization for easy deployment (optional).

---

## API Endpoints

### `GET /parcels/unpicked?roomNumber=`
Get all unpicked packages for a room number, for example
```example
curl -X GET "http://localhost:8080/parcels/unpicked?roomNumber=102"
```

### `POST /parcels/accept`
Accept a parcel for a guest.

#### Request Body (JSON):
```json
{
  "guestName": "Bob",
  "roomNumber": 102,
  "parcelName": "Documents",
  "parcelReceivedTime": "2025-04-01T12:00:00"
}
```
```example
curl -X POST "http://localhost:8080/parcels/accept" \
     -H "Content-Type: application/json" \
     -d '{
       "guestName": "Bob",
       "roomNumber": 102,
       "parcelName": "Documents",
       "parcelReceivedTime": "2025-04-01T12:00:00"
     }'

```

## TODO's
- Swagger is not running correctly, which I do not have time to look further :(
- Logging should be added, along with a debug database like Kusto to make good use of it :)
- There is no special pattern for this project as the ask does not have enough context for scaling, although, I have tried my best to make it as simple and extendable as possible.
- There should be documentation for each function, which I will follow up in the future commits.
- Lacking unit tests for controller layers, as well as refactoring the folders for each Service/Controller/Schema/etc to be organized.
- No Deployment here, I do not have Heroku and my Azure is not up for use (NDA) sooooo... just run it locally ;)
- Have fun debugging!

## Contributing

### If you'd like to contribute:
- Fork the repository.
- Create a new branch (feature/your-feature).
- Submit a pull request with detailed notes.

## Licensing
This project is licensed under the MIT License.

## Contact

---

This version of the `README.md` now includes all relevant information, along with examples for cURL, making it comprehensive for users. Let me know if there’s anything else you'd like to tweak! 🚀
