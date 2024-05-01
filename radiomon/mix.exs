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
      {:postgrex, "~> 0.17.0"}
    ]
  end

  def application do
    [
      mod: {RadioMon.Application, []}
    ]
  end
end
