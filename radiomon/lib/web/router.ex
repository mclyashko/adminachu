defmodule RadioMonWeb.Router do
  use Phoenix.Router

  pipeline :browser do
    plug :accepts, ["html"]
  end

  scope "/" do
    pipe_through :browser
    get "/", RadioMonWeb.PlayerController, :show
  end
end