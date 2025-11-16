# API Documentation

```bash
GET     /api/v1/users               # get all users
GET     /api/v1/users/{userId}      # get user by id
POST    /api/v1/users               # create user
PUT     /api/v1/users/{userId}      # update user by id
DELETE  /api/v1/users/{userId}      # delete user by id

GET     /api/v1/users/{userId}/bookshelf    # get user bookshelf
POST    /api/v1/users/{userId}/bookshelf    # create user bookshelf
PUT     /api/v1/users/{userId}/bookshelf    # update user bookshelf
DELETE  /api/v1/users/{userId}/bookshelf    # delete user bookshelf
GET     /api/v1/users/{userId}/bookshelf/books              # get books from user bookshelf
POST    /api/v1/users/{userId}/bookshelf/books/{bookId}     # add book to bookshelf
DELETE  /api/v1/users/{userId}/bookshelf/books/{bookId}     # delete book from bookshelf

GET     /api/v1/books               # get all books
GET     /api/v1/books/{bookId}      # get book by id
POST    /api/v1/books               # create book
PUT     /api/v1/books/{bookId}      # update book by id
DELETE  /api/v1/books/{bookId}      # delete book by id

GET     /api/v1/bookshelves                 # get all public bookshelves
GET     /api/v1/bookshelves/{bookshelfId}   # get public bookshelf by id

```
