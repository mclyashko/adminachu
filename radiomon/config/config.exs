import Config

config :radio_mon, RadioMon.Repo,
  database: "postgres-db",
  username: "postgres-user",
  password: "postgres-password",
  hostname: "localhost"

config :radio_mon, ecto_repos: [RadioMon.Repo]

config :radio_mon, RadioMonWeb.Endpoint,
  http: [port: 4000],
  debug_errors: true,
  pubsub_server: RadioMon.PubSub,
  code_reloader: true,
  live_reload: [
    patterns: [
      ~r{priv/static/.*(js|css|png|jpeg|jpg|gif)$},
      ~r{lib/web/view/.*(ex)$},
      ~r{lib/web/template/.*(eex)$}
    ]
  ]
