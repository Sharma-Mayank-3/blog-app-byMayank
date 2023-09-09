# blog-app-byMayank
#

# User Entity 
1. All CRUD Operations.


# Category Entity 
1. Create and Get All operations. 
2. findByCategoryId, 
3. Use of like and exact categoryTile match in CategoryRepo class. 


# Post Entity 
1. Post CRUD
2. Get Posts by category findBy
3. Get posts by user by native query.
4. Get Post by search keyword in postTitle like native query.
5. Implement Pagination and Sorting in get All Post new API and Modify Post Response as per this

# Comment Entity
1. Create Comment
2. Get All Comment on Post 
3. Search Comment
4. findByCommentContentContaining -> In this search based on containing(like)
5. findByCommentContentLike ->  In this search based on exact keyword

# Some Observation
# FetchType.LAZY :
1. if we delete parent(user) the child(post) will get delete.
2. if we try to delete child then child will get delete but not parent.

# FetchType.EAGER :
1. if we delete parent(user) the child(post) will get delete.
2. if we try to delete child then child will "not" get delete also the parent.




# Spring Security

# Pom Dependency
1.     <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-security</artifactId>
       <version>3.1.3</version>
       </dependency>

Note : With this a password is generated in console and userName is user, one can do form based authentation using user and password to access the request.  
or if define the below properties in application file, then use this username and password to do the form based login.
# userName, Password and Role already defined in application.properties file.
1. spring.security.user.name=mayank
2. spring.security.user.password=password
3. spring.security.user.roles=USER,ADMIN

Note : "we are not interested in this above form based, we need to pass it in postman."

For this disable the form based and get the basic auth, means comment the application properties new changes 
and create a class "SecurityConfig", have a look at the class SecurityConfig SecurityFilterChain method.

# Take the username password and role from DB.

# Create Entity Role
Define many to many relation between role and user.
1. create bean AuthenticationManager
2. bean DaoAuthenticationProvider
3. bean PasswordEncoder
4. create class CustomUserDeatilsService and get the user by userName from userRepo.
5. implement CustomUserDeatilsService class with UserDetailsService.
6. User class implement with UserDetails and see the changes.
7. now go with basic auth, pass userName and password to access the api's
8. NOTE : "POST and PUT and DELETE api's cant be accessed by this above" 


# JWT Authentication
1. we want to create a token and with that token only all the api's should be accessible.
2. remove basic auth from SecurityConfig and follow 8 steps of JWT.

# 8 steps of JWT.
1. include 2 dependencies

       <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt</artifactId>
        <version>0.9.1</version>
        </dependency>

   	<dependency>
   		<groupId>javax.xml.bind</groupId>
   		<artifactId>jaxb-api</artifactId>
   		<version>2.3.1</version>
   	</dependency>

2. Inside security package create 3 class
 a. JwtAuthentationEntryPoint
 b. JwtAuthentationFilter
 c. JwtTokenHelper
3. make changes in SecurityConfig class see it.
4. create a AuthController and it can be access normally.
5. define some JwtException.

# Authorization || Role based Authentication
# we want to delete user only by admin.
use this in SecurityConfig

     .authorizeHttpRequests(auth -> auth.requestMatchers("/blog-app/v1/auth/login").permitAll()
     .requestMatchers("/blog-app/user/create").permitAll()
     .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
     .requestMatchers("/blog-app/admin").hasRole("ADMIN")
     .requestMatchers("/blog-app/normal").hasRole("NORMAL")
     .requestMatchers("/blog-app/about").permitAll()
     //                        .requestMatchers("/blog-app/normal").hasRole("ADMIN")
     .anyRequest().authenticated())

# NOTE : 
1.    USER mayank password : "xyz", has ADMIN role
2.    User hello password : "hello" has NORMAL role 


