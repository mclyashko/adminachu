defmodule RadioMonWeb.Router do
  use Phoenix.Router

  pipeline :browser do
    plug CORSPlug
    plug :accepts, ["html"]
  end

  pipeline :api do
    plug CORSPlug
    plug :accepts, ["json"]
  end

  scope "/" do
    pipe_through :browser
    get "/", RadioMonWeb.PlayerController, :show
  end

  scope "/" do
    pipe_through :api
    post "/current_audio", RadioMonWeb.PlayerController, :current_audio
  end
end