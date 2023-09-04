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