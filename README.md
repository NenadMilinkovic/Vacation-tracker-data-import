# Vacation-tracker-data-import service

## Working with docker

Clone also Vacation-tracker-data-search service(https://github.com/NenadMilinkovic/Vacation-tracker-data-search)

Run services with command:

    docker compose up

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