❗  Currently in development ❗ 

Getting started:

✅ Set up a MongoDB Atlas free cluster and a simple database named teomongotext, with two collections inside (files and characters). I used the AWS server closest to me to host the database. Create a user and a connection string for said database

✅ Install a Java Runtime Enviorment and download an appropriate JavaFX runtime and JavaFX jmods for your operating system and unzip them to a desired location.

Add these environment variables pointing to the lib directory of the runtime and to the jmods directory:

Linux/Mac

```
export PATH_TO_FX=path/to/javafx-sdk-14/lib
```

Windows

```
set PATH_TO_FX="path\to\javafx-sdk-14\lib"
```


✅ Compile the app yourself or simply run the jar file. Don't worry about the Settings.ini file, it will be created automatically inside your Documents folder, regardless of the OS you run on.

✅ Navigate to the edit menu and use the option to add your connection string

✅ You should now be connected to your database, ready to sync your next masterpiece 😁
