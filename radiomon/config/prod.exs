import Config

repo_url = System.get_env("REPO_URL") || raise "REPO_URL not set"
repo_username = System.get_env("REPO_USERNAME") || raise "REPO_USERNAME not set"
repo_password = System.get_env("REPO_PASSWORD") || raise "REPO_PASSWORD not set"
port = System.get_env("PORT") || raise "PORT not set"

config :radio_mon, RadioMon.Repo,
  url: repo_url,
  username: repo_username,
  password: repo_password

config :radio_mon, RadioMonWeb.Endpoint,
  http: [port: String.to_integer(port)]
