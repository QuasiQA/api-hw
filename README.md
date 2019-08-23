# Project overview
## Task
You need to create a basic framework that will use the following public API that can detect language by text:
[languagelayer](https://languagelayer.com)

The framework needs to meet certain requirements:
1. Sentences to detect language should be stored in the file
2. You need to perform several validations per each request:
    * status code
    * response contains `"success":true`
    * language is detected correctly

## Deadline
2019-08-23

# Results
## Definition of "language is detected correctly"
I'm considering this is when percentage of the detection is greater or equal to 90%.<br>
So threshold was set to define this correctness

## Test execution
* In command line from project folder type `gradlew cleanTest test`
* Generate allure report
  1. In command line from project folder type `gradlew allureServe`. Browser tab will be opened with report.
  2. OR In command line from project folder type `gradlew allureReport`, then in browser open file `<project_dir>/build/reports/allure-report/index.html`
  Important! You should open it from your IDE via context menu **Right click > Open in browser > Default**
  Don't open directly from your OS. That will cause broken report
* Click on **Suites** tab, select any test to see details

## Known limitation
* regression suite can't be executed due to issue, potentially with TestNG groups.
The suite was added to show the group functionality of TestNG library
