# WanderMap — Backend

REST API for a personal travel mapping service built with Java & Spring Boot.

WanderMap lets you track every place you've visited on an interactive 3D globe — countries, cities, specific spots — with notes and photos that auto-place themselves on the map using EXIF GPS data.

## Tech Stack

- **Java 21** + **Spring Boot 3**
- **PostgreSQL** + **PostGIS** (geospatial queries)
- **Spring Security** + **JWT** authentication
- **Spring Data JPA** / Hibernate
- **Apache Commons Imaging** (EXIF parsing)
- **Docker** / Docker Compose

## Architecture

Three-layer REST API: React frontend → Spring Boot API → PostgreSQL

```
src/
├── controller/     # REST endpoints
├── service/        # Business logic
├── repository/     # JPA repositories
├── model/          # Entities: Country, City, Place, Photo, Tag
├── dto/            # Request / response objects
├── security/       # JWT filters
└── exception/      # Global error handling
```

## Key Features

- JWT-based authentication
- Three-level location hierarchy: Country → City → Place
- Photo upload with automatic GPS extraction from EXIF metadata
- PostGIS geospatial support (radius queries)
- Travel statistics dashboard
- Chronological timeline of visits
- Public / private visibility per entry

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register |
| POST | `/api/auth/login` | Login, returns JWT |
| GET | `/api/countries` | All visited countries |
| POST | `/api/countries` | Add a country |
| GET | `/api/countries/{id}/cities` | Cities in a country |
| POST | `/api/cities/{id}/places` | Add a place |
| POST | `/api/photos/upload` | Upload photo (EXIF auto-parsed) |
| GET | `/api/stats` | User statistics |
| GET | `/api/timeline` | Chronological trip history |

## Getting Started

```bash
# Clone and start with Docker
git clone https://github.com/kajzen/WanderMap.git
cd WanderMap
docker-compose up -d
```

API runs at `http://localhost:8080`

## Status

In active development — v1.0 MVP in progress.

Frontend: [WanderMapFront](https://github.com/kajzen/WanderMapFront)
