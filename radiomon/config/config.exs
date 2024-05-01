import Config

config :radio_mon, RadioMon.Repo,
  database: "postgres-db",
  username: "postgres-user",
  password: "postgres-password",
  hostname: "localhost"

config :radio_mon, ecto_repos: [RadioMon.Repo]
