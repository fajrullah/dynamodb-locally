package opaweb.authz

default allow = false

allow {
  input.method == "GET"
  input.path = ["public"]
  is_user
}

allow {
    input.method == "GET"
    input.path == ["api", "students"]
    is_user
}

allow {
  input.method == "GET"
  input.path == ["info"]
  is_admin
}

allow {
    input.path == ["info"]
    is_admin
}

allow {
  input.path[0] == "graphql"
  is_admin
}

allow {
   input.path[0] == "graphiql"
   is_admin
}

allow {
    input.path[0] == "api"
    is_admin
}

# user is allowed if he has a user role
is_user {
	# for some `i`...
	some i
  input.roles[i].authority == "ROLE_USER"
}

# user is allowed if he has a admin role
is_admin {
	# for some `i`...
	some i
  input.roles[i].authority == "ROLE_ADMIN"
}
