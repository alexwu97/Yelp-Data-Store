### Yelp Data Store
[Yelp Academic Dataset](https://www.yelp.com/academic_dataset) is used for this project. The dataset is in JSON format and is maintained in a simple in-memory database with restaurants, users and reviews.

The server is handled to allow multi-client querying. In addition to CRUD access to the dataset, clients can also request server for predicting a user's rating for a particular restaurant. The server uses a supervised learning in least square regression on the dataset.




