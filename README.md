# Simple Java day planner

### Includes:
1. Ability to create new Events with the following properties:
    - Event description
    - type (meeting or task)
    - privacy (public, confidential, or personal)
    - date ("yyyy-MM-dd")
    - time ("hh:mm")
    
2. Ability to edit the events and all of their properties
3. Ability to delete each event
4. Ability to show all events for a specified criteria:
    - for a specific date
    - for a specific month
    - of a specific type
    - for a specific range between two dates
    
### All data is stored in a MySql database

_________________

# [Link for testing](https://arnaudok-planner.herokuapp.com/events)

_________________

## Can also be tested locally
#### Requirements: JRE or JDK and a MySql service running on port 3306
1. Download the eventPlanner.jar and event.sql files or clone the repository to your machine
2. Create a new DB called 'event' in your MySql service and run the command
```shell
   $ mysql -u username -p event < event.sql
```
3. navigate to the eventPlanner.jar and run the command
```shell
   $ java -jar eventPlanner.jar
```

4. Open [postman](https://www.postman.com) or any other api testing tool and send your requests to http://localhost:8080/events/
#### Permitted methods:
   - GET ( e.g. http://localhost:8080/events; http://localhost:8080/events?filter=type&type=task; http://localhost:8080/events?filter=day&date=2021-05-15...)
   - POST (e.g. body: {"type":"task","privacy":"confidential","description":"Write email to Ivan","date":"2021-05-12","time":"09:00:00"})
   - PUT (e.g. http://localhost:8080/events/25?type=task )
   - Delete (e.g. http://localhost:8080/events/25)
