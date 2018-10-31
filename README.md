# Startup Managment System

## Project Setup
### InteliJ 
1. Create a new project i.e startup-managment
2. locate `src` folder 
3. delete main and git bash
4. ` git init `
5. ` git remote add origin https://github.com/EdwinWalela/startup-managment.git `
6. ` git pull origin master `
And you are good to go!

### Netbeans
1. Create a new project called `main`
2. locate `src` folder
3. follow steps 3 - 6 of InteliJ setup

### Database setup
1. Run Xampp(Ensure apache and mysql are running) and open `shell`
2. run `mysql -h localhost -u root`
3. copy the contents of the `sql` file and paste it in the terminal

## Project workflow
Below is the work flow when working on this project

Every time you open the project
1. `git pull origin master`

You work on the project (add/change/delete)


2. `git add .`
3. `git commit -m "what have i changed/added/removed"`
4. `git push origin master`

## Project structure

### Tables
#### 1. startups
| startup_id        | name           | founder  |doj        | domain     | email  | contact|
| -------------     |-------------   | -----    | --------  |------------| -------|--------|
#### 2. users
| user_id      | username       | startup_id| email     | password   | admin  | startup_id|
| --------     |-------------   | -----     | --------  |------------| -------|----------|


## The Query Object
This object has a number of functions which facilitate the communication between the application and the database. Below is a list of functions attached to the `query` object

Inorder to use the `Query` object in other files (apart from main) ensure the file/class has a Query attribute then initialize the Query attribute in the class's constructor
```java
public class AdminPanel{
  Query query;
  
  public AdminPanel(Query newQuery){
    query = newQuery;
    // do your stuff
    // i.e int totalUsers = query.tableCount("users");
  }
}
```

#### To test a separate JFrame (i.e UserdashBoard) 

1. Comment out any code present in main except this lines

```java
 Connection conn = new Configuration().newConnection();
 Query query = new Query(conn);
```

2. Create an instance of your class in main

```java
  Userdashboard userView = new Userdashboard(query);
```
3. Ensure your class's constructor expects a `Query newQuery` parameter
4. Also ensure your class `setVisible` is set to `true`

5. When done testing comment out your class's declaration and uncomment what you commented out in (1.)

### registerUser
expects to be called with an array string[6] in the order(USER_ID,USERNAME,STARTUP_ID,EMALL,PASSWORD,ADMIN)

```java
String userId = useridinput.getText();
String username = usernameinput.getText();
        ........
String admin = adminInput.getText();

boolean success = query.registerUser(new String[]{userId,username,....,admin});

if(success){
  //new user created 
}else{
  //failed to add user
}
```
### userLogin
expects to be called with an array of String[2] in the order(userID,password) and return a boolean array of length 2
```java
String userId = useridInput.getText();
String password = passwordInput.getText();

boolean[] result = query.userLogin(new String[]{userId,password});

if(result[0]){
  // passwords match
  if(result[1]){
    // passwords match and this is an admin
  }else{
    //passwords match but this is a normal user
  }
}else{
   //wrong login details
}

```
  
### startupResistration
expects to be called with an array String[7] in the order (NAME,FOUNDER,DOJ,DOMAIN,EMAIL,CONTACT,STARTUP_ID) and returns a boolean

```java
String startupName = startupnameInput.getText();
String founder = founderInput.getText();
             .......
String startupId = startupIdInput.getText();
boolean result = query.startupRegistration(new String[]{startupName,founder,......,startupId});

if(result){
 // new startup added
}else{
 // insertion failed
}
```
### tableCount
expects to be called with a string of the required table and returns an integer of the table count.

```java
int totalUsers = query.tableCount("users");
```

### fetchData
expects to be called with the following arguments (table,field)(String) and returns a ResultSet

```java
ResultSet rs = query.fetchData("users","*");
try{
  while(rs.next()){
    System.out.println(rs.getString(1));
  }
}catch(SQLException e){ 
  System.out.println(e.getMessage());
}

```

