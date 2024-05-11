import Config

config :radio_mon, ecto_repos: [RadioMon.Repo]

config :radio_mon, RadioMonWeb.Endpoint,
  pubsub_server: RadioMon.PubSub

config :radio_mon, RadioMon.Processor,
  threshold: 10_000,
  gap: 3_000

config :radio_mon, RadioMon.Processor.Media,
  base_url: "/media/"

import_config "#{Mix.env()}.exs"
