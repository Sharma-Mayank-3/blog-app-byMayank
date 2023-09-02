# blog-app-byMayank

# User Entity with all the CRUD operation

# Category Entity with Create, getAll.

# Category Entity, 
1. findByCategoryId, 
2. Use of like and exact categoryTile match in CategoryRepo class. 

# Post Entity 
1. Post CRUD
2. Get Posts by category findBy
3. Get posts by user by native query.


# Some Observation
# FetchType.LAZY :
1. if we delete parent(user) the child(post) will get delete.
2. if we try to delete child then child will get delete but not parent.
3. 
# FetchType.EAGER :
1. if we delete parent(user) the child(post) will get delete.
2. if we try to delete child then child will "not" get delete also the parent.