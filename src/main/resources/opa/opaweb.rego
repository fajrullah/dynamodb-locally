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
    input.method == "PUT"
    input.path == ["info"]
    is_admin
}

allow {
    input.method == "POST"
    input.path == ["info"]
    is_admin
}

allow {
    input.method == "DELETE"
    input.path == ["info"]
    is_admin
}

allow {
    input.method == "GET"
    input.path[0] == "api"
    is_admin
}

allow {
    input.method == "GET"
    input.path == ["api", "students"]
    is_admin
}

allow {
    input.method == "PUT"
    input.path == ["api", "students"]
    is_admin
}

allow {
    input.method == "DELETE"
    input.path == ["api", "students"]
    is_admin
}

allow {
    input.method == "POST"
    input.path == ["api", "students"]
    is_admin
}

is_user {
  some i
  input.roles[i].authority == "ROLE_USER"
}

is_admin {
  some i
  input.roles[i].authority == "ROLE_ADMIN"
}
