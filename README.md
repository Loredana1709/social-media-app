# social-media-app

Bankend part for a social media platform -  user management epic.

Contains:

  REGISTER NEW USER:
  - the request contains basic info (first and last name, username, email, birth date, gender, password)
  - API that gets the input, validates it and persist it in the database using POST endpoint
  - database has an user table (uses Spring Data)
  
  GET USER PROFILE:
  - API that retrieve the user profile (does not return user password)
  - if a user is viewing its own profile, return more information (uses principal from Spring Security)
  
  UPDATE USER PROFILE:
  - API using PUT that updates some of the fields
  - as PUT was used, the data from db was checked before persisting it into db
  
  LOGIN USER:
  - API that retrieve the email and password and check them against the db
  
  The app is secured using Spring Security:
  
    ACCESS TOKEN - during the login process, if the username and password are matching, generate a JWT that will be used for all the subsequent request;
    
    AUTHORIZATION - some of the actions can be performed only by authenticated users
    
    PERMISSIONS - even if the user is authenticated, some actions are restricted to a user itself (e.g. update user profile)
    
