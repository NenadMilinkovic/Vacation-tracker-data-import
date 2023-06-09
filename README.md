# Vacation-tracker-data-import service

## Working with docker

Clone also Vacation-tracker-data-search service(https://github.com/NenadMilinkovic/Vacation-tracker-data-search)

Run services in next way:

* In docker-compose.yml change build path for search service to root folder of it
* Run command:

  `docker compose up`

## Header

Every request must have X-API-KEY header and value ABCD 

## Import Employee Profiles

* **URL:**

  `/api/dataImport/importEmployee`

* **Method**

  `POST`

* **Body Params:**

  **Required:**

  `file=[multipart file]`

  Example: `employee_profiles.csv`

## Import number of vacation days for year

* **URL:**

  `/api/dataImport/importVacationDays`

* **Method**

  `POST`

* **Body Params:**

  **Required:**

  `file=[multipart file]`

  Example: `vacations_2019.csv`

## Import used vacation days

* **URL:**

  `/api/dataImport/importUsedVacationDays`

* **Method**

  `POST`

* **Body Params:**

  **Required:**

  `file=[multipart file]`

  Example: `used_vacation_dates.csv`

## Example files

Example .csv files are located in `Samples` directory.