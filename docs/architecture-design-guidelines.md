# Architecture Design Guidelines
This is rough list of steps required to design application architecture before development starts.

## Collect High-Level requirements
* Use-cases or user stories.
* Functional requirements.
* Non-Functional requirements.
* Security requirements.
* HA and / or performance requirements.

## Data model analysis
* Data model - entity data model and relations between entities.
* State diagrams for important business entities - states and transitions between states.
* Scopes of entities - application scope, conversation scope, session scope, ...
* Domain and sub-domain data models - split data model to domain and/or sub-domains.

## Identify service types
* Stateful, or stateless services.
* Clustering and shared caches.

## Database technologies
* Select proper database technology for the task.
* Transactional data: SQL
* Document data: MongoDB, CouchDB, ElasticSearch
* Time-Series data: InfluxDB
* Graph data: Neo4j



