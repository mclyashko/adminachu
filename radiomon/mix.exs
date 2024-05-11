defmodule RadioMon.MixProject do
  use Mix.Project

  def project do
    [
      app: :radio_mon,
      version: "1.0.0",
      deps: deps()
    ]
  end

  defp deps do
    [
      {:ecto_sql, "~> 3.11.0"},
      {:postgrex, "~> 0.17.0"},
      {:id3v2, "~> 0.1.2"},
      {:phoenix, "~> 1.5"},
      {:jason, "~> 1.2"},
      {:phoenix_html, "~> 3.0"},
      {:phoenix_view, "~> 2.0"},
      {:plug_cowboy, "~> 2.5"},
      {:phoenix_live_reload, "~> 1.3"},
      {:cors_plug, "~> 3.0"}, # надо? см. plug CORSPlug
    ]
  end

  def application do
    [
      mod: {RadioMon.Application, []}
    ]
  end
end
