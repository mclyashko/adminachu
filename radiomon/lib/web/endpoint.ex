defmodule RadioMonWeb.Endpoint do
  use Phoenix.Endpoint, otp_app: :radio_mon

  if code_reloading? do
    socket "/phoenix/live_reload/socket", Phoenix.LiveReloader.Socket
    plug Phoenix.CodeReloader
    plug Phoenix.LiveReloader
  end
  plug Plug.Static, at: "/media", from: "priv/static"
  plug Plug.Parsers, parsers: [:urlencoded]
  plug RadioMonWeb.Router
end