defmodule RadioMon.Application do
  use Application

  def start(_type, _args) do
    children = [
      RadioMon.Repo,
      RadioMonWeb.Endpoint,
      {Phoenix.PubSub, [
        name: RadioMon.PubSub,
        adapter: Phoenix.PubSub.PG2
      ]},
      {Cachex, name: :audio_cache},
    ]
    Supervisor.start_link(children, strategy: :one_for_one)
  end
end